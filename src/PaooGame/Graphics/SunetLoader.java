package PaooGame.Graphics;
import javax.sound.sampled.*;
import java.io.IOException;

//https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/package-summary.html
//https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
//Sampled Audio: Sampled audio is represented as a sequence of time-sampled data of the amplitude of sound wave. It is supported in package javax.sound.sampled. The supported file formats are: "wav", "au" and "aiff".
public class SunetLoader
{
    /*incarc un stream audio intr-un obiect AudioInputStream si returnez o referinta catre acesta*/
    public static AudioInputStream load_sunet(String path)
    {
        ///Sunt situatii in care nu pot sa accesez fisierul sursa=> arunca exceptie ce trebuie sa fie tratata
        try
        {
             return AudioSystem.getAudioInputStream(SunetLoader.class.getResource(path));//Obtains an audio input stream from the provided File.(site oracle)

        }
        catch (UnsupportedAudioFileException e)
        {
            e.printStackTrace();// printeaza informatiile necesare depanarii.
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static void setVolume(Clip clip, int volume)
    {
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = control.getMinimum();
        float result = range * (1 - volume / 100.0f);
        control.setValue(result);
    }

}

