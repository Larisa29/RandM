package PaooGame.Items;

import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Animation;
/*! \class Monstru5 extends Enemy
    \brief Implementeaza al 5 lea tip de inamic posibil
 */
public class Monstru5 extends Enemy
{
    Animation monstru5_stg;  /*!<Animatie directie stanga a inamicului .*/
    Animation monstru5_drt; /*!<Animatie directie dreapta a inamicului.*/
    /*! \fn     public Monstru5(RefLinks refLink,float x, float y)
        \brief Constructor de initializare al clasei Monstru1
        \param refLink Referinta catre obiectul shortcut , cu ajutorul caruia am acces la alte clase din joc
        \param x Pozitia de start pe axa X a inamicului.
        \param y Pozitia de start pe axa Y a inamicului.
        \brief Tot aici se initializeaza si imaginile corespunzatoare animatiei inamicului.
         */

    public Monstru5(RefLinks refLink,float x, float y)
    {
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        monstru5_stg=new Animation(200, Assets.monstru5_stg);
        monstru5_drt=new Animation(200, Assets.monstru5_dr);
        normalBounds.x = 16;//collision box pt Rick e la 16 pixeli spre dreapta fata de imaginea in ansamblu
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 27;
    }
    /*! \fn public void Update()
\brief Actualizeaza pozitia si imaginea inamicului in functie de directie.
       Actualizam pozitia pe harta e inamicului.
*/
    @Override
    public void Update()
    {
        monstru5_stg.Update();
        monstru5_drt.Update();
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
            return monstru5_stg.getImgCurenta();
        else return monstru5_drt.getImgCurenta();

    }

}
