package byog.Core;

import java.util.Random;

public class CreateRoomObject {
    public static Random RANDOM;

    public static room CreateFirstRoom() {
        RANDOM = Map.RANDOM;
        int latitude = RandomUtils.uniform(RANDOM, 0, Map.MapWidth-3);
        int longitude = RandomUtils.uniform(RANDOM, 0, Map.MapHeight-3);
        return new room(3, 3, latitude, longitude);
    }

    public static room CreateRandomRoom() {
        int width = RandomUtils.uniform(RANDOM, 3, 5);
        int height = RandomUtils.uniform(RANDOM, 3, 5);
        int latitude = RandomUtils.uniform(RANDOM, 0, Map.MapWidth);
        int longitude = RandomUtils.uniform(RANDOM, 0, Map.MapHeight);
        return new room(width, height, latitude, longitude);
    }

    public static room CreateLeftCap(room Sprout) {
        room cap = CreateRandomRoom();
        int position = RandomUtils.uniform(RANDOM, 1, cap.height - 1);
        cap.position = position;
        cap.latitude = Sprout.latitude - cap.width;
        cap.longitude = Sprout.longitude - position + 1;
        return cap;
    }

    public static room CreateRightCap(room Sprout) {
        room cap = CreateRandomRoom();
        int position = RandomUtils.uniform(RANDOM, 1, cap.height - 1);
        cap.position = position;
        cap.latitude = Sprout.latitude + Sprout.width;
        cap.longitude = Sprout.longitude - position + 1;
        return cap;
    }

    public static room CreateUpCap(room Sprout) {
        room cap = CreateRandomRoom();
        int position = RandomUtils.uniform(RANDOM, 1, cap.width - 1);
        cap.position = position;
        cap.latitude = Sprout.latitude - position + 1;
        cap.longitude = Sprout.longitude + Sprout.height;
        return cap;
    }

    public static room CreateDownCap(room Sprout) {
        room cap = CreateRandomRoom();
        int position = RandomUtils.uniform(RANDOM, 1, cap.width - 1);
        cap.position = position;
        cap.latitude = Sprout.latitude - position + 1;
        cap.longitude = Sprout.longitude - cap.height;
        return cap;
    }




}
