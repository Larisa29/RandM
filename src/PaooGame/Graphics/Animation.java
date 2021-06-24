package PaooGame.Graphics;
import java.awt.image.BufferedImage;

public class Animation
{
    private int timeFrame;           /*!<timeFrame pt timpul la care vreau sa modific imaginea(trec 2 secunde->merg la urmatoarea imagine din vector)*/
    private int index;               /*!<index corespunzator imaginii curente  */
    private BufferedImage[] imagini;        /*!<vecttor de imagini ale caracterului*/
    private long timp_anterior,timp_curent; /*!< variabile care ma ajuta sa determin cand cresc indexul*/

    /*! public Animation(int s, BufferedImage[] f)
           \brief Constructorul cu parametri al clasei Animation

           Retine proprietatile necesare stabilirii imaginii caracterului ce trebuie afisata la un moment dat

        */
    public Animation(int s, BufferedImage[] f)
    {
        ///timeFrame->viteza cu care se schimba de la o imagine la alta
        this.timeFrame=s;
        this.imagini=f;
        ///am index=0->incep cu prima imagine de fiecare data
        index=0;
        timp_curent=0;
        ///Metoda returneaza timpul care a trecut de cand a inceput programul curent in milisecunde
        timp_anterior=System.currentTimeMillis();

    }

    /*! \fn public BufferedImage getImgCurenta()
        \brief returneaza imaginea de la indexul curent
    */
    public BufferedImage getImgCurenta()
    {
        return imagini[index];
    }

    /*! \fn public void Update()
        \brief Functie care ma ajuta sa stabilesc imaginea ce trebuie afisata la un mmoment dat.
        Pt fiecare directie a eroilor am cate 2 stari posibile.Pt fiecare directie exista un vector care pastreaza aceste imagini, avand grija sa nu depasesc indexul.
         abordare similara cu cea din game loop
    */

     public void Update()
    {
        timp_curent+=System.currentTimeMillis()-timp_anterior;
        timp_anterior=System.currentTimeMillis();
        if(timp_curent>timeFrame)
        {
            index++;
            timp_curent=0;
            //if(index>=2)
                if(index>=imagini.length)
            {
                index=0;

            }
        }
    }


}
