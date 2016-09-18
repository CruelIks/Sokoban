package model;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 17.09.2016.
 */
public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public static void main(String[] args) {
        /*new LevelLoader(Paths.get("src\\main\\java\\res\\levels.txt")).getLevel(1);*/

        try (InputStream is = LevelLoader.class.getResourceAsStream("/levelsRes.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))
        ) {
            String tmp = reader.readLine();
            System.out.println("read: " + tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public GameObjects getLevel(int level) {

        GameObjects gameObjects;

        Set<Wall> wallSet = new HashSet<>();
        Set<Box> boxSet = new HashSet<>();
        Set<Home> homeSet = new HashSet<>();
        Player player = null;
        int fieldSize = Model.FIELD_SELL_SIZE;

        int curLvl = level > 60 ? level % 60 : level;
        String currentLevel = "Maze: " + curLvl;

        /*try (BufferedReader reader = new BufferedReader(new FileReader(levels.toString()))) {*/
        try (InputStream is = LevelLoader.class.getResourceAsStream("/levelsRes.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))
        ) {
            while (reader.ready()) {
                String currentString = reader.readLine();
                if (!currentString.equals(currentLevel)) {
                    continue;
                }

                reader.readLine();

                //second tech
                String str2 = reader.readLine();
                int xSize = Integer.parseInt(str2.substring(8).trim());
                /*System.out.println("X position: " + xSize);*/
                //third
                String str3 = reader.readLine();
                int ySize = Integer.parseInt(str3.substring(8).trim());
                /*System.out.println("Y position: " + ySize);*/

                reader.readLine();
                reader.readLine();
                reader.readLine();

                for (int i = 0; i < ySize; i++) {
                    String tmp = reader.readLine();

                    for (int j = 0; j < xSize; j++) {
                        char symbol = tmp.charAt(j);

                        switch (symbol) {
                            case 'X': {
                                wallSet.add(new Wall(fieldSize / 2 + j * fieldSize, fieldSize / 2 + i * fieldSize));
                                break;
                            }
                            case '*': {
                                boxSet.add(new Box(fieldSize / 2 + j * fieldSize, fieldSize / 2 + i * fieldSize));
                                break;
                            }
                            case '.': {
                                homeSet.add(new Home(fieldSize / 2 + j * fieldSize, fieldSize / 2 + i * fieldSize));
                                break;
                            }
                            case '&': {
                                homeSet.add(new Home(fieldSize / 2 + j * fieldSize, fieldSize / 2 + i * fieldSize));
                                boxSet.add(new Box(fieldSize / 2 + j * fieldSize, fieldSize / 2 + i * fieldSize));
                                break;
                            }
                            case '@': {
                                player = new Player(fieldSize / 2 + j * fieldSize, fieldSize / 2 + i * fieldSize);
                                break;
                            }
                        }
                    }

                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        gameObjects = new GameObjects(wallSet, boxSet, homeSet, player);
        return gameObjects;
    }
}
