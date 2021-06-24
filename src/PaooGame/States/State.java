package PaooGame.States;

import java.awt.*;
import PaooGame.RefLinks;

/*! \class State
    \brief Implementeaza notiunea abstracta de stare a jocului/programului.

    Un joc odata ce este lansat in executie nu trebuie "sa arunce jucatorul direct in lupta", este nevoie de
    un meniu care sa contine optiuni: New Game, Load Game, Settings, About etc. Toate aceste optiuni nu sunt altceva
    decat stari ale programului (jocului) ce trebuiesc incarcate si afisate functie de starea curenta.
 */
public abstract class State
{
    ///Urmatoarele atribute sunt statice pentru a evita dealocarea spatiului de memorie la trecerea dintr-o stare in alta.
    private static State previousState  = null; /*!< Referinta catre starea anterioara a jocului.*/
    private static State currentState   = null; /*!< Referinta catre starea curenta a jocului: game, meniu, settings, about etc.*/
    protected RefLinks refLink;
    static long now;
    static long then;
    static boolean flag=false;

    public State(RefLinks refLink)
    {
        this.refLink = refLink;
    }

    /*! \fn public static void SetState(State state)
        \brief Seteaza starea curenta a jocului.

        \param state Noua stare a programului (jocului).
     */
    public static void SetState(State state)
    {
        previousState = currentState;
        currentState = state;
    }

    public static State GetState()
    {
        return currentState;
    }
    public static State GetPreviousState() { return previousState; }


    ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update();
    ///Metoda abstracta destinata desenarii starii curente
    public abstract void Draw(Graphics g);

    /*! \fn  public static boolean secondElapsed()
        \brief Metoda marcheaza trecerea unei secunde in joc.
     */
    public static boolean secondElapsed()
    {
        if(!flag)   //flag pe false
        {
            then= System.nanoTime();
            flag=true;
        }
        now=System.nanoTime();      //System.nanoTime() returns the current value of the running Java Virtual Machine’s high-resolution time source, in nanoseconds.
        if(now-then>=1000000000)
        {
            flag=false;
            return true;
        }
        return false;
    }
}