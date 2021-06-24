package PaooGame.Tiles;
/*! \class Tile_Factory
    \brief Clasa permite crearea obiectelor Tile folosind sablonul "Factory"
 */
public class Tile_Factory {
    /*! \fn public Tile Tile_creare(int id)
        \brief Metoda returneaza tipul de dala cerut, in functie de id ul introdus.
        Tile_Factory este fabrica de creare a dalei, Tile este produsul fabricii

        \param id Id-ul dalei.
     */

    public Tile Tile_creare(int id)
    {
             switch (id) {
                 case 4:
                     return new SoilTile(4);
                 case 5:
                     return new ZidTile(5);
                 case 0:
                     return new ZidInchisTile(0);
                 case 1:
                     return new ObstacolNegruTile(1);
                 case 2:
                     return new UsaTile(2);
                 case 3:
                     return new LicoareTile(3);
                 case 6:
                     return new BecTile(6);
                 case 7:
                     return new ButoiTile(7);
                 case 8:
                     return new VerdeTile(8);
                 case 9:
                     return new ZidGriTile(9);
                 case 10:
                     return new ZidGriInchisTile(10);

                 default:
                     return null;
            }
    }


}
