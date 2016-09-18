package model;

import controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

/**
 * Created by Admin on 17.09.2016.
 */
public class Model {

    /**
     * int FIELD_SELL_SIZE - size of game field's sell
     */
    public static final int FIELD_SELL_SIZE = 30;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    /*private LevelLoader levelLoader = new LevelLoader(Paths.get("src\\main\\java\\res\\levels.txt"));*/
    private LevelLoader levelLoader = new LevelLoader(Paths.get("/levelsRes.txt"));

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void restartLevel(int level)
    {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart()
    {
        restartLevel(currentLevel);
    }

    public void startNextLevel()
    {
        currentLevel++;
        restart();
    }

    public void move(Direction direction)
    {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) {
            return;
        }
        if (checkBoxCollision(direction)){
            return;
        }

        //move player
        switch (direction)
        {
            case UP:
            {
                player.move(0, -FIELD_SELL_SIZE);
                break;
            }
            case DOWN:
            {
                player.move(0, FIELD_SELL_SIZE);
                break;
            }
            case LEFT:
            {
                player.move(-FIELD_SELL_SIZE, 0);
                break;
            }
            case RIGHT:
            {
                player.move(FIELD_SELL_SIZE, 0);
                break;
            }
        }

        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {

        Set<Wall> walls = gameObjects.getWalls();
        for (Wall wall : walls)
        {
            if (gameObject.isCollision(wall, direction))
            {
                return true;
            }
        }

        return false;
    }

    public boolean checkBoxCollision(Direction direction)
    {
        Player player = gameObjects.getPlayer();
        Set<Box> box = gameObjects.getBoxes();

        for (Box box1 : box)
        {
            if (player.isCollision(box1, direction))
            {

                //we meet a box, can we move it?
                if (checkWallCollision(box1, direction))
                {
                    return true;
                }

                for (Box box2 : box)
                {
                    if (box1.equals(box2))
                    {
                        continue;
                    }

                    if (box1.isCollision(box2, direction))
                    {
                        return true;
                    }
                }

                //yes we can
                switch (direction)
                {
                    case UP:
                    {
                        box1.move(0, -FIELD_SELL_SIZE);
                        break;
                    }
                    case DOWN:
                    {
                        box1.move(0, FIELD_SELL_SIZE);
                        break;
                    }
                    case LEFT:
                    {
                        box1.move(-FIELD_SELL_SIZE, 0);
                        break;
                    }
                    case RIGHT:
                    {
                        box1.move(FIELD_SELL_SIZE, 0);
                        break;
                    }

                }


            }
        }


        return false;
    }

    public void checkCompletion()
    {

        Set<Box> box = gameObjects.getBoxes();
        Set<Home> homes = gameObjects.getHomes();

        for (Home home : homes)
        {
            boolean foundBox = false;
            for (Box box1 : box)
            {
                if (home.getX() == box1.getX() && home.getY() == box1.getY())
                {
                    foundBox = true;
                    break;
                }
            }

            if (!foundBox)
            {
                return;
            }

        }

        eventListener.levelCompleted(currentLevel);

    }
}
