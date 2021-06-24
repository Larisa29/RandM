package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import PaooGame.States.State;

import java.awt.*;


/*! \class public abstract class Character extends Item
    \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

    Notiunea este definita doar de viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */
public abstract class Character extends Item
{
    //public static final int DEFAULT_LIFE            = 10;   /*!< Valoarea implicita a vietii unui caracter.*/
    public static final float DEFAULT_SPEED         = 2.0f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH  = 48;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 48;   /*!< Inaltimea implicita a imaginii caracterului.*/

    protected float speed;  /*!< Retine viteza de deplasare caracterului.*/
    protected float xMove;  /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a caracterului pe axa Y.*/



    /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Character

        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
        \param x Pozitia de start pa axa X a caracterului.
        \param y Pozitia de start pa axa Y a caracterului.
        \param width Latimea imaginii caracterului.
        \param height Inaltimea imaginii caracterului.
     */
    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
        ///Apel constructor la clasei de baza
        super(refLink, x,y, width, height);
        ///Seteaza pe valorile implicite pentru viata, viteza si distantele de deplasare
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
    }

    /*! \fn public void Move()
        \brief Modifica pozitia caracterului
     */
    public void Move()
    {
        ///Modifica pozitia caracterului pe axa X.

        if(am_coliziune(xMove,0f)==false)//verific coliiziunea cu punctl spre care ma deplasez
        {

            MoveX();
        }
        ///Modifica pozitia caracterului pe axa Y.

        if(am_coliziune(0f,yMove)==false)

        {
            //System.out.println("collide ");

            MoveY();

        }

    }

    /*! \fn public void MoveX()
        \brief Modifica pozitia caracterului pe axa X.
     */
    public void MoveX()
    {
        ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa X.
        ///deplasare la dreapta sau stanga
        if(xMove>0 && fara_coliziune_dreapta())
        {
                x += xMove;

        }
        else if (xMove<0 && fara_coliziune_stanga())
        {
            x += xMove;
        }
    }
    /*! \fn public void MoveY()
          \brief Modifica pozitia caracterului pe axa Y.
       */
    public void MoveY()
    {
        ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa Y.
        ///deplasare la sus sau jos
         if(yMove>0 && fara_coliziune_jos()==true)//deplasare in jos
        {
            y += yMove;

        }
        else if (yMove<0 && fara_coliziune_sus()==true)//deplasare in sus
        {
            y += yMove;

        }
    }
    /*! \fn public boolean fara_coliziune_dreapta()
             \brief Metoda stabileste daca exista coliziune la dreapta , pt care returneaza true, altfel false.Totodata, tine cont de tipul de dala cu
             care intra personajul in contact. Fiecare dala mai are atribuit inca un cod suplimentar pt a facilita decizia de actiune in functie de dala de contact
             Tin cont si de soliditatea fiecarui tile . Pt fiecare dala stabilesc tipul de interactiune particular
          */
    public boolean fara_coliziune_dreapta()
    {
        ///tempx->coordonata x a dalei spre care ma indrept, ramane aceeasi si pt coltul de sus si cel de jos
        int temp_x=(int) (this.x+this.xMove+this.normalBounds.x+this.normalBounds.width)/DEFAULT_CREATURE_WIDTH;

        //colt dreapta sus, daca !isSolid()=>pot sa ma deplaez
        boolean cd_s=refLink.GetMap().GetTile(temp_x, (int)(this.y + this.normalBounds.width)/DEFAULT_CREATURE_WIDTH).IsSolid();//ar trb sa pun TILE_WIDTH daca tot o am, dar inca nu stiu de ce da eroare cand incerc
       //colt drepata jos
        boolean cd_j=refLink.GetMap().GetTile(temp_x, (int)(this.y + this.normalBounds.y+normalBounds.height)/DEFAULT_CREATURE_WIDTH).IsSolid();
        //verific daca colturile din dreapta sus se intersecteaza cu ceva, daca se intersecteaza cel putin unul din ele=>coliziune
        if((cd_s || cd_j) && (refLink.GetMap().GetTile(temp_x, (int)(this.y + this.normalBounds.y+normalBounds.height)/DEFAULT_CREATURE_WIDTH).getFlag()==0))//am intersectie cu coltul  drept-sus sau drept-jos?=> coliziune in dreapta
        {
            //System.out.println(cd_s+" "+cd_j);
            System.out.println("coliziune dreapta\n");
            return false;
        }
        else if((cd_j==false ||cd_s==false) && (refLink.GetMap().GetTile(temp_x, (int)(this.y + this.normalBounds.y+normalBounds.height)/DEFAULT_CREATURE_WIDTH).getFlag()==1) )
        {
            System.out.println("coliziune cu licoare sau bec \n"+this.y+" "+(this.normalBounds.y+normalBounds.height)/DEFAULT_CREATURE_WIDTH);
            refLink.GetMap().setTile(temp_x, (int)(this.y + this.normalBounds.y+normalBounds.height)/DEFAULT_CREATURE_WIDTH);
            return true;
        }
        else if((cd_j || cd_s) && (refLink.GetMap().GetTile(temp_x, (int)(this.y + this.normalBounds.y+normalBounds.height)/DEFAULT_CREATURE_WIDTH).getFlag()==2) && (!refLink.GetKeyManager().invizibil && !refLink.GetKeyManager().ENTER))
        {
            //daca am un solid cu cod asociat 2(adica e butoi sau butoi verde
            System.out.println("am coleziune cu butoi");
            //flag_butoi=true;
            return false;
        }
        return true;
    }
    /*! \fn public boolean fara_coliziune_stanga()
                \brief Metoda stabileste daca exista coliziune la stanga , pt care returneaza true, altfel false.Totodata, tine cont de tipul de dala cu
                care intra personajul in contact. Fiecare dala mai are atribuit inca un cod suplimentar pt a facilita decizia de actiune in functie de dala de contact
                Tin cont si de soliditatea fiecarui tile. Pt fiecare dala stabilesc tipul de interactiune particular
             */
    public boolean fara_coliziune_stanga()
    {
        ///tempx->coordonata x a dalei spre care ma indrept
        int temp_x=(int) (this.x+this.xMove+this.normalBounds.x)/DEFAULT_CREATURE_WIDTH;
        ///calcul colt stanga sus
        boolean cs_s=refLink.GetMap().GetTile(temp_x, (int)(this.y + this.normalBounds.width)/DEFAULT_CREATURE_WIDTH).IsSolid();//ar trb sa pun TILE_WIDTH daca tot o am, dar inca nu stiu de ce da eroare cand incerc
        //calcul colt stanga jos
        boolean cs_j=refLink.GetMap().GetTile(temp_x, (int)(this.y + this.normalBounds.y+normalBounds.height)/DEFAULT_CREATURE_WIDTH).IsSolid();
        ///verific daca colturile din stanga sus se intersecteaza cu ceva, daca se intersecteaza cel putin unul din ele=>coliziune
        if((cs_s || cs_j) && (refLink.GetMap().GetTile(temp_x, (int)(this.y + this.normalBounds.y+normalBounds.height)/DEFAULT_CREATURE_WIDTH).getFlag()==0))//am intersectie cu coltul  stang-sus sau stang-jos?=> coliziune in dreapta
        {
            System.out.println("coliziune stanga\n");
            return false;
        }
        ///verific daca am coliziune cu licoarea sau becul si daca am atunci treb sa le fac sa dispara folosind functia setTile()
        else if((cs_s==false ||cs_j==false) && (refLink.GetMap().GetTile(temp_x, (int)(this.y + this.normalBounds.y+normalBounds.height)/DEFAULT_CREATURE_WIDTH).getFlag()==1))
        {
            System.out.println("coliziune cu licoare sau bec \n");
            refLink.GetMap().setTile(temp_x, (int)(this.y + this.normalBounds.y+normalBounds.height)/DEFAULT_CREATURE_WIDTH);

            return true;
        }//conditie pe dos pt ca returnez false
        else if((cs_s || cs_j) && (refLink.GetMap().GetTile(temp_x, (int)(this.y + this.normalBounds.y+normalBounds.height)/DEFAULT_CREATURE_WIDTH).getFlag()==2) && (!refLink.GetKeyManager().invizibil && !refLink.GetKeyManager().ENTER))
        {
            ///verific daca am un solid cu cod asociat 2(adica e butoi sau butoi verde) si actionez specific
            System.out.println("am coleziune cu butoi");
            //flag_butoi=true;
            return false;
        }

            return true;
    }
    /*! \fn public boolean fara_coliziune_jos()
                    \brief Metoda stabileste daca exista coliziune in jos , pt care returneaza true, altfel false.Totodata, tine cont de tipul de dala cu
                    care intra personajul in contact. Fiecare dala mai are atribuit inca un cod suplimentar pt a facilita decizia de actiune in functie de dala de contact
                    Tin cont si de soliditatea fiecarui tile . Pt fiecare dala stabilesc tipul de interactiune particular
                 */
    public boolean fara_coliziune_jos()
    {
        ///tempy->coordonata y a dalei spre care ma indrept
        int temp_y=(int)(this.y +this.yMove +this.normalBounds.y+this.normalBounds.height)/DEFAULT_CREATURE_HEIGHT;
        ///calcul colt stanga jos
        //daca unde urmeaza sa ma deplasez am suprafata solida=>coliziune
        boolean cs_j=refLink.GetMap().GetTile((int)(this.x + this.normalBounds.x)/DEFAULT_CREATURE_WIDTH,temp_y).IsSolid();//ar trb sa pun TILE_WIDTH daca tot o am, dar inca nu stiu de ce da eroare cand incerc
        ///calcul colt drepata jos
        boolean cd_j=refLink.GetMap().GetTile((int)(this.x + this.normalBounds.x+this.normalBounds.width)/DEFAULT_CREATURE_WIDTH,temp_y).IsSolid();
        if((cs_j || cd_j) && (refLink.GetMap().GetTile((int)(this.x + this.normalBounds.x+this.normalBounds.width)/DEFAULT_CREATURE_WIDTH,temp_y).getFlag()==0))//am intersectie cu coltul  stang-jos sau drept-jos?=> coliziune jos
        {
            System.out.println("coliziune jos \n");
            return false;
        }
        else if((cd_j==false ||cs_j==false)&& (refLink.GetMap().GetTile((int)(this.x + this.normalBounds.x+this.normalBounds.width)/DEFAULT_CREATURE_WIDTH,temp_y).getFlag()==1))
        {
            System.out.println("coliziune cu licoare sau bec \n");
            refLink.GetMap().setTile((int)(this.x + this.normalBounds.x+this.normalBounds.width)/DEFAULT_CREATURE_WIDTH,temp_y);
            return true;
        }
        else if((cd_j || cs_j) && (refLink.GetMap().GetTile((int)(this.x + this.normalBounds.x+this.normalBounds.width)/DEFAULT_CREATURE_WIDTH,temp_y).getFlag()==2) && (!refLink.GetKeyManager().invizibil && !refLink.GetKeyManager().ENTER))
        {
            ///daca am un solid cu cod asociat 2(adica e butoi sau butoi verde) stabilesc tipul de interactiune particular
            System.out.println("am coleziune cu butoi");
            return false;
        }
        return true;
    }
    /*! \fn public boolean fara_coliziune_sus()
                        \brief Metoda stabileste daca exista coliziune in sus , pt care returneaza true, altfel false.Totodata, tine cont de tipul de dala cu
                        care intra personajul in contact. Fiecare dala mai are atribuit inca un cod suplimentar pt a facilita decizia de actiune in functie de dala de contact
                        Tin cont si de soliditatea fiecarui tile . Pt fiecare dala stabilesc tipul de interactiune particular
                     */
    public boolean fara_coliziune_sus()
    {
        ///tempy->coordonata y a dalei spre care ma indrept
        int temp_y=(int)(this.y +this.yMove +this.normalBounds.y)/DEFAULT_CREATURE_HEIGHT;//y pt partea de sus
        ///calcul colt stanga sus
        boolean cs_s=refLink.GetMap().GetTile((int)(this.x + this.normalBounds.x)/DEFAULT_CREATURE_WIDTH,temp_y).IsSolid();//ar trb sa pun TILE_WIDTH daca tot o am, dar inca nu stiu de ce da eroare cand incerc
        ///calcul colt drepata sus
        boolean cd_s=refLink.GetMap().GetTile((int)(this.x + this.normalBounds.x+this.normalBounds.width)/DEFAULT_CREATURE_WIDTH,temp_y).IsSolid();
        if((cs_s || cd_s) && (refLink.GetMap().GetTile((int)(this.x + this.normalBounds.x+this.normalBounds.width)/DEFAULT_CREATURE_WIDTH,temp_y).getFlag()==0))//am intersectie cu coltul  stang-sus sau drept-sus?=> coliziune sus, poate am intersectie doar cu un singur colt
        {
            System.out.println("coliziune sus\n");
            return false;
        }
        //false face referire  la faptul ca dala NU ar fi Solid()
        else if((cs_s==false ||cd_s==false)&& (refLink.GetMap().GetTile((int)(this.x + this.normalBounds.x+this.normalBounds.width)/DEFAULT_CREATURE_WIDTH,temp_y).getFlag())==1)
        {
            System.out.println("coliziune cu licoare sau bec \n");
            refLink.GetMap().setTile((int)(this.x + this.normalBounds.x+this.normalBounds.width)/DEFAULT_CREATURE_WIDTH,temp_y);
            return true;
        }
        else if((cd_s || cs_s) && (refLink.GetMap().GetTile((int)(this.x + this.normalBounds.x+this.normalBounds.width)/DEFAULT_CREATURE_WIDTH,temp_y).getFlag()==2) && (!refLink.GetKeyManager().invizibil && !refLink.GetKeyManager().ENTER))
        {
            ///daca am un solid cu cod asociat 2(adica e butoi sau butoi verde) actionez particular
            System.out.println("am coleziune cu butoi");
            return false;
        }
        return true;

    }






    /*! \fn public int GetLife()
        \brief Returneaza viata caracterului.
     */
    public int GetLife()
    {
        return life;
    }

    /*! \fn public int GetSpeed()
        \brief Returneaza viteza caracterului.
     */
    public float GetSpeed()
    {
        return speed;
    }

    /*! \fn public void SetLife(int life)
        \brief Seteaza viata caracterului.
     */
    public void SetLife(int life)
    {
        this.life = life;
    }

    /*! \fn public void SetSpeed(float speed)
        \brief
     */
    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    /*! \fn public float GetXMove()
        \brief Returneaza distanta in pixeli pe axa X cu care este actualizata pozitia caracterului.
     */
    public float GetXMove()
    {
        return xMove;
    }

    /*! \fn public float GetYMove()
        \brief Returneaza distanta in pixeli pe axa Y cu care este actualizata pozitia caracterului.
     */
    public float GetYMove()
    {
        return yMove;
    }

    /*! \fn public void SetXMove(float xMove)
        \brief Seteaza distanta in pixeli pe axa X cu care va fi actualizata pozitia caracterului.
     */
    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }

    /*! \fn public void SetYMove(float yMove)
        \brief Seteaza distanta in pixeli pe axa Y cu care va fi actualizata pozitia caracterului.
     */
    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }




    /*! \fn public void invizibil(Graphics g)
            \brief Metoda exemplifica starea de 'invizibil' a personajului . Aceasta ii permite caracterului sa treaca prin anumite obstacole
         */
    public void invizibil(Graphics g)
    {
        g.drawImage(Assets.invizibil, (int)x, (int)y, width, height, null);
    }




}


