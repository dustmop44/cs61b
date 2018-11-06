package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class DrawRoom {

    public static TETile[][] DrawRoom(int width, int height) {
        TETile[][] Drawing = new TETile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
                    Drawing[i][j] = Tileset.WALL;
                } else {
                    Drawing[i][j] = Tileset.FLOOR;
                }
            }
        }
        return Drawing;
    }
}
