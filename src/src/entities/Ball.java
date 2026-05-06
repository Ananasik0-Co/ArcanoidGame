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

    public void normalizeSpeed() {
        double currentSpeed = Math.sqrt(dx * dx + dy * dy);
        if (currentSpeed != 0) {
            dx = (dx / currentSpeed) * speed;
            dy = (dy / currentSpeed) * speed;
        }
    }

    public void reverseX() {dx = -dx;}
    public void reverseY() {dy = -dy;}

    public void setY(int Y) {y = Y;}
    public void setX(int X) {x = X;}

    public void setDX(double dx) { this.dx = dx; }
    public void setDY(double dy) { this.dy = dy; }
    public double getDX() { return dx; }
    public double getDY() { return dy; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
}