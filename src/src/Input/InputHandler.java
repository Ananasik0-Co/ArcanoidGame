package Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter
{
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    public boolean enterPressed = false;
    public boolean escPressed = false;
    public boolean rPressed = false;

    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT ||  key == KeyEvent.VK_A) isMovingLeft = true;
        if (key == KeyEvent.VK_RIGHT ||  key == KeyEvent.VK_D) isMovingRight = true;

        if (key == KeyEvent.VK_ENTER) enterPressed = true;
        if (key == KeyEvent.VK_ESCAPE) escPressed = true;
        if (key == KeyEvent.VK_R) rPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT ||  key == KeyEvent.VK_A) isMovingLeft = false;
        if (key == KeyEvent.VK_RIGHT ||  key == KeyEvent.VK_D) isMovingRight = false;
    }

    public boolean isMovingLeft() { return isMovingLeft; }
    public boolean isMovingRight() { return isMovingRight; }
}
