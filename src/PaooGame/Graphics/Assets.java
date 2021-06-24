package PaooGame.Graphics;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
    /// Referinte catre elementele grafice (dale) utilizate in joc.

    public static BufferedImage zid_rosu;
    public static BufferedImage zid_rosu_inchis;
    public static BufferedImage soil;
    public static BufferedImage obstacol_negru;
    public static BufferedImage butoi;
    public static BufferedImage licoare;
    public static BufferedImage bec;
    public static BufferedImage usa;
    public static BufferedImage verde;
    public static BufferedImage zid_gri_inchis;
    public static BufferedImage zid_gri;
    public static BufferedImage portal;
    public static BufferedImage invizibil;


    public static BufferedImage[] monstru1_stg;
    public static BufferedImage[] monstru1_dr;

    public static BufferedImage[] monstru2_stg;
    public static BufferedImage[] monstru2_dr;

    public static BufferedImage[] monstru3_stg;
    public static BufferedImage[] monstru3_dr;

    public static BufferedImage[] monstru4_stg;
    public static BufferedImage[] monstru4_dr;

    public static BufferedImage[] monstru5_stg;
    public static BufferedImage[] monstru5_dr;

    public static BufferedImage[] Rick_inceput;
    public static BufferedImage[] Morty_inceput;


    public static BufferedImage[] Rick_front1;
    public static BufferedImage[] Rick_right1;
    public static BufferedImage []Rick_back1;
    public static BufferedImage []Rick_left1;

    public static BufferedImage[] Morty_front1;
    public static BufferedImage[] Morty_right1;
    public static BufferedImage []Morty_back1;
    public static BufferedImage []Morty_left1;

    public static BufferedImage fundal_menu;          /*meniu imagine (fundal) facuta*/
    public static BufferedImage select;                 /*sageata care apare pe ecran cand apas pe un buton din meniu*/
    public static BufferedImage settings;               /*fundal settings*/
    public static BufferedImage gameover;               /*fundal gameover*/
    public static BufferedImage pause;               /*fundal pause state*/
    public static BufferedImage win;               /*fundal pause state*/



    public static AudioInputStream menu_sunet;
    public static AudioInputStream gameover_sunet;
    public static AudioInputStream joc_sunet;

    //Sunet-> CLip in care pot sa incarc un element audio
    public static Clip clip_menu;                                   /*obiect de tip "Clip" in care incarc sunetul din menu*/
    public static Clip clip_gameover;
    public static Clip clip_joc;


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet1=new SpriteSheet(ImageLoader.LoadImage("/textures/sprite_final.png"));
        SpriteSheet sheet2=new SpriteSheet(ImageLoader.LoadImage("/textures/rickoss.png"));
        SpriteSheet sheet3=new SpriteSheet(ImageLoader.LoadImage("/textures/morty.png"));
        SpriteSheet sheet4=new SpriteSheet(ImageLoader.LoadImage("/textures/monstri_sprite.png"));
        /// Se obtin subimaginile corespunzatoare elementelor necesare.


        zid_rosu = sheet1.crop(0, 0);
        zid_rosu_inchis = sheet1.crop(1, 0);
        soil = sheet1.crop(1, 1);
        obstacol_negru = sheet1.crop(2, 1);
        usa=sheet1.crop(0, 2);
        licoare=sheet1.crop(0, 4);
        bec =sheet1.crop(2, 3);
        butoi=sheet1.crop(1, 4);
        verde=sheet1.crop(2, 4);
        portal=sheet1.crop(2, 2);

        zid_gri_inchis=sheet1.crop(2, 0);
        zid_gri=sheet1.crop(0,1);

        monstru1_stg=new BufferedImage[1];
        monstru1_stg[0]=sheet4.crop(1,0);

        monstru1_dr=new BufferedImage[1];
        monstru1_dr[0]=sheet4.crop(0,0);

        monstru2_stg=new BufferedImage[1];
        monstru2_stg[0]=sheet4.crop(1,1);

        monstru2_dr=new BufferedImage[1];
        monstru2_dr[0]=sheet4.crop(0,1);

        monstru3_stg=new BufferedImage[1];
        monstru3_stg[0]=sheet4.crop(3,0);

        monstru3_dr=new BufferedImage[1];
        monstru3_dr[0]=sheet4.crop(2,0);

        monstru4_stg=new BufferedImage[1];
        monstru4_stg[0]=sheet4.crop(3,1);

        monstru4_dr=new BufferedImage[1];
        monstru4_dr[0]=sheet4.crop(2,1);

        monstru5_stg=new BufferedImage[1];
        monstru5_stg[0]=sheet4.crop(2,2);

        monstru5_dr=new BufferedImage[1];
        monstru5_dr[0]=sheet4.crop(0,2);

        Rick_inceput=new BufferedImage[1];
        Rick_inceput[0]=sheet2.crop(0,0);//animatie de inceput pt Rick si cea default daca nu ma misc

        Morty_inceput=new BufferedImage[1];
        Morty_inceput[0]=sheet3.crop(0,0);//animatie de inceput pt Morty si cea default daca nu ma misc


        //vectori de imagini pt fiecare tip de deplasare ;

        //vector de deplasare in jos
        Rick_front1=new BufferedImage[2];
        Rick_front1[0]=sheet2.crop(0,0);
        Rick_front1[1]=sheet2.crop(1,0);

        //vector de imagini de deplasare la dreapta
        Rick_right1=new BufferedImage[2];
        Rick_right1[0]=sheet2.crop(0,1);
        Rick_right1[1]=sheet2.crop(1,1);

        //vector de imagini de deplasare in sus
        Rick_back1=new BufferedImage[2];
        Rick_back1[0]=sheet2.crop(2,0);
        Rick_back1[1]=sheet2.crop(3,0);

        //vector de imagini de deplasare la stanga
        Rick_left1=new BufferedImage[2];
        Rick_left1[0]=sheet2.crop(0,2);
        Rick_left1[1]=sheet2.crop(1,2);

        //vectori de imagini pt fiecare tip de deplasare MORTY;

        //vector de deplasare in jos
        Morty_front1=new BufferedImage[2];
        Morty_front1[0]=sheet3.crop(0,0);
        Morty_front1[1]=sheet3.crop(1,0);

        //vector de imagini de deplasare la dreapta
        Morty_right1=new BufferedImage[2];
        Morty_right1[0]=sheet3.crop(2,0);
        Morty_right1[1]=sheet3.crop(3,0);

        //vector de imagini de deplasare in sus
        Morty_back1=new BufferedImage[2];
        Morty_back1[0]=sheet3.crop(2,2);
        Morty_back1[1]=sheet3.crop(3,2);

        //vector de imagini de deplasare la stanga
        Morty_left1=new BufferedImage[2];
        Morty_left1[0]=sheet3.crop(2,1);
        Morty_left1[1]=sheet3.crop(3,1);

        invizibil=ImageLoader.LoadImage("/textures/fum.png");
        fundal_menu=ImageLoader.LoadImage("/textures/fundal.jpg");
        select=ImageLoader.LoadImage("/textures/ok.png");
        settings=ImageLoader.LoadImage("/textures/settings.jpg");
        gameover=ImageLoader.LoadImage("/textures/gameover.jpg");
        pause=ImageLoader.LoadImage("/textures/pause_save.jpg");
        win=ImageLoader.LoadImage("/textures/win.jpg");

        menu_sunet=SunetLoader.load_sunet("/menu_sunet.wav");
        gameover_sunet=SunetLoader.load_sunet("/sad.wav");
        joc_sunet=SunetLoader.load_sunet("/game.wav");

        //sunet
        try{
            clip_menu=AudioSystem.getClip();
            clip_menu.open(menu_sunet);
            clip_gameover=AudioSystem.getClip();
            clip_gameover.open(gameover_sunet);
            clip_joc=AudioSystem.getClip();
            clip_joc.open(joc_sunet);

        }
        catch(LineUnavailableException | IOException e)
        {
            System.err.println("Eroare la incarcarea sunetelor in Assets.");
        }

    }
}
