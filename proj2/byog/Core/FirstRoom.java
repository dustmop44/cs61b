package byog.Core;

import byog.TileEngine.TETile;

public class FirstRoom {
    public TETile[][] World;
    public room firstroom;
    public room[] firstroomarray;

    public FirstRoom(TETile[][] BlankWorld) {
        World = BlankWorld;
        firstroom = CreateRoomObject.CreateFirstRoom();
        PutRoomOnMap.PutRoom(firstroom, World);
        firstroomarray = new room[]{firstroom};
    }

    public void CreateRest() {
        Spawn Spawner = new Spawn(World);
        Spawner.SpawnHallways(firstroomarray);
    }
}
