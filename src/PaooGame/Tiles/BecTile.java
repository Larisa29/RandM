package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class BecTile extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public BecTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.bec, id,1);
    }

    /*! \fn public boolean IsSolid()
       \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in cazul unei coliziuni.
    */
    @Override
    public boolean IsSolid()
    {
        return false;
    }
}
