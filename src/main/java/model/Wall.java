package model;

import java.awt.*;

/**
 * Created by Admin on 17.09.2016.
 */
public class Wall extends CollisionObject {
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(new Color(150, 75, 0));
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
        graphics.drawLine(getX() + getWidth() / 2, getY(), getX() + getWidth() / 2, getY() + getHeight());
        graphics.drawLine(getX(), getY() + getHeight() / 2, getX() + getWidth(), getY() + getHeight() / 2);
    }
}
