package PaooGame;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Input.KeyManager;
import PaooGame.Items.RICK;
import PaooGame.States.*;
import PaooGame.Tiles.Tile;
import PaooGame.Input.MouseManager;
import java.awt.*;
import java.awt.image.BufferStrategy;

/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie). Mai multe explicatii veti primi la curs.

    In mod obisnuit aceasta clasa trebuie sa contina urmatoarele:
        - public Game();            //constructor
        - private void init();      //metoda privata de initializare
        - private void update();    //metoda privata de actualizare a elementelor jocului
        - private void draw();      //metoda privata de desenare a tablei de joc
        - public run();             //metoda publica ce va fi apelata de noul fir de executie
        - public synchronized void start(); //metoda publica de pornire a jocului
        - public synchronized void stop()   //metoda publica de oprire a jocului
 */
public class Game implements Runnable
{
    private GameWindow      wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean         runState;   /*!< Flag ce starea firului de executie.*/
    private Thread          gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy  bs;         /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
    private final database database;          /*!< Referinta catre baza de date a programului*/
    private static Game instance = null;

    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************

    private Graphics        g;          /*!< Referinta catre un context grafic.*/

    ///Available states
    private State playState;            /*!< Referinta catre joc.*/
    private State menuState;            /*!< Referinta catre menu.*/
    private State nivel1State;            /*!< Referinta catre nivel1.*/
    private State nivel2State;            /*!< Referinta catre nivel1.*/
    private State GameOverState;
    private State settingsState;        /*!< Referinta catre setari.*/
    private State pauseState;        /*!< Referinta catre pause state.*/
    private State endState;           /*!< Referinta catre end state.*/
    private KeyManager keyManager;      /*!< Referinta catre obiectul care gestioneaza intrarile din partea utilizatorului.*/
    private RefLinks refLink;            /*!< Referinta catre un obiect a carui sarcina este doar de a retine diverse referinte pentru a fi usor accesibile.*/


    private MouseManager mouseManager;

    private Tile tile; /*!< variabila membra temporara. Este folosita in aceasta etapa doar pentru a desena ceva pe ecran.*/

