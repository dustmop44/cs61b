package byog.Core;

import byog.TileEngine.TETile;

public class PutRoomOnMap {

    public static void PutRoom(room Room, TETile[][] World) {
        for (int i = 0; i < Room.width; i++) {
            for (int j = 0; j < Room.height; j++) {
                World[Room.latitude + i][Room.longitude + j] = Room.drawing[i][j];
            }
        }
    }
}
