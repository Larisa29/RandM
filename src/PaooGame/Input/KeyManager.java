package PaooGame.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*! \class public class KeyManager implements KeyListener
    \brief Gestioneaza intrarea (input-ul) de tastatura.

    Clasa citeste daca au fost apasata o tasta, stabiliteste ce tasta a fost actionata si seteaza corespunzator un flag.
    In program trebuie sa se tina cont de flagul aferent tastei de interes. Daca flagul respectiv este true inseamna
    ca tasta respectiva a fost apasata si false nu a fost apasata.
 */
public class KeyManager implements KeyListener
{
    private boolean[] keys; /*!< Vector de flaguri pentru toate tastele. Tastele vor fi regasite dupa cod [0 - 255]*/
    public boolean up;      /*!< Flag pentru tasta "sus W" apasata.*/
    public boolean down;    /*!< Flag pentru tasta "jos S" apasata.*/
    public boolean left;    /*!< Flag pentru tasta "stanga A" apasata.*/
    public boolean right;   /*!< Flag pentru tasta "dreapta D" apasata.*/
    public boolean sus;      /*!< Flag pentru tasta "sageata sus" apasata.*/
    public boolean jos;      /*!< Flag pentru tasta "sageata jos" apasata.*/
    public boolean stanga;      /*!< Flag pentru tasta "sageata stanga" apasata.*/
    public boolean dreapta;      /*!< Flag pentru tasta "sageata dreapta" apasata.*/
    public boolean ESC;         /*!< Flag pentru tasta "esc" apasata.*/
    public boolean ENTER;         /*!< Flag pentru tasta "enter" apasata.*/
    public boolean invizibil;  /*!< Flag pentru tasta "space" apasata.*/
    /*! \fn public KeyManager()
        \brief Constructorul clasei.
     */
    public KeyManager()
    {
        ///Constructie vector de flaguri aferente tastelor.
        keys = new boolean[256];
    }


    public void Update()
    {
        up    = keys[KeyEvent.VK_W];
        down  = keys[KeyEvent.VK_S];
        left  = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        sus = keys[KeyEvent.VK_UP];
        jos=keys[KeyEvent.VK_DOWN];
        stanga= keys[KeyEvent.VK_LEFT];
        dreapta=keys[KeyEvent.VK_RIGHT];
        ESC=keys[KeyEvent.VK_ESCAPE];
        ENTER=keys[KeyEvent.VK_ENTER];
        invizibil=keys[KeyEvent.VK_SPACE];

    }

    /*! \fn public void keyPressed(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de tasta apasata este generat.

         \param e obiectul eveniment de tastatura.
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        ///se retine in vectorul de flaguri ca o tasta a fost apasata.
        keys[e.getKeyCode()] = true;
    }

    /*! \fn public void keyReleased(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de tasta eliberata este generat.

         \param e obiectul eveniment de tastatura.
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        ///se retine in vectorul de flaguri ca o tasta a fost eliberata.
        keys[e.getKeyCode()] = false;
    }

    /*! \fn public void keyTyped(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand o tasta a fost apasata si eliberata.
        Momentan aceasta functie nu este utila in program.
     */
    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
