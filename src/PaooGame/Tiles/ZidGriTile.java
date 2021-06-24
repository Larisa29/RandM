package PaooGame.Tiles;
import PaooGame.Graphics.Assets;

public class ZidGriTile extends Tile
{
    /*! \fn public SoilTile(int id)
      \brief Constructorul de initializare al clasei

      \param id Id-ul dalei util in desenarea hartii.
   */
    public ZidGriTile(int id)
    {
        super(Assets.zid_gri, id,0);
    }

    /*! \fn public boolean IsSolid()
       \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in cazul unei coliziuni.
    */
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
