package byog.Core;

import java.util.Random;

public class MakeSprouts {
    public static Random RANDOM;

    public static void AssignRandom() {
        RANDOM = Map.RANDOM;
    }

    public static room makesprout(room ROOM) {
        int horizontal = RandomUtils.uniform(RANDOM,0, 2);
        if (horizontal == 0) {
            int leftright = RandomUtils.uniform(RANDOM, 0, 2);
            if (leftright == 0) {
                return CreateHallwayObject.CreateLeftHallway(ROOM);
            } else if (leftright == 1) {
                return CreateHallwayObject.CreateRightHallway(ROOM);
            }
        } else if (horizontal == 1) {
            int updown = RandomUtils.uniform(RANDOM, 0, 2);
            if (updown == 0) {
                return CreateHallwayObject.CreateUpHallway(ROOM);
            } else if (updown == 1) {
                return CreateHallwayObject.CreateDownHallway(ROOM);
            }
        }
        return new room();
    }
}
