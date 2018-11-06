package byog.Core;

public class Rearray {

    public static room[] rearray(room[] rooms) {
        int count = 0;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null) {
                count++;
            }
        }
        room[] rearrayed = new room[count];
        count = 0;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null) {
                rearrayed[count] = rooms[i];
                count++;
            }
        }
        return rearrayed;
    }

    public static room[] rearray(room[][] roomsarray) {
        int count = 0;
        for (int i = 0; i < roomsarray.length; i++) {
            for (int j = 0; j < roomsarray[i].length; j++) {
                if (roomsarray[i][j] != null) {
                    count++;
                }
            }
        }
        room[] rearrayed = new room[count];
        count = 0;
        for (int i = 0; i < roomsarray.length; i++) {
            for (int j = 0; j < roomsarray[i].length; j++) {
                if (roomsarray[i][j] != null) {
                    rearrayed[count] = roomsarray[i][j];
                    count++;
                }
            }
        }
        return rearrayed;
    }
}
