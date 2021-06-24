package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.SunetLoader;
import PaooGame.Items.*;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;
import PaooGame.Items.ItemsManager;
import PaooGame.Items.Item;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    public static Map map;                     /*!< Referinta catre harta*/


    public PlayState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza
        super(refLink);
        ///Construieste harta jocului
        map    = new Map(refLink,1);
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);

        try {
            SunetLoader.setVolume(Assets.clip_joc, refLink.GetDatabase().getJocVolume());
        }
        catch (SQLException e)
        {
            System.err.println("Eroare MenuState->Constructor");
        }

    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        map.Update();


        if(!Assets.clip_joc.isRunning())
        { ///Verific daca Clipul audio este pornit.In caz contrar, il pornesc.
            Assets.clip_joc.setFramePosition(0);  /// il setez sa inceapa de la frame-ul 0 , incepe din nou in momentul in care se termina prima data
            Assets.clip_joc.start(); /// Folosind start pornesc clipul audio
        }


    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);
    }

    protected void de_la_inceput()
    {

        map=new Map(refLink,1);
        refLink.SetMap(map);

    }





///ii dau matricea pe care sa mi o scrie in fisier
protected static void writeInFile(String path, int[][] matrix)
    {
        FileWriter f;
        try {
            f = new FileWriter(path);
            for(int i=0; i<16; ++i)
            {
                for(int j=0;j<25;++j) {
                    f.write(matrix[j][i] + " ");
                }
                f.write("\n");
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
