package byog.Core;

import java.util.Random;

public class CreateHallwayObject {
    public static Random RANDOM;
    public static int minlength = 2;
    public static int maxlength = 3;

    public static void AssignRandom() {
        RANDOM = Map.RANDOM;
    }

    public static room CreateLeftHallway(room ROOM) {
        int position = RandomUtils.uniform(RANDOM, 1, ROOM.height - 1);
        int width = RandomUtils.uniform(RANDOM, minlength, maxlength);
        int height = 3;
        int latitude = ROOM.latitude - width;
        int longitude = ROOM.longitude + position - 1;
        room hallway = new room(width, height, latitude, longitude);
        hallway.horizontal = 0;
        hallway.updownleftright = 0;
        hallway.position = position;
        return hallway;
    }

    public static room CreateRightHallway(room ROOM) {
        int position = RandomUtils.uniform(RANDOM, 1, ROOM.height - 1);
        int width = RandomUtils.uniform(RANDOM, minlength, maxlength);
        int height = 3;
        int latitude = ROOM.latitude + ROOM.width;
        int longitude = ROOM.longitude + position - 1;
        room hallway = new room(width, height, latitude, longitude);
        hallway.horizontal = 0;
        hallway.updownleftright = 1;
        hallway.position = position;
        return hallway;
    }

    public static room CreateUpHallway(room ROOM) {
        int position = RandomUtils.uniform(RANDOM, 1, ROOM.width - 1);
        int width = 3;
        int height = RandomUtils.uniform(RANDOM, minlength, maxlength);
        int latitude = ROOM.latitude + position - 1;
        int longitude = ROOM.longitude + ROOM.height;
        room hallway = new room(width, height, latitude, longitude);
        hallway.horizontal = 1;
        hallway.updownleftright = 0;
        hallway.position = position;
        return hallway;
    }

    public static room CreateDownHallway(room ROOM) {
        int position = RandomUtils.uniform(RANDOM, 1, ROOM.width - 1);
        int width = 3;
        int height = RandomUtils.uniform(RANDOM, minlength, maxlength);
        int latitude = ROOM.latitude + position - 1;
        int longitude = ROOM.longitude - height;
        room hallway = new room(width, height, latitude, longitude);
        hallway.horizontal = 1;
        hallway.updownleftright = 1;
        hallway.position = position;
        return hallway;
    }
}
