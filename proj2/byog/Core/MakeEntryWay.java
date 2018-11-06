package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class MakeEntryWay {

    public static void punctureroomtohall(room trunk, room sprout, TETile[][] World) {
        if (sprout.horizontal == 0 && sprout.updownleftright == 0) {
            if (sprout.width > 1) {
                sprout.drawing[sprout.width - 1][1] = Tileset.FLOOR;
            }
            World[trunk.latitude][trunk.longitude + sprout.position] = Tileset.FLOOR;
        } else if (sprout.horizontal == 0 && sprout.updownleftright == 1) {
            if (sprout.width > 1) {
                sprout.drawing[0][1] = Tileset.FLOOR;
            }
            World[trunk.latitude + trunk.width - 1][trunk.longitude + sprout.position] = Tileset.FLOOR;
        } else if (sprout.horizontal == 1 && sprout.updownleftright == 0) {
            if (sprout.height > 1) {
                sprout.drawing[1][0] = Tileset.FLOOR;
            }
            World[sprout.latitude + 1][sprout.longitude - 1] = Tileset.FLOOR;
        } else if (sprout.horizontal == 1 && sprout.updownleftright == 1) {
            if (sprout.height > 1) {
                sprout.drawing[1][sprout.height - 1] = Tileset.FLOOR;
            }
            World[trunk.latitude + sprout.position][trunk.longitude] = Tileset.FLOOR;
        }
    }

    public static void puncturehalltoroom(room sprout, room cap, TETile[][] World) {
        if (sprout.horizontal == 0 && sprout.updownleftright == 0) {
            World[sprout.latitude][sprout.longitude + 1] = Tileset.FLOOR;
            cap.drawing[cap.width - 1][cap.position] = Tileset.FLOOR;
        } else if (sprout.horizontal == 0 && sprout.updownleftright == 1) {
            World[sprout.latitude + sprout.width - 1][sprout.longitude + 1] = Tileset.FLOOR;
            cap.drawing[0][cap.position] = Tileset.FLOOR;
        } else if (sprout.horizontal == 1 && sprout.updownleftright == 0) {
            World[sprout.latitude + 1][sprout.longitude + sprout.height - 1] = Tileset.FLOOR;
            cap.drawing[cap.position][0] = Tileset.FLOOR;
        } else if (sprout.horizontal == 1 && sprout.updownleftright == 1) {
            World[sprout.latitude + 1][sprout.longitude] = Tileset.FLOOR;
            cap.drawing[cap.position][cap.height - 1] = Tileset.FLOOR;
        }
    }
}
