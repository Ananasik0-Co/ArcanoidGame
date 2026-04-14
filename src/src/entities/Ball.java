package entities;

import java.awt.Color;

public class Ball extends GameObject
{

    private double dx, dy;

    private double speed = 5.0;

    public Ball(int x, int y, int rad, Color color)
    {
        super(x, y, rad, rad, color);

        this.dx = speed;
        this.dy = -speed;
    }

    public void multiplySpeed(double speed)
    {
        this.dx *= speed;
        this.dy *= speed;
    }

    public void move()
    {
        x += (int)dx;
        y += (int)dy;
    }

    public void reverseX() {dx = -dx;}
    public void reverseY() {dy = -dy;}
}