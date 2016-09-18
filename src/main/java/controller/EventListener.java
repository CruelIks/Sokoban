package controller;

import model.Direction;

/**
 * Created by Admin on 17.09.2016.
 */
public interface EventListener
{
    void move(Direction direction);
    void restart();
    void startNextLevel();
    void levelCompleted(int level);

}