package PaooGame.States;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class EndState extends PlayState
    \brief Implementeaza starea finnala a jocului.
 */
public class EndState extends PlayState
{
    /*! \fn public EndState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.
         \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public EndState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza ceea ce se intampla in joc, acum fiind ultima stare posibila
     */
    @Override
    public void Update() {
        if (!Assets.clip_menu.isRunning()) { // daca Clipul audio nu este deja pornit
            Assets.clip_menu.setFramePosition(0);  // il setam sa inceapa de la frame-ul 0 , incepe din nou in momentul in care se termina prima data
            Assets.clip_menu.start(); // pornim clipul audio
        }
        if (refLink.GetMouseManager().getMouseX() >= 449 && refLink.GetMouseManager().getMouseX() <= 747)//daca sunt cu cursorul pe back to menu
        {
            if (refLink.GetMouseManager().getMouseY() >= 354 && refLink.GetMouseManager().getMouseY() <= 409)//daca sunt pe butonul de new game, va aparea o sageata de 70x55 in dreapta la coordonate calculate prin incercari, coordonate printate la consola
            {
                if (refLink.GetMouseManager().click_stanga()) {
                    Nivel1State.nivel1_map.LoadWorld(1);
                    Nivel1State.nivel1_map.SetObjGasiste();
                    Nivel2State.nivel2_map.LoadWorld(2);
                    Nivel2State.nivel2_map.SetObjGasiste();
                    Nivel1State.nivel1_map.setItemsManager(Nivel1State.nivel1_map.alege_itemsManager(1));
                    Nivel2State.nivel2_map.setItemsManager(Nivel2State.nivel2_map.alege_itemsManager(2));
                    Nivel1State.timp=40;
                    Nivel2State.timp=30;
                    State.SetState(refLink.GetGame().getMenuState());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Eroare thread in PauseState->Update");
                    }

                    System.out.println("ar trebui sa mi apara mneiul");
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
        g.drawImage(Assets.win,0,0,refLink.GetWidth(),refLink.GetHeight(),null);//fundal game over

    }
}
