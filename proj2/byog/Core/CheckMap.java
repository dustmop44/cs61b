package byog.Core;

import byog.TileEngine.TETile;

public class CheckMap {

    public static Boolean obstructed(room ROOM, TETile[][] World) {
        int latitude = ROOM.latitude;
        int longitude = ROOM.longitude;
        int WorldWidth = World.length;
        int WorldHeight = World[0].length;
        if (latitude + ROOM.width >= WorldWidth || latitude < 0) {
            return true;
        } else if (longitude + ROOM.height >= WorldHeight || longitude < 0) {
            return true;
        }
        for (int i = 0; i < ROOM.width; i++) {
            for (int j = 0; j < ROOM.height; j++) {
                if (World[latitude + i][longitude + j] != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
