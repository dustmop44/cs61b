package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class BlindWorld {

    public static TETile[][] update(TETile[][] RealWorld, Player PLAYER) {
        int lat = PLAYER.latitude;
        int viewminlong = PLAYER.longitude - 5;
        int viewmaxlong = PLAYER.longitude + 5;
        int viewminlat = PLAYER.latitude - 5;
        int viewmaxlat = PLAYER.latitude + 5;
        int WorldWidth = RealWorld.length;
        int WorldHeight = RealWorld[0].length;
        TETile[][] BlindedWorld = new TETile[WorldWidth][WorldHeight];
        for (int i = 0; i < WorldWidth; i++) {
            for (int j = 0; j< WorldHeight; j++) {
                if ((j == viewminlong || j == viewmaxlong) && i >= lat - 2 && i <= lat + 2) {
                    BlindedWorld[i][j] = RealWorld[i][j];
                } else if ((j == viewminlong + 1 || j == viewmaxlong - 1) && i >= lat - 3 && i <= lat + 3) {
                    BlindedWorld[i][j] = RealWorld[i][j];
                } else if ((j == viewminlong + 2 || j == viewmaxlong - 2) && i >= lat - 4 && i <= lat + 4) {
                    BlindedWorld[i][j] = RealWorld[i][j];
                } else if ((j == viewminlong + 3 || j == viewmaxlong - 3) && i >= lat - 5 && i <= lat + 5) {
                    BlindedWorld[i][j] = RealWorld[i][j];
                } else if ((j == viewminlong + 4 || j == viewmaxlong - 4) && i >= lat - 5 && i <= lat + 5) {
                    BlindedWorld[i][j] = RealWorld[i][j];
                } else if ((j == viewminlong + 5 || j == viewmaxlong - 5) && i >= lat - 5 && i <= lat + 5) {
                    BlindedWorld[i][j] = RealWorld[i][j];
                } else {
                    BlindedWorld[i][j] = Tileset.NOTHING;
                }
            }
        }
        return BlindedWorld;
    }
}
