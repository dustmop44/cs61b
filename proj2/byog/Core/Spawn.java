package byog.Core;

import byog.TileEngine.TETile;

public class Spawn {
    public TETile[][] World;
    public CapperSprouter CS;

    public Spawn(TETile[][] BlankishWorld) {
        World = BlankishWorld;
        CS = new CapperSprouter(World);
    }

    public room[] SpawnRooms(room[] hallways) {
        room[] caps = new room[hallways.length];
        for (int i = 0; i < hallways.length; i++) {
            caps[i] = CS.capper(hallways[i]);
        }
        caps = Rearray.rearray(caps);
        if (caps.length == 0) {
            return caps;
        } else {
            SpawnHallways(caps);
        }
        return caps;
    }

    public room[] SpawnHallways(room[] rooms) {
        room[][] hallwayarray = new room[rooms.length][];
        for (int i = 0; i < rooms.length; i++) {
            hallwayarray[i] = CS.sprouter(rooms[i]);
        }
        room[] hallways = Rearray.rearray(hallwayarray);
        if (hallways.length == 0) {
            return hallways;
        } else {
            SpawnRooms(hallways);
        }
        return hallways;
    }

}
