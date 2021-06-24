package PaooGame.States;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
/*! \class GameOverState extends PlayState
    \brief Implementeaza starea in care jocul a fost pierdut de jucatori
 */
public class GameOverState extends PlayState
{
    /*! \fn     public GameOverState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.
         \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public GameOverState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
    }
    /*! \fn public void Update()
            \brief Actualizeaza parametrii principali ai jocului
         */
    @Override
    public void Update()
    {
        if(!Assets.clip_gameover.isRunning())
        { /// Verific daca Clipul audio este pornit.In caz contrar, il pornesc.
            Assets.clip_gameover.setFramePosition(0);   /// il setez sa inceapa de la frame-ul 0 , incepe din nou in momentul in care se termina prima data
            Assets.clip_gameover.start(); /// Folosind start pornesc clipul audio
        }
        ///Dupa un joc pierdut, se reintra in meniul principal.
        if(refLink.GetMouseManager().getMouseX()>=484 && refLink.GetMouseManager().getMouseX()<=724)//optiune de intrare din nou in menu
        {
            if(refLink.GetMouseManager().getMouseY()>=335 && refLink.GetMouseManager().getMouseY()<=387)
            {
                if(refLink.GetMouseManager().click_stanga())
                {
                    Nivel1State.nivel1_map.LoadWorld(1);
                    Nivel1State.nivel1_map.SetObjGasiste();
                    Nivel2State.nivel2_map.LoadWorld(2);
                    Nivel2State.nivel2_map.SetObjGasiste();
                    Nivel1State.nivel1_map.setItemsManager(Nivel1State.nivel1_map.alege_itemsManager(1));
                    Nivel2State.nivel2_map.setItemsManager(Nivel2State.nivel2_map.alege_itemsManager(2));
                    Nivel1State.timp=40;
                    Nivel2State.timp=30;
                    Assets.clip_gameover.stop();

                    State.SetState(refLink.GetGame().getMenuState());
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Eroare thread in PauseState");
                    }
                }


            }

        }
    }
    /*! \fn public void Draw(Graphics g)
             \brief Deseneaza (randeaza) pe ecran.

             \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
          */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.gameover,0,0,refLink.GetWidth(),refLink.GetHeight(),null);//fundal game over

    }
}
