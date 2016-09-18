package model;

import java.awt.*;

/**
 * Created by Admin on 17.09.2016.
 */
public class Home extends GameObject {
    public Home(int x, int y)
    {
        /*super(x, y, 2, 2);*/
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(new Color(255, 0, 0));
        graphics.drawOval(getX(), getY(), getWidth(), getHeight());

        graphics.drawLine(getX() + getWidth() / 2, getY(), getX() + getWidth() / 2, getY() + getHeight());
        graphics.drawLine(getX(), getY() + getHeight() / 2, getX() + getWidth(), getY() + getHeight() / 2);

        /*graphics.setColor(Color.WHITE);*/
        graphics.drawOval(getX() + getWidth() / 4, getY() + getHeight() / 4, getWidth() / 2, getHeight() / 2);

    }
}
