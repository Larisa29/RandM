package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class LicoareTile extends Tile
{
    /*! \fn public GrassTile(int id)
           \brief Constructorul de initializare al clasei

           \param id Id-ul dalei util in desenarea hartii.
        */



    public LicoareTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.licoare, id,1);
     }
    @Override
    public boolean IsSolid()
    {
        return false;
    }



}
