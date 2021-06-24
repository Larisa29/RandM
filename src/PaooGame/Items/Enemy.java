package PaooGame.Items;
import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.States.State;

import java.awt.*;

/*! \class public class Enemy extends Character
    \brief  Clasa implementeaza notiunea de inamic prin extindere pt caracter.
    Modul de calcul al deplasarii este diferit fata de cei 2 eroi ai jocului.
    Clasa aduce in plus anumite puncte maxime si minime care pot fi atinse de inamici in timpul deplasarii.
 */
//public abstract class Enemy extends Item
public class Enemy extends Character

{
    public static final int DEFAULT_LIFE            = 3;   /*!< Valoarea implicita a vietii unui caracter.*/
    public static final float DEFAULT_SPEED         = 1.0f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH  = 48;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 48;   /*!< Inaltimea implicita a imaginii caracterului.*/

    protected int life;     /*!< Retine viata caracterului.*/
    protected float speed;  /*!< Retine viteza de deplasare caracterului.*/
    protected float xMove;  /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a caracterului pe axa Y.*/
    protected float maxX;   /*!< Retine poztia maxima a caracterului pe axa X.*/
    protected float minX;   /*!< Retine pozitie minima a caracterului pe axa X.*/

   int  inainte=1;  /*!< flag util pt determinarea directiei de deplasare-->dreapta*/
   int  inapoi=0;   /*!< flag util pt determinarea directiei de deplasar-->stanga*/
    /*! \fn public Enemy(RefLinks refLink, float x, float y, int width, int height, float speed, int life)
            \brief Constructor cu parametri al clasei Enemy

            \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
            \param x Pozitia de start pe axa X a inamicului.
            \param y Pozitia de start pe axa Y a inamicului.
            \param width Latimea imaginii inamicului.
            \param height Inaltimea imaginii inamicului.
         */
    public Enemy(RefLinks refLink, float x, float y, int width, int height)
    {
        ///Apel constructor la clasei de baza
        super(refLink, x,y, width, height);
        //Seteaza pe valorile implicite pentru viata, viteza si distantele de deplasare
        life    = DEFAULT_LIFE;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
        maxX=x+2*48;
        minX=x-2*48;
        //System.out.println("x="+x+" ");
       // System.out.println("y="+y+" ");

       // System.out.println("maxX="+maxX+" ");
       // System.out.println("minX="+minX+" ");

    }

    /*! \fn public void Move()
        \brief Modifica pozitia inamicului, avand in vedere si eroii oponenti
     */
    public void Move()
    {

        if(am_coliziune_M(xMove,0f)==false)//verific coliiziunea cu punctl spre care ma deplasez
        {
            //daca inamicii nu sunt in coliziune cu rick si morty, se pot misca
            MoveX();
        }

        if(am_coliziune_M(xMove,0f)==true)//verific coliiziunea cu punctl spre care ma deplasez
        {
            //refLink.GetMap().getItemsManager().getItems().remove(2);
            Assets.clip_joc.stop();
            State.SetState(refLink.GetGame().getGameOverState());


        }


        if(am_coliziune_M(0f,yMove)==false)
        {
            //System.out.println("collide ");

            MoveY();
        }

    }

    /*! \fn public void MoveX()
        \brief Modifica pozitia inamicului pe axa X. Functia se bazeaza pe niste flag-uri prin care determin daca trebuie ca inamicul sa se deplasezze
        la dreapta sau la stanga.Acesta are o suprafata limitata de deplasare .Limitele sunt 2 valori, minim si maxim , setate anterior in program.
     */
    public void MoveX()
    {
        ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa X.
        yMove = 0;
        //System.out.println("x:="+x+" ");
        if(inainte==1 && inapoi==0 && x<=maxX)
        {
            xMove=speed;
            x+=xMove;
            if(x==maxX)
                inapoi=1;
            else
                inapoi=0;
        }
        else if(x>=minX && inapoi==1)
        {
            xMove=-speed;
            x+=xMove;
            if(x==(minX+96))
            {
                inainte=1;
                inapoi=0;
            }


         }


    }
    /*! \fn public void MoveX()
           \brief Modifica pozitia caracterului pe axa Y.
        */
    public void MoveY()
    {
        //y=0;
        //System.out.println("y="+y+" ");
    }
    @Override
    public void Update() {

    }
    @Override
    public void Draw(Graphics g) {

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
        \brief Seteaza viteza caracterului.
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
            \brief Returneaza distanta in pixeli pe axa X cu care este actualizata pozitia caracterului.
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



}
