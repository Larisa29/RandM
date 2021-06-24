package PaooGame.States;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.SunetLoader;
import PaooGame.Items.*;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.Items.ItemsManager;

import java.awt.*;
/*! \class Nivel1State extends PlayState
    \brief Implementeaza nivelul 1.
 */
public class Nivel1State extends PlayState
{
    public static Map nivel1_map;
    public static int timp;
    /*! \fn public Nivel1State(RefLinks refLink)
                \brief Constructorul de initializare al clasei.Initializez parametrii asociati clasei

                \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
             */
    public Nivel1State(RefLinks refLink)
    {
        super(refLink);
        ///Construieste harta jocului
        nivel1_map=new Map(refLink,1);
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(nivel1_map);
        timp=40;
        System.out.println("construcor nivel 1");
    }

    /*! \fn public void Update()
        \brief Actualizeaza joc
     */
    @Override
    public void Update()
    {
        ///Pt personaje update-ul este inclus in map
        nivel1_map.Update();

        if(State.GetPreviousState()==refLink.GetGame().getMenuState())//daca abia a inceput jocul
        {
            if(!Assets.clip_joc.isRunning())
                   { /// daca Clipul audio nu este deja pornit
                      Assets.clip_joc.setFramePosition(0);  /// il setam sa inceapa de la frame-ul 0 , incepe din nou in momentul in care se termina prima data
                        Assets.clip_joc.start(); /// Folosind start pornim clipul audio
                   }
        }

      ///Daca scorul necesar este atins, se face trecerea la nivelul 2.
        if(nivel1_map.getNr()==24)
        {
            //Assets.clip_joc.stop();
            System.out.println("treci la nivelul urmator");
            State.SetState(refLink.GetGame().getNivel2State());


            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                System.err.println("Eroare thread in PauseState");
            }

            //System.out.println("asteptam putin");
            refLink.SetMap(Nivel2State.nivel2_map);
        }

        ///tasta ESC apasata ma conduce la starea de Pause a jocului
        if(refLink.GetKeyManager().ESC)
        {
            System.out.println("apas pe esc");

            State.SetState(refLink.GetGame().getPauseState());
        }


    }
    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {

        nivel1_map.Draw(g);//se ocupa si de printarea itemilor
        g.setColor(Color.blue);
        g.setFont(new Font("Arial Black", Font.PLAIN, 25));
        ///Ma folosesc de functia drawString din clasa Graphics pt a afisa cate obiecte mai sunt de gasit(scorul).
        ///Metoda desenează textul dat de șirul specificat, utilizând fontul și culoarea curentă a acestui context grafic la o pozitie calculata experimental
        g.drawString("Objects:",refLink.GetWidth()-300,refLink.GetHeight()-730);  //latimea/inaltimea ferestrei minus...
        g.drawString(Integer.toString(24-nivel1_map.getNr()),refLink.GetWidth()-150,refLink.GetHeight()-730);

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
            System.out.println("a mai trecut o secunda");
            timp-=1;
        }

        ///Daca timpul din joc s-a scrus fara sa ating scorul predefinit , atunci state-ul meu devine GameOver
        if(timp<0 && nivel1_map.getNr()!=24)
        {
            Assets.clip_joc.stop();
             State.SetState(refLink.GetGame().getGameOverState());
        }


    }


    @Override
    public void de_la_inceput()
    {
        super.de_la_inceput();

        //refLink.SetMap(nivel1_map);
        timp=16;
        //System.out.println("timp curent"+timp);
    }


}
