package PaooGame.States;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.SunetLoader;
import PaooGame.Items.*;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.Items.ItemsManager;

import java.awt.*;
/*! \class Nivel2State extends PlayState
    \brief Implementeaza nivelul 2.
 */
public class Nivel2State extends PlayState
{
    public static Map nivel2_map;    /*!< Referinta catre harta curenta.*/
    private ItemsManager itemsManager;
    public static int timp;
    /*! \fn public Nivel2State(RefLinks refLink)
                   \brief Constructorul de initializare al clasei.Initializez parametrii asociati clasei

                   \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
                */
    public Nivel2State(RefLinks refLink)
    {
        super(refLink);
        ///Construieste harta jocului
        nivel2_map = new Map(refLink,2);
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(nivel2_map);
        timp=30;

    }
    /*! \fn public void Update()
       \brief Actualizeaza joc
    */
    @Override
    public void Update()
    {
        if(!Assets.clip_joc.isRunning())
        { /// daca Clipul audio nu este deja pornit
            Assets.clip_joc.setFramePosition(0);  /// il setam sa inceapa de la frame-ul 0 , incepe din nou in momentul in care se termina prima data
            Assets.clip_joc.start(); /// Folosind start pornesc clipul audio
        }
        ///pt personaje update-ul e in map
        nivel2_map.Update(); //pt personaje update-ul e in map
        /*if(nivel2_map.getNr()==5)
        {
            //refLink.SetMap(PlayState.map);
            //State.SetState(refLink.GetGame().getNivel1State());
            System.out.println("YOU WINN");
            State.SetState(refLink.GetGame().getMenuState());
            //sa fac o poza cu nivel 2 care sa apara 3 secunde cu you win! next level=>da at next level, else nu
        }*/


    }

    @Override
    public void Draw(Graphics g)
    {
        ///se ocupa de printarea hartii si a itemilor
        nivel2_map.Draw(g);
        g.setColor(Color.blue);
        g.setFont(new Font("Arial Black", Font.PLAIN, 25));
        ///Ma folosesc de functia drawString din clasa Graphics pt a afisa cate obiecte mai sunt de gasit(scorul).
        ///Metoda desenează textul dat de șirul specificat, utilizând fontul și culoarea curentă a acestui context grafic la o pozitie calculata experimental
        g.drawString("Objects:",refLink.GetWidth()-300,refLink.GetHeight()-730);  //latimea/inaltimea ferestrei minus...
        g.drawString(Integer.toString(40-nivel2_map.getNr()),refLink.GetWidth()-150,refLink.GetHeight()-730);//drawString e o metoda din clasa Graphics care imi afiseaza ca text ce ii dau eu la primul parametru

        g.drawString("00 :",refLink.GetWidth()-1100,refLink.GetHeight()-730);  //latimea/inaltimea ferestrei minus...
        ///Tratez situatia in care timpul meu ramas este <10 .Vreau sa mi afiseze secundele conform unui cronometru.
        if(timp<10)
        {
            g.drawString("0",refLink.GetWidth()-1050,refLink.GetHeight()-730);  //latimea/inaltimea ferestrei minus...

        }
        g.drawString(Integer.toString(timp),refLink.GetWidth()-1035,refLink.GetHeight()-730);  //latimea/inaltimea ferestrei minus...(era 1050 initial)

        ///verific trecerea unei secunde
        if(secondElapsed())
        {
            //System.out.println("a mai trecut o secunda");
            timp-=1;
        }

        ///tasta ESC apasata ma conduce la starea de Pause a jocului
        if(refLink.GetKeyManager().ESC)
        {
            System.out.println("apas pe esc");

            State.SetState(refLink.GetGame().getPauseState());
        }


        ///Daca timpul din joc s-a scrus fara sa ating scorul predefinit , atunci state-ul meu devine GameOver
        if(timp<0 && nivel2_map.getNr()!=40) {
            Assets.clip_joc.stop();
            State.SetState(refLink.GetGame().getGameOverState());

        }

        ///Terminand nivelul 2 cu succes, se ajunge la EndState.
        if(timp>0 && nivel2_map.getNr()==40)
        {
            Assets.clip_joc.stop();
            State.SetState(refLink.GetGame().getEndState());

        }
    }

    }
