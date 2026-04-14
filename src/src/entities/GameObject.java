package entities;

import java.awt.Rectangle;
import java.awt.Color;

public abstract class GameObject
{

    protected int x, y;
    protected int width, height;

    protected Color color;

    public GameObject(int x, int y, int width, int height, Color color)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Color getColor() { return color; }


    public Rectangle getBounds()
    {
        return new Rectangle(x, y, width, height);
    }
}