package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Player {
    public TETile[][] World;
    public static TETile Avatar = Tileset.WATER;
    public static int latitude;
    public static int longitude;

    public Player(int startlatitude, int startlongitude, TETile[][] WORLD) {
        latitude = startlatitude;
        longitude = startlongitude;
        World = WORLD;
    }

    public void moveup() {
        if (World[latitude][longitude + 1] != Tileset.WALL) {
            World[latitude][longitude + 1] = Avatar;
            World[latitude][longitude] = Tileset.FLOOR;
            longitude = longitude + 1;
        }
    }

    public void movedown() {
        if (World[latitude][longitude - 1] != Tileset.WALL) {
            World[latitude][longitude - 1] = Avatar;
            World[latitude][longitude] = Tileset.FLOOR;
            longitude = longitude - 1;
        }
    }

    public void moveleft() {
        if (World[latitude - 1][longitude] != Tileset.WALL) {
            World[latitude - 1][longitude] = Avatar;
            World[latitude][longitude] = Tileset.FLOOR;
            latitude = latitude - 1;
        }
    }

    public void moveright() {
        if (World[latitude + 1][longitude] != Tileset.WALL) {
            World[latitude + 1][longitude] = Avatar;
            World[latitude][longitude] = Tileset.FLOOR;
            latitude = latitude + 1;
        }
    }

}