    /*! \fn public Game(String title, int width, int height)
        \brief Constructor de initializare al clasei Game.

        Acest constructor primeste ca parametri titlul ferestrei, latimea si inaltimea
        acesteia avand in vedere ca fereastra va fi construita/creata in cadrul clasei Game.

        \param title Titlul ferestrei.
        \param width Latimea ferestrei in pixeli.
        \param height Inaltimea ferestrei in pixeli.
     */
    public Game(String title, int width, int height)
    {
        /// Obiectul GameWindow este creat insa fereastra nu este construita
        /// Acest lucru va fi realizat in metoda init() prin apelul
        /// functiei BuildGameWindow();
        wnd = new GameWindow(title, width, height);
        /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)
        runState = false;
        ///Construirea obiectului de gestiune a evenimentelor de tastatura +mouse
        keyManager = new KeyManager();
        mouseManager=new MouseManager();
        ///Construirea obiectului de gestiune a bazei de date
        database = new database();
    }

    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    private void InitGame()
    {
        /// Este construita fereastra grafica.
        wnd.BuildGameWindow();
        ///Sa ataseaza ferestrei managerul de tastatura pentru a primi evenimentele furnizate de fereastra.
        wnd.GetWndFrame().addKeyListener(keyManager);
        wnd.GetWndFrame().addMouseListener(mouseManager);
        wnd.GetWndFrame().addMouseMotionListener(mouseManager);
        wnd.GetCanvas().addMouseListener(mouseManager);
        wnd.GetCanvas().addMouseMotionListener(mouseManager);

        ///Se incarca toate elementele grafice (dale)
        Assets.Init();
        ///Se construieste obiectul de tip shortcut ce va retine o serie de referinte catre elementele importante din program.
        refLink = new RefLinks(this);
        ///Definirea starilor programului
        playState       = new PlayState(refLink);
        menuState       = new MenuState(refLink);
        settingsState   = new SettingsState(refLink);
        endState      = new EndState(refLink);
        nivel1State     =new Nivel1State(refLink);
        nivel2State     =new Nivel2State(refLink);
        pauseState      = new PauseState(refLink);
        GameOverState  =new GameOverState(refLink);

        //Seteaza starea implicita cu care va fi lansat programul in executie
        State.SetState(menuState);

    }


    public static Game getInstance(String s, int x, int y)
    {
        if(instance==null)
        {
            instance = new Game(s,x,y);
        }
        return instance;
    }




    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run()
    {
        /// Initializeaza obiectul game
        InitGame();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState == true)
        {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {
                /// Actualizeaza pozitiile elementelor
                Update();
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                oldTime = curentTime;
            }
        }

    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame()
    {
        if(runState == false)
        {
            /// Se actualizeaza flagul de stare a threadului
            runState = true;
            /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
            /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
            /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }
        else
        {
            /// Thread-ul este creat si pornit deja
            return;
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StopGame()
    {
        if(runState == true)
        {
            /// Actualizare stare thread
            runState = false;
            /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try
            {
                /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
        else
        {
            /// Thread-ul este oprit deja.
            return;
        }
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Update()
    {
        ///Determina starea tastelor
        keyManager.Update();
        ///Trebuie obtinuta starea curenta pentru care urmeaza a se actualiza starea, atentie trebuie sa fie diferita de null.
        if(State.GetState() != null)
        {
            ///Actualizez starea curenta a jocului daca exista.
            State.GetState().Update();
            wnd.GetWndFrame().requestFocusInWindow(); //treaba asta face ca imi functioneaza tastele in joc chiar daca eu dau vreun click pe harta, fara nu mergea; solutie de pe stackoverflow si ce am mai vazut pe google
        }
    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Draw()
    {
        /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();
        /// Verific daca buffer strategy a fost construit sau nu
        if(bs == null)
        {
            /// Se executa doar la primul apel al metodei Draw()
            try
            {
                /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
        /// Se obtine contextul grafic curent in care se poate desena.
        g = bs.getDrawGraphics();
        /// Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        /// operatie de desenare
        ///Trebuie obtinuta starea curenta pentru care urmeaza a se actualiza starea, atentie trebuie sa fie diferita de null.
        if(State.GetState() != null)
        {
            ///Actualizez starea curenta a jocului daca exista.
            State.GetState().Draw(g);
        }
        /// end operatie de desenare

        /// Se afiseaza pe ecran
        bs.show();

        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }

    /*! \fn public int GetWidth()
        \brief Returneaza latimea ferestrei
     */
    public int GetWidth()
    {
        return wnd.GetWndWidth();
    }

    /*! \fn public int GetHeight()
        \brief Returneaza inaltimea ferestrei
     */
    public int GetHeight()
    {
        return wnd.GetWndHeight();
    }

    /*! \fn public KeyManager GetKeyManager()
        \brief Returneaza obiectul care gestioneaza tastatura.
     */
    public KeyManager GetKeyManager()
    {
        return keyManager;
    }

    /*! \fn public MouseManager GetMouseManager()
        \brief Returneaza obiectul care gestioneaza mouse ul.
     */
    public MouseManager GetMouseManager()
    {
        return mouseManager;
    }

    /*! \fn  public State getPlayState()
            \brief functia returneaza playstate , folosit in alte clase.
            Il folosesc atunci cand vreau sa initializez starea initiala a jocului
         */
    public State getPlayState()
    {
        return playState;
    }
    /*! \fn  public State getNivel1State()
        \brief Returneaza Nivel1State  foarte util in alte clase
     */
    public State getNivel1State()
    {
        return nivel1State;
    }

    /*! \fn  public State getNivel2State()
          \brief Returneaza Nivel2State  foarte util in alte clase
       */
    public State getNivel2State()
    {
        return nivel2State;
    }

    /*! \fn public State getMenuState()
            \brief Metoda returneaza MenuState, util cand vrem sa revenim la el din alte state uri
         */
    public State getMenuState()
    {
        return menuState;
    }
    /*! \fn public State getSettingsState()
            \brief Metoda returneaza SettingsState, util in alte clase
         */
    public State getSettingsState()
    {
        return settingsState;
    }
    /*! \fn public State getPauseState()
        \brief Returneaza pauseState folosit in nivelurile mele.
     */
    public State getPauseState()
    {
        return pauseState;
    }

    /*! \fn public State getGameOverState()
        \brief Returneaza GameOverState folosit in nivelurile mele.
     */
    public State getGameOverState()
    {
        return GameOverState;
    }

    /*! \fn public State getEndState()
     \brief Returneaza EndState folosit in nivelurile mele.
  */
    public State getEndState()
    {
        return endState;
    }
    /*! \fn public State getDatabase()
        \brief Metoda returneaza databese ul  folosit pt a incarca sau schimba setari.
     */
    public database getDatabase() { return database; }




}

