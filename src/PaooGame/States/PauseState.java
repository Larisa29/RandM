package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import java.awt.*;
import java.sql.SQLException;
/*! \class PauseState extends State
    \brief Completeaza meniul jocului oferind posibilitatea de a opri jocul la un moment dat.
 */
public class PauseState extends PlayState
{
    /*! \fn public PauseState(RefLinks refLink)
     \brief Constructorul clasei.
     \param refLink o clasa folosita pentru a accesa variate elemente ale jocului in mod rapid.
  */
    public PauseState(RefLinks reflink)
    {
        super(reflink);
    }
    /*! \fn   public void Update()
             In functie de butonul selectat, starea jocului va fi actualizata.
      */
    @Override
    public void Update()
    {
        ///daca sunt cu coordonata X determinata prin incercari in zona butonului yes/no actionez corespunzator
        if(refLink.GetMouseManager().getMouseY()>=393 && refLink.GetMouseManager().getMouseY()<=447)
        {
            ///Apasand pe butonul NO voi continua jocul pornind de la starea anterioara.
            if (refLink.GetMouseManager().getMouseX() >= 636 && refLink.GetMouseManager().getMouseX() <= 763)
            {
                if (refLink.GetMouseManager().click_stanga())
                {
                    State.SetState(State.GetPreviousState());
                }
            }

            ///Apasand pe butonul YES trebuie sa incep un joc nou , cu obiectele reinitializate
            if(refLink.GetMouseManager().getMouseX() >= 392 && refLink.GetMouseManager().getMouseX() <= 580)
            {
                if (refLink.GetMouseManager().click_stanga())
                {
                    ///creez harta de la inceput pentru a putea incepe un joc nou
                    System.out.println("AM AJUNS PE SETARILE DE LA NEW GAME dupa ce am apasat pause");
                    Nivel1State.nivel1_map.LoadWorld(1);
                    Nivel1State.nivel1_map.SetObjGasiste();
                    Nivel2State.nivel2_map.LoadWorld(2);
                    Nivel2State.nivel2_map.SetObjGasiste();
                    Nivel1State.nivel1_map.setItemsManager(Nivel1State.nivel1_map.alege_itemsManager(1));
                    Nivel2State.nivel2_map.setItemsManager(Nivel2State.nivel2_map.alege_itemsManager(2));
                    Nivel1State.timp=40;
                    Nivel2State.timp=30;
                    Assets.clip_joc.stop();
                    //Nivel1State.nivel1_map=new Map(refLink,1);

                    //refLink.SetMap(new Map(refLink,1));
                    /*Nivel2State.nivel2_map=new Map(refLink,2);
                    refLink.SetMap(new Map(refLink,2));*/
                    State.SetState(refLink.GetGame().getMenuState());

                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Eroare thread in PauseState");
                    }

                    System.out.println("ar trebui sa mi apara meniul");
                }
            }

        }

        ///Apasand pe butonul save salvez starea hartii si a obiectelor din joc pt a putea sa continui cu ele in aceasta forma
        if(refLink.GetMouseManager().getMouseY()>=313 && refLink.GetMouseManager().getMouseY()<=442)
        {
            if(refLink.GetMouseManager().getMouseX() >= 525 && refLink.GetMouseManager().getMouseX() <= 688)
            {
                if (refLink.GetMouseManager().click_stanga())
                {
                    System.out.println("sunt pe save ");
                    //save_game();

                }
            }
        }



    }
    /*! \fn public void Draw(Graphics g)
      \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

      \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
   */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.pause,0,0,refLink.GetWidth(),refLink.GetHeight(),null);
    }






}
