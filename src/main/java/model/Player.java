package model;

import java.awt.*;

/**
 * Created by Admin on 17.09.2016.
 */
public class Player extends CollisionObject implements Movable {
    public Player(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        //graphics.drawOval(getX(), getY(), getWidth(), getHeight());
        graphics.setColor(new Color(255, 255, 0));
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
        /*graphics.fillOval(getX() + getWidth() / 4, getY(), getWidth() / 3, getHeight() / 3);*/


    }

    @Override
    public void move(int x, int y)
    {
        setX(getX() + x);
        setY(getY() + y);
    }
}
