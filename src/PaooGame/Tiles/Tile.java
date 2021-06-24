package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 32;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/
    private final static Tile_Factory tileFactory = new Tile_Factory();

    /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
    /// o singura data in memorie

   public static Tile soilTile          =tileFactory.Tile_creare(4);
    public static Tile zid_rosu_inchis  =tileFactory.Tile_creare(5);
    public static Tile obstacol_negru   =tileFactory.Tile_creare(0);
    public static Tile zid_rosu         =tileFactory.Tile_creare(1);
    public static Tile usa              =tileFactory.Tile_creare(2);
    public static Tile licoare          =tileFactory.Tile_creare(3);
    public static Tile bec              =tileFactory.Tile_creare(6);
    public static Tile butoi            =tileFactory.Tile_creare(7);
    public static Tile verde            =tileFactory.Tile_creare(8);
    public static Tile zid_gri          =tileFactory.Tile_creare(9);
    public static Tile zid_gri_inchis   =tileFactory.Tile_creare(10);


   /* public static Tile zid_rosu_inchis  = new ZidInchisTile(0);      //dala zid rosu inchis
    public static Tile obstacol_negru   = new ObstacolNegruTile(1);      //dala obstacol negru
    public static Tile usa              =new UsaTile(2);
    public static Tile licoare          =new LicoareTile(3);
    public static Tile bec              =new BecTile(6);
    public static Tile butoi             =new ButoiTile(7);
    public static Tile verde             =new VerdeTile(8);
    public static Tile zid_gri             =new ZidGriTile(9);
    public static Tile zid_gri_inchis      =new ZidGriInchisTile(10);
*/



    private int flag;                       /*!<flag util pt a face diferenta intre soilTile si licoare/buto*/
    public static final int TILE_WIDTH  = 48;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 48;                       /*!< Inaltimea unei dale.*/


    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
        \param flag , tipul dalei.

     */
    public Tile(BufferedImage image, int idd,int f)
    {
        img = image;
        id = idd;
        tiles[id] = this;
        flag=f;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {
    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
        /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }


    /*! \fn public int GetId()
                \brief Returneaza id-ul dalei.
             */
    public int GetId()
    {
        return id;
    }
    /*! \fn public int GetId()
              \brief Returneaza tipul dalei.
           */
    public int getFlag()
    {
        return flag;
    }

}
