package byog.Core;

import java.util.Random;

public class MakeCaps {
    public static Random RANDOM;

    public static void AssignRandom() {
        RANDOM = Map.RANDOM;
    }

    public static room makecap(room sprout) {
        if (sprout.horizontal == 0 && sprout.updownleftright == 0) {
            return CreateRoomObject.CreateLeftCap(sprout);
        } else if (sprout.horizontal == 0 && sprout.updownleftright == 1) {
            return CreateRoomObject.CreateRightCap(sprout);
        } else if (sprout.horizontal == 1 && sprout.updownleftright == 0) {
            return CreateRoomObject.CreateUpCap(sprout);
        } else if (sprout.horizontal == 1 && sprout.updownleftright == 1) {
            return CreateRoomObject.CreateDownCap(sprout);
        }
        return new room();
    }
}
