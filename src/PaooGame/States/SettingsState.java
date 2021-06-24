package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Graphics.SunetLoader;

import java.awt.*;
import java.sql.SQLException;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State
{
    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public SettingsState(RefLinks refLink)
    {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update() {
        if (!Assets.clip_menu.isRunning()) { ///Verific daca Clipul audio este pornit.In caz contrar, il pornesc.
            Assets.clip_menu.setFramePosition(0);  /// il setez sa inceapa de la frame-ul 0 , incepe din nou in momentul in care se termina prima data
            Assets.clip_menu.start(); /// Folosind start pornesc clipul audio
        }

        ///Apas pe - sau + asociate sunetului din meniu pot schimba volumul ecestuia. Volumul va fi setat din baza de date.
        if (refLink.GetMouseManager().getMouseY() >= 380 && refLink.GetMouseManager().getMouseY() <= 435) {
            try {
                if (refLink.GetMouseManager().getMouseX() >= 366 && refLink.GetMouseManager().getMouseX() <= 428) {
                    if (refLink.GetMouseManager().click_stanga()) {
                        if (refLink.GetDatabase().getMenuVolume() > 0)
                            refLink.GetDatabase().UpdateMenuVolume(refLink.GetDatabase().getMenuVolume() - 10);
                        SunetLoader.setVolume(Assets.clip_menu, refLink.GetDatabase().getMenuVolume());
                        Thread.sleep(100); //daca nu as folosi acestea , in baza mea de date nu ar astepta sa se schimbe cu 10 si atat pe rand
                    }
                }

                if (refLink.GetMouseManager().getMouseX() >= 781 && refLink.GetMouseManager().getMouseX() <= 840) {
                    //100 suporta maxim :error altfel:Requested value 8.000002 exceeds allowable maximum value 6.0206.
                    if (refLink.GetDatabase().getMenuVolume() < 100)
                        refLink.GetDatabase().UpdateMenuVolume(refLink.GetDatabase().getMenuVolume() + 10);
                    SunetLoader.setVolume(Assets.clip_menu, refLink.GetDatabase().getMenuVolume());
                    Thread.sleep(100);
                }
            } catch (SQLException e) {
                System.err.println("Eroare la incarcare din baza de date in SettingsState");
            } catch (InterruptedException e) {
                System.err.println("Eroare la intreruperea thread-ului in SettingsState.");
            }
        }


        ///Apasand pe - sau + asociate sunetului din timpul jocului, pot schimba volumul ecestuia. Volumul va fi setat din baza de date.
        if (refLink.GetMouseManager().getMouseY() >= 460 && refLink.GetMouseManager().getMouseY() <= 520) {
            try {
                //apas pe - la joc
                if (refLink.GetMouseManager().getMouseX() >= 365 && refLink.GetMouseManager().getMouseX() <= 427) {
                    if (refLink.GetMouseManager().click_stanga()) {
                        if (refLink.GetDatabase().getJocVolume() > 0)
                            refLink.GetDatabase().UpdateJocVolume(refLink.GetDatabase().getJocVolume() - 10);
                        SunetLoader.setVolume(Assets.clip_joc, refLink.GetDatabase().getJocVolume());
                        Thread.sleep(100); //daca nu as folosi acestea , in baza mea de date nu ar astepta sa se schimbe cu 10 si atat pe rand
                    }
                }
                //apas pe + la joc
                if (refLink.GetMouseManager().getMouseX() >= 780 && refLink.GetMouseManager().getMouseX() <= 840) {
                    //100 suporta maxim :error altfel:Requested value 8.000002 exceeds allowable maximum value 6.0206.
                    if (refLink.GetDatabase().getJocVolume() < 100)
                        refLink.GetDatabase().UpdateJocVolume(refLink.GetDatabase().getJocVolume() + 10);
                    SunetLoader.setVolume(Assets.clip_joc, refLink.GetDatabase().getJocVolume());
                    Thread.sleep(100);
                }

            } catch (SQLException e) {
                System.err.println("Eroare la incarcare din baza de date in SettingsState");
            } catch (InterruptedException e) {
                System.err.println("Eroare la intreruperea thread-ului in SettingsState.");
            }
        }

        ///Apasand pe butonul back to menu se revine in meniul principal
        if (refLink.GetMouseManager().getMouseY() >= 302 && refLink.GetMouseManager().getMouseY() <= 429) {
            if (refLink.GetMouseManager().getMouseX() >= 449 && refLink.GetMouseManager().getMouseX() <= 748) {
                if (refLink.GetMouseManager().click_stanga()) {
                    State.SetState(refLink.GetGame().getMenuState());
                }
            }
        }


    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.settings, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);

    }
}
