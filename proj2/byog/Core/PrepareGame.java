package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.Random;

public class PrepareGame {
    public static Random RANDOM;
    public static int MapWidth;
    public static int MapHeight;
    public static int seed;
    public TETile[][] World;

    public PrepareGame(int inputseed) {
        seed = inputseed;
        RANDOM = new Random(inputseed);
        MapWidth = 80;
        MapHeight = 45;
        TERenderer ter = new TERenderer();
        ter.initialize(MapWidth, MapHeight);
        Map NewMap = new Map(MapWidth, MapHeight, RANDOM);
        World = NewMap.CreateMap();
    }

    public PrepareGame() {
        seed = seed + 1;
        RANDOM = new Random(seed);
        MapWidth = 80;
        MapHeight = 45;
        TERenderer ter = new TERenderer();
        ter.initialize(MapWidth, MapHeight);
        Map NewMap = new Map(MapWidth, MapHeight, RANDOM);
        World = NewMap.CreateMap();

    }

    public void StartGame() {
        GameInterface Interface = new GameInterface(World);
        InitializeMap.Initialize(Interface, World);
        Interface.RunGame();
    }
}
