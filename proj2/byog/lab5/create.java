package byog.lab5;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;
import java.util.Random;

/**
 * Random sized rooms, connected by hallways.
 * So random branching hallways on random sides of the rooms.
 * So create branches with random frequency at 1 - 4 sides of the room.
 * Random length of branches leading to random size of rooms and repeat.
 * When it reaches a certain max latitude and longitude, it creates a wall
 * and no more creation from there.
 * Oh and wherever the hallways starts, the wall must be a floor and
 * where the rooms sprout a hallway, the wall must be a floor for that tile.
 */

public class create {


    public static TETile[][] createroom(int width, int height) {
        if (width == 0 || height == 0) {
            return null;
        }
        TETile[][] room = new TETile[width][height];
        for (int i = 0; i < width; i++) {
            room[i][0] = Tileset.WALL;
            room[i][height-1] = Tileset.WALL;
        }
        for (int i = 0; i < height; i++) {
            room[0][i] = Tileset.WALL;
            room[width-1][i] = Tileset.WALL;
        }
        for (int i = 1; i < (height-1); i++) {
            for (int j = 1; j < (width -1); j++) {
                room[j][i] = Tileset.FLOOR;
            }
        }
        return room;
    }
}
