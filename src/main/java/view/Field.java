package view;

import controller.EventListener;
import model.Direction;
import model.GameObject;
import model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

/**
 * Created by Admin on 17.09.2016.
 */
public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler keyHandler = this.new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void paint(Graphics g) {

        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        GameObjects gameObjects = view.getGameObjects();

        Set<GameObject> allObjects = gameObjects.getAll();
        for (GameObject allObject : allObjects) {
            allObject.draw(g);
        }


    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_LEFT: {
                    eventListener.move(Direction.LEFT);
                    break;
                }
                case KeyEvent.VK_RIGHT: {
                    eventListener.move(Direction.RIGHT);
                    break;
                }
                case KeyEvent.VK_UP: {
                    eventListener.move(Direction.UP);
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    eventListener.move(Direction.DOWN);
                    break;
                }
                case KeyEvent.VK_R: {
                    eventListener.restart();
                    break;
                }
            }
        }
    }
}
