package PaooGame.Input;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/*! \class MouseManager implements MouseListener, MouseMotionListener
    \brief Clasa utila pt a controla input ul de la mouse.

    Daca a fost apasat un click se seteaza corespunzator un flag
    In program trebuie sa se tina cont de flagul aferent click-ului de interes. Daca flagul respectiv este true inseamna
    ca click-ul respectiv a fost apasat, iar daca este false, nu a fost apasat.
 */
public class MouseManager implements MouseListener, MouseMotionListener
{
    private boolean l_click, d_click;
    private int mouseX, mouseY;
    public MouseManager() {}

    /*! \fn public void mouseMoved(MouseEvent e)
        \brief Functie cu care obtii pozitia mouse-ului pe ecran, pozitie definita ca un punct cu anumite coordonate
     */
     @Override
    public void mouseMoved(MouseEvent e)
    {
        mouseX=e.getX();
        mouseY=e.getY();

    }


    /*! \fn  public void mousePressed(MouseEvent e)
        \brief Aceasta metoda va fi apelata atunci cand click-ul  este apasat, indiferent de click. BUTTON1 echivalwnt cu click stanga, BUTTON 3 e echivalent cu click dreapta

      */
    @Override
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton()==MouseEvent.BUTTON1)//button1 e click stanga
        {
            l_click=true;
            System.out.println(e.getX()+", "+e.getY());

        }
        else if(e.getButton()==MouseEvent.BUTTON3)//button3 e click dreapta
        {
            d_click=true;
        }

    }
    /*! \fn public void mouseReleased(MouseEvent e)
            \brief  Aceasta metoda va fi apelata atunci cand click-ul nu mai este apasat, indiferent de click

         */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(e.getButton()==MouseEvent.BUTTON1)//button1 e click stanga
        {
            l_click=false;
        }
        else if(e.getButton()==MouseEvent.BUTTON3)//button3 e click dreapta
        {
            d_click=false;
        }
    }

    public int getMouseX()
    {
        return mouseX;
    }

    public int getMouseY()
    {
        return mouseY;
    }

    public boolean click_dreapta(){
        return d_click;
    }
    public boolean click_stanga(){
        return l_click;
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}