package PaooGame.Items;
import PaooGame.RefLinks;
import java.util.ArrayList;
import java.awt.*;

 /*! \class public class ItemsManager
    \brief  Clasa utila pentru a putea controla usor crearea de entitati noi si manipularea acestora

 */

public class ItemsManager {
    private RefLinks reflink;
    private RICK rick;
    private MORTY  morty;

    private Monstru1 monstru1;
    private Monstru2 monstru2;
    private Monstru3 monstru3;
    private Monstru4 monstru4;
    private Monstru5 monstru5;

    private ArrayList <Item> items;

    /*! \fn public ItemsManager(RefLinks reflink, RICK rick, MORTY morty,Monstru1 monstru1,Monstru2 monstru2,Monstru3 monstru3, Monstru4 monstru4,Monstru5 monstru5)
         \brief Constructor cu parametri al clasei ItemsManager

         \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
         \param rick Pozitia de start pe axa X a inamicului.
         \param morty Pozitia de start pe axa Y a inamicului.
         \param monstru1 inamic de tip Monstru1.
         \param monstru2 inamic de tip Monstru2.
         \param monstru3 inamic de tip Monstru3.
         \param monstru4 inamic de tip Monstru4.
         \param monstru5 inamic de tip Monstru5.

      */
    public ItemsManager(RefLinks reflink, RICK rick, MORTY morty,Monstru1 monstru1,Monstru2 monstru2,Monstru3 monstru3, Monstru4 monstru4,Monstru5 monstru5)
    {
        this.reflink=reflink;
        this.rick=rick;
        this.morty=morty;
        this.monstru1=monstru1;
        this.monstru2=monstru2;
        this.monstru3=monstru3;
        this.monstru4=monstru4;
        this.monstru5=monstru5;

        items=new ArrayList<Item>();
        ///adaugare elemente in lista de itemuri
        addItem(rick);
        addItem(morty);
        addItem(monstru1);
        addItem(monstru2);
        addItem(monstru3);
        addItem(monstru4);
        addItem(monstru5);

    }
/*! \fn public public void Update()
         \brief Metoda necesara parcurgerii listei de item uri si de actualizare a acestora

 */
    public void Update()
    {
        for(int i=0; i<items.size(); i++)
        {
            Item x=items.get(i);
            if(x!=null)
                x.Update();

        }

    }

    /*! \fn  public void addItem(Item x)
         \brief Metoda necesara pt a adauga noi itemi in lista

 */
    public void addItem(Item x)
    {
        if(x!=null)
            items.add(x);
        //System.out.println("entitate noua");

    }

    /*! \fn public void Draw(Graphics g)
         \brief Randeaza/deseneaza itemul in noua pozitie.

         \brief g Contextul grafic in care trebuie efectuata desenarea.
      */
    public void Draw(Graphics g)
    {
        for(int i=0; i<items.size(); i++)
        {
            Item x=items.get(i);
            if(x!=null)
                x.Draw(g);
        }


    }
    public RICK getRick() {
        return rick;
    }

    public MORTY getMorty() {
        return morty;
    }
    public Monstru1 getMonstru1() {
        return monstru1;
    }

    /*! \fn  public ArrayList<Item> getItems()
       \brief Metoda returneaza lista de item uri pe care am construit o
    */
    public ArrayList<Item> getItems() {
        return items;
    }



    public int getIndex()
    {
        if(items.get(0)!=null)
        {
            return 0;
        }

        return 2;
    }



}
