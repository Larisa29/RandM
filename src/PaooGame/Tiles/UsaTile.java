package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class UsaTile extends Tile
{
    /*! \fn public WaterTile(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public UsaTile(int id)
    {
        super(Assets.usa, id,0);
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
