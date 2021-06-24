package PaooGame;
import PaooGame.GameWindow.GameWindow;

/*! \class Main
    \brief Clasa de unde se porneste jocul.
 */
public class Main
{
    public static void main(String[] args)
    {
        //Game paooGame = new Game("R&M", 1200, 768);
        Game paooGame=Game.getInstance("R&M", 1200, 768);//sablon singleton
        paooGame.StartGame();
    }
}
