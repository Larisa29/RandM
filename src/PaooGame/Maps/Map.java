package PaooGame.Maps;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import PaooGame.Items.ItemsManager;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import PaooGame.Items.RICK;
import PaooGame.Items.MORTY;
import PaooGame.Items.Monstru1;
import PaooGame.Items.Monstru2;
import PaooGame.Items.Monstru3;
import PaooGame.Items.Monstru4;
import PaooGame.Items.Monstru5;


/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map
{
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    public int [][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    private ItemsManager itemsManager;/*!< Referinta catre o un obiect ce permite operatii cu personajele.*/
    private int nr_obiecte_schimbate;/*!< Nr de obiecte(din harta) ce se schimba in timpul unui nivel.*/


    /*! \fn public Map(RefLinks refLink)
            \brief Constructorul de initializare al clasei.

            \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
         */
    public Map(RefLinks refLink, int nr_hartii)
    {
        /// Retine referinta "shortcut".
        this.refLink = refLink;
        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.

        if(nr_hartii==1)
            itemsManager=new ItemsManager(refLink,new RICK(refLink,100,100),new MORTY(refLink,150,150),new Monstru1(refLink,150,300),new Monstru2(refLink,300,500), new Monstru3(refLink,750,600),new Monstru4(refLink,800,100),null);//, new Monstru5(refLink,800,100));
        else
            itemsManager=new ItemsManager(refLink,new RICK(refLink,500,100),new MORTY(refLink,250,450),new Monstru1(refLink,250,248),new Monstru2(refLink,900,320),new Monstru3(refLink,230,500),new Monstru4(refLink,800,580),new Monstru5(refLink,800,100));//new Monstru5(refLink,600,600));
        ///Fiecare harta are initializat parametrul care contorizeaza nr de dale schimabte pe parcurs
        nr_obiecte_schimbate=0;
        LoadWorld(nr_hartii);

    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente
     */
    public  void Update()
    {
        itemsManager.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g)
    {
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = 0; y < refLink.GetGame().GetHeight()/Tile.TILE_HEIGHT; y++)
        {
            for(int x = 0; x < refLink.GetGame().GetWidth()/Tile.TILE_WIDTH; x++)
            {
                GetTile(x, y).Draw(g, (int)x * Tile.TILE_HEIGHT, (int)y * Tile.TILE_WIDTH);
            }
        }
        ///tot aici  ma ocup si de desenarea personajelor pe harta
        itemsManager.Draw(g);
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita, aici soilTile
     */
    public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.soilTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.soilTile;
        }
        return t;
    }

    /*! \fn public void setTile(int x,int y)
    \brief Schimba o dala cu alta predefinita.
       Odata ce am schimbat dala, contorizez schimbarea in variabila nr_obiecte_schimbate
 */
    public void setTile(int x,int y)
    {

        tiles[x][y] = 4;//cod asociat dala sol
        nr_obiecte_schimbate++;
        System.out.println("obiecte culese: "+nr_obiecte_schimbate);
    }


    /*! \fn  public ItemsManager getItemsManager()
        Returneaza un obiect de tip ItemsManager
     */
    public ItemsManager getItemsManager()
    {//accesez in alte clase
        return itemsManager;
    }
    /*! \fn      public void SetObjGasiste()
    Functie care seteaza nr de obiecte gasite cu 0.
        */
    public void SetObjGasiste()
    {
        nr_obiecte_schimbate=0;
    }
    /*! \fn     public int getNr()
        Functie care returneaza nr de obiecte  schimbate.
            */
    public int getNr()
    {
        return nr_obiecte_schimbate;
    }

    /*! \fn public ItemsManager alege_itemsManager(int nr_harta)
    Metoda returneaza un tip predefinit de obiect ItemsManager, corespunzator fie hartii 1 ,fie hartii 2.
    */
    public ItemsManager alege_itemsManager(int nr_harta)
    {
        if(nr_harta==1)
            return new ItemsManager(refLink,new RICK(refLink,100,100),new MORTY(refLink,150,150),new Monstru1(refLink,150,300),new Monstru2(refLink,300,500), new Monstru3(refLink,750,600),new Monstru4(refLink,800,100),null);
        else
            return new ItemsManager(refLink,new RICK(refLink,500,100),new MORTY(refLink,250,450),new Monstru1(refLink,250,248),new Monstru2(refLink,900,320),new Monstru3(refLink,230,500),new Monstru4(refLink,800,580),new Monstru5(refLink,800,100));//new Monstru5(refLink,600,600));

    }
    /*! \fn     public void setItemsManager(ItemsManager im)
       Metoda seteaza un obiecte de tip ItemsManager
       */
    public void setItemsManager(ItemsManager im)
    {
        itemsManager=im;
    }



    /*! \fn public void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */

    public void LoadWorld(int nr_hartii)
    {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
        ///Se stabileste latimea hartii in numar de dale.
        width = 25;
        ///Se stabileste inaltimea hartii in numar de dale
        //height = 10;
        height = 16;
        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];
        //Se incarca matricea cu coduri
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(nr_hartii==1)
                    tiles[x][y] = nivel1_harta(y,x);
                else if(nr_hartii==2)
                    tiles[x][y] =nivel2_harta(y,x);
            }
        }
    }

    /*! \fn private int MiddleEastMap(int x ,int y)
        \brief O harta incarcata static.

        \param x linia pe care se afla codul dalei de interes.
        \param y coloana pe care se afla codul dalei de interes.
     */
    private int nivel1_harta(int x ,int y)
    {
        final int map[][] = {
                {5, 5, 5, 5, 5, 5, 5, 5, 5,5,5,5,5,5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                {5, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                {5, 3, 4, 4, 4, 4, 4, 4, 4,4,4,4,6,6, 6, 4, 4, 4, 4, 4, 4, 4, 4, 6, 5},
                {5, 3, 4, 4, 7, 4, 4, 4, 4,1,4,1,4,1, 4, 4, 4, 4, 4, 4, 7, 4, 4, 6, 5},
                {5, 3, 4, 7, 3, 7, 4, 4, 4,4,4,4,4,4, 4, 4, 4, 4, 4, 7, 6, 7, 4, 6, 5},
                {5, 4, 4, 4, 7, 4, 4, 1, 1,4,4,4,4,4, 1, 1, 4, 4, 4, 4, 7, 4, 4, 4, 5},
                {5, 4, 4, 4, 4, 4, 4, 1, 3,4,5,5,5,4, 6, 1, 4, 1, 4, 4, 4, 4, 4, 4, 5},
                {5, 4, 4, 4, 4, 4, 4, 1, 4,4,5,5,5,4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 5},
                {5, 4, 4, 4, 4, 4, 4, 1, 6,4,0,2,0,4, 3, 1, 4, 1, 4, 4, 4, 4, 4, 4, 5},
                {5, 6, 4, 8, 4, 4, 4, 1, 1,4,4,4,4,4, 1, 1, 4, 4, 4, 4, 8, 4, 4, 3, 5},
                {5, 6, 8, 3, 8, 4, 4, 4, 4,4,4,8,4,4, 4, 4, 4, 4, 4, 8, 3, 8, 4, 3, 5},
                {5, 6, 4, 8, 4, 4, 4, 4, 4,4,8,6,8,4, 4, 4, 4, 4, 4, 4, 8, 4, 4, 3, 5},
                {5, 4, 4, 4, 4, 4, 4, 4, 4,4,4,8,4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5},
                {5, 4, 4, 4, 4, 4, 4, 4, 4,4,4,4,4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5},
                {5, 5, 5, 5, 5, 5, 5, 5, 5,5,5,5,5,5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                {0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        return map[x][y];



    }



    private int nivel2_harta(int x ,int y)
    {
        final int map[][] = {
                {9, 9, 9, 9, 9, 9, 9, 9, 9,9,9,9,9,9, 9, 9, 9, 9, 9, 9, 9, 9, 9,9,  9},
                {9, 10, 10, 10, 10, 10, 10, 10, 10,10,10,10,9,10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 9},
                {9, 4, 4, 4, 4, 4, 4, 4, 4,4,4,6,9,6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 9},
                {9, 3, 4, 4, 7, 4, 4, 4, 4,1,4,9,9,9, 4, 4, 1, 4, 4, 4, 7, 4, 4, 6, 9},
                {9, 4, 4, 7, 3, 7, 4, 4, 1,6,4,10,9,10, 4, 4, 3, 1, 4, 7, 6, 7, 4, 4, 9},
                {9, 3, 4, 4, 7, 4, 4, 4, 6,1,4,6,9,6, 4, 4, 1, 3, 4, 4, 7, 4, 4, 6, 9},
                {9, 4, 4, 4, 4, 4, 4, 4, 1,6,4,4,9,4, 4, 4, 3, 1, 4, 4, 4, 4, 4, 4, 9},
                {9, 3, 4, 4, 4, 4, 4, 4, 6,1,4,4,4,4, 4, 4, 1, 3, 4, 4, 4, 4, 4, 6, 9},
                {9, 4, 4, 4, 4, 4, 4, 4, 1,6,4,4,4,4, 4, 4, 3, 1, 4, 4, 4, 4, 4, 4, 9},
                {9, 3, 4, 8, 4, 4, 4, 4, 6,1,4,4,9,4, 4, 4, 1, 3, 4, 4, 8, 4, 4, 6, 9},
                {9, 4, 8, 3, 8, 4, 4, 4, 1,6,4,3,9,3, 4, 4, 3, 1, 4, 8, 3, 8, 4, 4, 9},
                {9, 3, 4, 8, 4, 4, 4, 4, 6,1,4,9,9,9, 4, 4, 1, 3, 4, 4, 8, 4, 4, 6, 9},
                {9, 4, 4, 4, 4, 4, 4, 4, 4,4,4,10,9,10, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 9},
                {9, 3, 4, 4, 4, 4, 4, 4, 4,4,4,3,9,3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 9},
                {9, 9, 9, 9, 9, 9, 9, 9, 9,9,9,9,9,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                {10, 10, 10, 10, 10, 10, 10, 10, 10,10,10,10,10,10, 10, 10,10, 10, 10, 10, 10, 10, 10, 10, 10}};
        return map[x][y];



    }



}