package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class ObstacolNegruTile extends Tile
{
    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public ObstacolNegruTile(int id)
    {
        super(Assets.obstacol_negru, id,0);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
