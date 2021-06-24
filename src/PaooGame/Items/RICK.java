package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Animation;


public class RICK extends Character
{
    Animation rick_default,rick_din_fata,rick_din_spate,rick_din_stanga,rick_din_dreapta;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */

    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
     /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public RICK(RefLinks refLink, float x, float y)
    {


        ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        ///Seteaza animatia de start a eroului
        rick_default=new Animation(200,Assets.Rick_inceput);

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;//collision box pt Rick e la 16 pixeli spre dreapta fata de imaginea in ansamblu
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 27;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;

        ///ma ocup de animatii
        rick_din_fata=new Animation(200,Assets.Rick_front1);
        rick_din_spate=new Animation(200,Assets.Rick_back1);
        rick_din_stanga=new Animation(200,Assets.Rick_left1);
        rick_din_dreapta=new Animation(200,Assets. Rick_right1);

    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
        rick_din_fata.Update();
        rick_din_spate.Update();
        rick_din_stanga.Update();
        rick_din_dreapta.Update();

        ///Verifica daca a fost apasata o tasta
        GetInput();
        ///Actualizeaza pozitia
        Move();
    }


    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
        ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().sus)
        {
            yMove = -speed;
            //System.out.println("y move:="+yMove+" ");

        }
        ///Verificare apasare tasta "jos"
        if(refLink.GetKeyManager().jos)
        {
            yMove = speed;
           // System.out.println("y move:="+yMove+" ");
        }
        ///Verificare apasare tasta "left"
        if(refLink.GetKeyManager().stanga)
        {
            xMove = -speed;
            //System.out.println("x move:="+xMove+" ");
        }
        ///Verificare apasare tasta "dreapta"
        if(refLink.GetKeyManager().dreapta)
        {
            xMove = speed;
           // System.out.println("x move:="+xMove+" ");
        }




    }



    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(getAnimatiaPtFrameCurent(), (int)x, (int)y, width, height, null);

        if((refLink.GetKeyManager().ENTER || refLink.GetKeyManager().invizibil)  && (refLink.GetKeyManager().sus || refLink.GetKeyManager().jos || refLink.GetKeyManager().stanga || refLink.GetKeyManager().dreapta))
        {
            System.out.println("trec prin butoi  ");
            invizibil(g);
            //xMove=speed;

        }
        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.red);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }


    /*! \fn public Boolean exista_miscare()
            Functia verifica daca exista tasta apasata, lucru care produce miscarea in joc.
        */
    private Boolean exista_miscare()
    {
        if(refLink.GetKeyManager().stanga || refLink.GetKeyManager().sus || refLink.GetKeyManager().dreapta || refLink.GetKeyManager().jos)
            return true;
        return false;
    }
    /*! \fn     private BufferedImage getAnimatiaPtFrameCurent()
        Functia utilizata pentru selectia imaginii ce trebuie afisata in functie de pozitia inamicului.
    */
    private BufferedImage getAnimatiaPtFrameCurent()
    {
        if(exista_miscare()==false)                 //daca nu apas vreo tasta personajul este pozitionat cu fata
        {
            return rick_default.getImgCurenta();
        }
        else if(xMove<0)
            return rick_din_stanga.getImgCurenta();
        else if(xMove>0)
            return rick_din_dreapta.getImgCurenta();
        else if(yMove<0)
            return rick_din_spate.getImgCurenta();
        else
            return rick_din_fata.getImgCurenta();
    }

}
