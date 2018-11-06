package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Exit {
    public static TETile[][] World;
    public static TETile Avatar = Tileset.FLOWER;
    public static int latitude;
    public static int longitude;

    public Exit(int startlatitude, int startlongitude, TETile[][] WORLD) {
        latitude = startlatitude;
        longitude = startlongitude;
        World = WORLD;
    }
}
