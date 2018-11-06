package byog.Core;

import byog.TileEngine.TETile;

public class InitializeMap {

    public static void Initialize(GameInterface Interface, TETile[][] World) {
        Interface.PLAYER = PlaceObjects.placeplayer(World);
        Interface.EXIT = PlaceObjects.placeexit(World);
        Interface.GHOSTS = new Ghosts(5, World);

        Display.DisplayMap(World);
    }
}
