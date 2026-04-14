package entities;

import java.awt.Color;

public class Paddle extends GameObject
{

    public Paddle(int x, int y, int width, int height, Color color)
    {
        super(x, y, width, height, color);
    }

    public void SetX(int x) {this.x = x;}

    public int getX() { return x; }
}