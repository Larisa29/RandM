package PaooGame.Items;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Animation;
/*! \class Monstru2 extends Enemy
    \brief Implementeaza al 2 lea tip de inamic posibil
 */
public class Monstru2 extends Enemy
{
    Animation monstru2_stg;  /*!<Animatie directie stanga a inamicului .*/
    Animation monstru2_drt; /*!<Animatie directie dreapta a inamicului.*/
    /*! \fn     public Monstru2(RefLinks refLink,float x, float y)
        \brief Constructor de initializare al clasei Monstru1
        \param refLink Referinta catre obiectul shortcut , cu ajutorul caruia am acces la alte clase din joc
        \param x Pozitia de start pe axa X a inamicului.
        \param y Pozitia de start pe axa Y a inamicului.
        \brief Tot aici se initializeaza si imaginile corespunzatoare animatiei inamicului.
         */
    public Monstru2(RefLinks refLink,float x, float y)
    {
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        monstru2_stg=new Animation(200, Assets.monstru2_stg);
        monstru2_drt=new Animation(200, Assets.monstru2_dr);


        normalBounds.x =5 ;
        normalBounds.y = 5;
        normalBounds.width = 32;
        normalBounds.height = 42;

    }
    /*! \fn public void Update()
   \brief Actualizeaza pozitia si imaginea inamicului in functie de directie.
          Actualizam pozitia pe harta e inamicului.
*/
    @Override
    public void Update()
    {
        monstru2_stg.Update();
        monstru2_drt.Update();
        Move();
    }



    @Override
    public void Draw(Graphics g)
    {
        //g.drawImage(image, (int)x, (int)y, width, height, null);
        g.drawImage(getAnimatiaPtFrameCurent(), (int)x, (int)y, width, height, null);
        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }


    /*! \fn     private BufferedImage getAnimatiaPtFrameCurent()

       \brief Metoda utila pentru a alege ce imagine a caracterului(din vectorul asociat) trebuie aleasa.
       Alegerea se face pe baza pozitiei inamicului.
    */
    private BufferedImage getAnimatiaPtFrameCurent()
    {
        if(xMove<0)
            return monstru2_stg.getImgCurenta();
            //else if(xMove>0)
            //return monstru1_drt.getImgCurenta();
        else return monstru2_drt.getImgCurenta();

    }
}
