package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class PlaceObjects {
    public static int MapWidth = PrepareGame.MapWidth;
    public static int MapHeight = PrepareGame.MapHeight;
    public static Random RANDOM;

    public static Player placeplayer(TETile[][] World) {
        RANDOM = PrepareGame.RANDOM;
        int latitude = RandomUtils.uniform(RANDOM, 0, MapWidth);
        int longitude = RandomUtils.uniform(RANDOM, 0, MapHeight);
        while (World[latitude][longitude] != Tileset.FLOOR) {
            latitude = RandomUtils.uniform(RANDOM, 0, MapWidth);
            longitude = RandomUtils.uniform(RANDOM, 0, MapHeight);
        }
        World[latitude][longitude] = Tileset.PLAYER;
        return new Player(latitude, longitude, World);
    }

    public static Exit placeexit(TETile[][] World) {
        int latitude = RandomUtils.uniform(RANDOM, 0, MapWidth);
        int longitude = RandomUtils.uniform(RANDOM, 0, MapHeight);
        while (World[latitude][longitude] != Tileset.FLOOR) {
            latitude = RandomUtils.uniform(RANDOM, 0, MapWidth);
            longitude = RandomUtils.uniform(RANDOM, 0, MapHeight);
        }
        World[latitude][longitude] = Tileset.FLOWER;
        return new Exit(latitude, longitude, World);
    }

    public static Ghost placeghost(TETile[][] World) {
        int latitude = RandomUtils.uniform(RANDOM, 0, MapWidth);
        int longitude = RandomUtils.uniform(RANDOM, 0, MapHeight);
        while (World[latitude][longitude] != Tileset.FLOOR) {
            latitude = RandomUtils.uniform(RANDOM, 0, MapWidth);
            longitude = RandomUtils.uniform(RANDOM, 0, MapHeight);
        }
        World[latitude][longitude] = Tileset.SAND;
        return new Ghost(latitude, longitude, World);
    }
}
