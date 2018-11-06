package byog.Core;

import byog.TileEngine.TETile;

import java.util.Random;

public class CapperSprouter {
    public static Random RANDOM;
    public TETile[][] World;

    public CapperSprouter(TETile[][] BlankishWorld) {
        World = BlankishWorld;
        RANDOM = Map.RANDOM;
        MakeCaps.AssignRandom();
        MakeSprouts.AssignRandom();
        CreateHallwayObject.AssignRandom();
    }


    public room[] sprouter(room trunk) {
        int numsprouts = RandomUtils.uniform(RANDOM, 20, 26);
        room[] sprouts = new room[numsprouts];
        for (int i = 0; i < numsprouts; i++) {
            room sprout = MakeSprouts.makesprout(trunk);
            for (int j = 0; j < 100; j++) {
                if (CheckMap.obstructed(sprout, World)) {
                    sprout = MakeSprouts.makesprout(trunk);
                } else {
                    break;
                }
            }
            if (!CheckMap.obstructed(sprout, World)) {
                MakeEntryWay.punctureroomtohall(trunk, sprout, World);
                PutRoomOnMap.PutRoom(sprout, World);
                sprouts[i] = sprout;
            } else {
                sprouts[i] = null;
            }
        }
        return Rearray.rearray(sprouts);
    }

    public room capper(room sprout) {
        int whethercap = RandomUtils.uniform(RANDOM, 1, 20);
        if (whethercap == 0) {
            return null;
        } else {
            room cap = MakeCaps.makecap(sprout);
            for (int j = 0; j < 100; j++) {
                if (CheckMap.obstructed(cap, World)) {
                    cap = MakeCaps.makecap(sprout);
                } else {
                    break;
                }
            }
            if (!CheckMap.obstructed(cap, World)) {
                MakeEntryWay.puncturehalltoroom(sprout, cap, World);
                PutRoomOnMap.PutRoom(cap, World);
                return cap;
            } else {
                return null;
            }
        }
    }
}
