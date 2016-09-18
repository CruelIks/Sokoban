package model;

import java.awt.*;

/**
 * Created by Admin on 17.09.2016.
 */
public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {

        graphics.setColor(new Color(255, 160, 0));
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
        graphics.setColor(new Color(0, 0, 0));
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
        graphics.drawLine(getX(), getY(), getX() + getWidth(), getY() + getHeight());
        graphics.drawLine(getX(), getY() + getHeight(), getX() + getWidth(), getY());

    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);

    }
}
