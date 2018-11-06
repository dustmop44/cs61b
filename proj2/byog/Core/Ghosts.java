package byog.Core;

import byog.TileEngine.TETile;

public class Ghosts {
    public Ghost[] GhostArray;

    public Ghosts(int numghosts, TETile[][] World) {
        GhostArray = new Ghost[numghosts];
        for (int i = 0; i < GhostArray.length; i++) {
            GhostArray[i] = PlaceObjects.placeghost(World);
        }
    }

    public void update() {
        for (int i = 0; i < GhostArray.length; i++) {
            GhostArray[i].update();
        }
    }

    public int length() {
        return GhostArray.length;
    }
}
