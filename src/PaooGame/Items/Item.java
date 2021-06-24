package PaooGame.Items;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;


/*! \class Item
    \brief. Implementeaza notiunea abstracta de entitate activa din joc, "element cu care se poate interactiona: monstru, turn etc.".
 */
public abstract class Item
{
    ///asa cum s-a mai precizat, coordonatele x si y sunt de tip float pentru a se elimina erorile de rotunjire
    ///ce pot sa apara in urma calculelor, urmand a se converti la intreg doar in momentul desenarii.
    protected float x;                  /*!< Pozitia pe axa X a "tablei" de joc a imaginii entitatii.*/
    protected float y;                  /*!< Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.*/
    protected int width;                /*!< Latimea imaginii entitatii.*/
    protected int height;               /*!< Inaltimea imaginii entitatii.*/
    protected Rectangle bounds;         /*!< Dreptunghiul curent de coliziune.*/
    protected Rectangle normalBounds;   /*!< Dreptunghiul de coliziune aferent starii obisnuite(spatiul ocupat de entitate in mod normal), poate fi mai mic sau mai mare decat dimensiunea imaginii sale.*/
    protected Rectangle attackBounds;   /*!< Dreptunghiul de coliziune aferent starii de atac.*/
    protected RefLinks refLink;         /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    public static final int DEFAULT_LIFE            = 10;   /*!< Valoarea implicita a vietii unui caracter.*/
    protected int life;     /*!< Retine viata caracterului.*/



    /*! \fn public Item(RefLinks refLink, float x, float y, int width, int height)
            \brief Constructor de initializare al clasei

            \param  reflink Referinte "shortcut" catre alte referinte
            \param  x   Pozitia pe axa X a "tablei" de joc a imaginii entitatii.
            \param  y   Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.
            \param  width   Latimea imaginii entitatii.
            \param  height  Inaltimea imaginii entitatii.
         */
    public Item(RefLinks refLink, float x, float y, int width, int height)
    {
        this.x = x;             /*!< Retine coordonata pe axa X.*/
        this.y = y;             /*!< Retine coordonata pe axa X.*/
        this.width = width;     /*!< Retine latimea imaginii.*/
        this.height = height;   /*!< Retine inaltimea imaginii.*/
        this.refLink = refLink; /*!< Retine the "shortcut".*/
        this.life    = DEFAULT_LIFE;//fiecare entity are asociat un health, items sunt doar personajele la mine

        ///Creaza dreptunghi de coliziune pentru modul normal, aici a fost stabilit la dimensiunea imaginii dar poate fi orice alta dimensiune
        normalBounds = new Rectangle(0, 0, width, height);
        ///Creaza dreptunghi de coliziune pentru modul de atack, aici a fost stabilit la dimensiunea imaginii dar poate fi orice alta dimensiune
        attackBounds = new Rectangle(0, 0, width, height);
        ///Dreptunghiul de coliziune implicit este setat ca fiind cel normal
        bounds = normalBounds;
    }

    ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update();
    ///Metoda abstracta destinata desenarii starii curente
    public abstract void Draw(Graphics g);

    /*! \fn public float GetX()
        \brief Returneaza coordonata pe axa X.
     */
    public float GetX()
    {
        return x;
    }

    /*! \fn public float GetY()
        \brief Returneaza coordonata pe axa Y.
     */
    public float GetY()
    {
        return y;
    }

    /*! \fn public float GetWidth()
        \brief Returneaza latimea entitatii.
     */
    public int GetWidth()
    {
        return width;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }


    /*! \fn public float GetHeight()
        \brief Returneaza inaltimea entitatii.
     */
    public int GetHeight()
    {
        return height;
    }

    /*! \fn public float SetX()
        \brief Seteaza coordonata pe axa X.
     */
    public void SetX(float x)
    {
        this.x = x;
    }

    /*! \fn public float SetY()
        \brief Seteaza coordonata pe axa Y.
     */
    public void SetY(float y)
    {
        this.y = y;
    }

    /*! \fn public float SetWidth()
        \brief Seteaza latimea imaginii entitatii.
     */
    public void SetWidth(int width)
    {
        this.width = width;
    }

    /*! \fn public float SetHeight()
        \brief Seteaza inaltimea imaginii entitatii.
     */
    public void SetHeight(int height)
    {
        this.height = height;
    }

    /*! \fn public void SetNormalMode()
        \brief Seteaza modul normal de interactiune
     */
    public void SetNormalMode()
    {
        bounds = normalBounds;
    }

    /*! \fn public void SetAttackMode()
        \brief Seteaza modul de atac de interactiune
     */
    public void SetAttackMode()
    {
        bounds = attackBounds;
    }


   //verific daca itemul curent are coliziune cu ceva din lista mea de itemuri
    public boolean am_coliziune(float xOffset, float yOffset)
     {
        for(Item x: refLink.GetMap().getItemsManager().getItems())//getItemsManager().getItems())
        {
            if(x.equals(this))
            {
                //System.out.println("x.equals()");
                //System.out.println("("+xOffset+","+yOffset+")");

                continue;//nu vreau sa compar o entitate cu ea insasi

            }
            if(x.getSuprafataColiziune(0f,0f).intersects(getSuprafataColiziune(xOffset,yOffset)))
            {
                //System.out.println("coliziune \n");
                //System.out.println("("+xOffset+","+yOffset+")");

                return true;
            }
            else
                {

                    return false;
                }
        }
        return false;
    }

    /*! \fn  public boolean am_coliziune_M(float xOffset, float yOffset)
      \brief Metoda folosita in clasa enemy pt a putea gestiona miscarea inamicilor la stanga respectiv dreapta.Aceasta traverseaza lista cu itemuri si
      folosind metoda intersecst depisteaza daca exista coliziune intre eroi si inamici.Rezultatul returnat poate fi true, marcand existenta coliziunii sau false care marcheaza
      lipsa coliziunii.
   */

    public boolean am_coliziune_M(float xOffset, float yOffset)
    {
        ///il extrag pe morty din arraylist , fiind pe pozitia 1
        Item m = refLink.GetMap().getItemsManager().getItems().get(1);
        ///il extrag pe rick din arraylist , fiind pe pozitia 0
        Item r=refLink.GetMap().getItemsManager().getItems().get(0);

        for(Item x: refLink.GetMap().getItemsManager().getItems())
        {
            if(x.equals(m) || x.equals(r))      //daca elementul curent e rick sau morty
            {
                //System.out.println("x.equals()");
                //System.out.println("("+xOffset+","+yOffset+")");
                //System.out.println("coliziune cu mine");

                continue;//nu vreau sa compar o entitate cu ea insasi
            }
            if(m.getSuprafataColiziune(0f,0f).intersects(getSuprafataColiziune(xOffset,yOffset))  || r.getSuprafataColiziune(0f,0f).intersects(getSuprafataColiziune(xOffset,yOffset)))
            {
                //System.out.println("coliziune \n");
                //System.out.println("("+xOffset+","+yOffset+")");
                System.out.println("coliziune cu cu monstrii");
                return true;
            }
            else
            {

                return false;
            }
        }
        return false;
    }

    /*! \fn  public Rectangle getSuprafataColiziune(float xOffset, float yOffset)
         \brief Metoda folosita pt a construi un Rectangle ce ne ajuta sa aflam daca 2 entitati se intersecteaza

      */
    public Rectangle getSuprafataColiziune(float xOffset, float yOffset)
    {
        return new Rectangle((int) (x+bounds.x+xOffset),(int)(y+bounds.y+yOffset), bounds.width, bounds.height);
    }



}
