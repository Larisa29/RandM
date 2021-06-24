package PaooGame.States;

import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.SunetLoader;

import java.awt.*;
import java.sql.SQLException;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{
    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    public MenuState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        try {
            SunetLoader.setVolume(Assets.clip_menu, refLink.GetDatabase().getMenuVolume());
        }
        catch (SQLException e)
        {
            System.err.println("Eroare MenuState. Verfica optiunile de setare a sunetului din baza de date");
        }

    }
    /*! \fn public void Update()
            \brief Actualizeaza starea curenta a meniului.
         */
    @Override
    public void Update() {
            if(!Assets.clip_menu.isRunning())
            { /// Verific daca Clipul audio este pornit.In caz contrar, il pornesc.
            Assets.clip_menu.setFramePosition(0);  /// il setez sa inceapa de la frame-ul 0 , incepe din nou in momentul in care se termina prima data
            Assets.clip_menu.start(); /// Folosind start pornesc clipul audio
            }
        ///In dreptul fiecarei optiuni din meniu va aparea o sageata de 70x55 in dreapta la coordonate calculate prin incercari, coordonate printate la consola pt a pune in evidenta optiunea selectata.
        if(refLink.GetMouseManager().getMouseX()>=482 && refLink.GetMouseManager().getMouseX()<=723)
        {
            ///Daca sunt cu cursorul in zona meniului si apas pe new game, state-ul meu devine dupa cum se selecteaza din baza de date.
            if(refLink.GetMouseManager().getMouseY()>=232 && refLink.GetMouseManager().getMouseY()<=290)
            {
                if(refLink.GetMouseManager().click_stanga() )
                {
                    Assets.clip_menu.stop();
                    try
                    {
                        if(refLink.GetDatabase().getNivel1Activ() == 1)
                        {
                            refLink.SetMap(Nivel1State.nivel1_map);
                            State.SetState(refLink.GetGame().getNivel1State());
                            Thread.sleep(500);

                        }
                        if(refLink.GetDatabase().getNivel2Activ() == 1)
                        {
                            refLink.SetMap(Nivel2State.nivel2_map);
                            State.SetState(refLink.GetGame().getNivel2State());
                            Thread.sleep(500);

                        }
                    }
                    catch (SQLException e )
                    {
                        System.err.println("Eroare in MenuState.Verifica Update->Baza de date.");
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Eroare in MenuState.Verifica Update->Thread.");
                    }
                }
            }
            ///Daca sunt cu cursorul in zona Settings , state-ul meu se actualizeaza cu cel corespunzator
            if(refLink.GetMouseManager().getMouseY()>=395 && refLink.GetMouseManager().getMouseY()<=443)
            {
                if(refLink.GetMouseManager().click_stanga())
                {
                    State.SetState(refLink.GetGame().getSettingsState());
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Eroare thread.Verifica PauseState->Update");
                    }

                    System.out.println("ar trebui sa mi apara meniul");
                }
            }
            ///Daca sunt cu cursorul pe exit, execut iesirea din program.
            if(refLink.GetMouseManager().getMouseY()>=467 && refLink.GetMouseManager().getMouseY()<=523)
            {
                if(refLink.GetMouseManager().click_stanga() && (State.GetPreviousState()!=refLink.GetGame().getMenuState()))//sunt in meniul principal pe exit(neaparat in meniul principal, nu in settings)
                {
                        System.exit(0);
                }
            }
        }

    }
    /*! \fn public void Draw(Graphics g)
           \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

           \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
        */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.fundal_menu,0,0,refLink.GetWidth(),refLink.GetHeight(),null);
        if(refLink.GetMouseManager().getMouseX()>=482 && refLink.GetMouseManager().getMouseX()<=723)//daca sunt cu cursorul in zona meniului
        {
            if(refLink.GetMouseManager().getMouseY()>=232 && refLink.GetMouseManager().getMouseY()<=288)//daca sunt pe butonul de new game, va aparea o sageata de 70x55 in dreapta la coordonate calculate prin incercari, coordonate printate la consola
            {
                g.drawImage(Assets.select,763,230,70,55,null);
            }
            if(refLink.GetMouseManager().getMouseY()>=307 && refLink.GetMouseManager().getMouseY()<=366)//daca sunt pe butonul de load, va aparea o sageata de 70x55 in dreapta la coordonate calculate prin incercari
            {
                g.drawImage(Assets.select,763,318,70,55,null);
            }
            if(refLink.GetMouseManager().getMouseY()>=388 && refLink.GetMouseManager().getMouseY()<=442)//daca sunt pe butonul de settings, va aparea o sageata de 70x55 in dreapta la coordonate calculate prin incercari
            {
                g.drawImage(Assets.select,763,394,70,55,null);
            }

            if(refLink.GetMouseManager().getMouseY()>=467 && refLink.GetMouseManager().getMouseY()<=526)//daca sunt pe butonul de exit, va aparea o sageata de 70x55 in dreapta la coordonate calculate prin incercari
            {
                g.drawImage(Assets.select,763,473,70,55,null);
            }


        }




    }





}
