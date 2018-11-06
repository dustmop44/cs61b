package byog.Core;

import byog.TileEngine.TETile;

import java.util.Random;

public class Map {
    public static Random RANDOM;
    public TETile[][] World;
    public static int MapWidth;
    public static int MapHeight;

    public Map(int width, int height, Random rand) {
        RANDOM = rand;
        MapWidth = width;
        MapHeight = height;
        World = new TETile[width][height];
    }

    public TETile[][] CreateMap() {
        FirstRoom FIRSTROOM = new FirstRoom(World);
        Spawn Spawner = new Spawn(World);
        Spawner.SpawnHallways(FIRSTROOM.firstroomarray);
        return World;
    }
}
