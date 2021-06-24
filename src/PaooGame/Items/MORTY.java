package PaooGame.Items;
import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Animation;

/*! \class Morty extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:imaginea,deplasarea,dreptunghiul de coliziune
 */
public class MORTY extends Character
{
    Animation morty_default,morty_din_fata,morty_din_spate,morty_din_stanga,morty_din_dreapta;
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/

    /*! \fn public MORTY(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public MORTY(RefLinks refLink, float x, float y)
    {
        ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        ///Seteaza imaginea de start a eroului
        morty_default=new Animation(200,Assets.Morty_inceput);

        ///Stabilieste pozitia relativa si dimensiunea  dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;//collisson box pt Rick e la 16 pixeli spre dreapta fata de imaginea in ansamblu
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 23;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;

        ///Tot aici ma ocup si de animatii
        morty_din_fata=new Animation(200,Assets.Morty_front1);
        morty_din_spate=new Animation(200,Assets.Morty_back1);
        morty_din_stanga=new Animation(200,Assets.Morty_left1);
        morty_din_dreapta=new Animation(200,Assets. Morty_right1);

    }



    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
        ///Actualizeaaza imagine(animatie)
        morty_din_fata.Update();
        morty_din_spate.Update();
        morty_din_stanga.Update();
        morty_din_dreapta.Update();

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
        if(refLink.GetKeyManager().up)
        {
            yMove = -speed;

        }
        ///Verificare apasare tasta "jos"
        if(refLink.GetKeyManager().down)
        {
            yMove = speed;
        }
        ///Verificare apasare tasta "left"
        if(refLink.GetKeyManager().left)
        {
            xMove = -speed;
        }
        ///Verificare apasare tasta "dreapta"
        if(refLink.GetKeyManager().right)
        {
            xMove = speed;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        //g.drawImage(image, (int)x, (int)y, width, height, null);
        g.drawImage(getAnimatiaPtFrameCurent(), (int)x, (int)y, width, height, null);
        if((refLink.GetKeyManager().invizibil|| refLink.GetKeyManager().ENTER)  && (refLink.GetKeyManager().up || refLink.GetKeyManager().down || refLink.GetKeyManager().left || refLink.GetKeyManager().right))
        {
            System.out.println("trec prin butoi  ");
            //deschide_portal(g);
            invizibil(g);
            //xMove=speed;

        }
        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }



    /*! \fn public Boolean exista_miscare()
         Functia verifica daca exista tasta apasata, lucru care produce miscarea in joc.
     */
    public Boolean exista_miscare()
    {
        if(refLink.GetKeyManager().left || refLink.GetKeyManager().up || refLink.GetKeyManager().right || refLink.GetKeyManager().down)
            return true;
        return false;
    }

    /*! \fn     private BufferedImage getAnimatiaPtFrameCurent()
        Functia utilizata pentru selectia imaginii ce trebuie afisata in functie de pozitia inamicului.
    */
    private BufferedImage getAnimatiaPtFrameCurent()
    {
        ///Daca nu apas vreo tasta personajul este pozitionat cu fata, altfel apare o imagine corespunzatoare directiei de deplasare
        if(exista_miscare()==false)
            return morty_default.getImgCurenta();
        else if(xMove<0)
            return morty_din_stanga.getImgCurenta();
        else if(xMove>0)
            return morty_din_dreapta.getImgCurenta();
        else if(yMove<0)
            return morty_din_spate.getImgCurenta();
        else
            return morty_din_fata.getImgCurenta();
    }




}

