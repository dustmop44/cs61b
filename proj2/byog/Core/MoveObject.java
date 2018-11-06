package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class MoveObject {
    public TETile[][] World;
    public TETile Avatar;
    public TETile LastTile;
    public MapObject thing;

    public MoveObject(MapObject GhostorPlayer) {
        World = GhostorPlayer.World();
        Avatar = GhostorPlayer.Avatar();
        thing = GhostorPlayer;
        LastTile = Tileset.FLOOR;
    }

    public void moveup() {
        TETile temp = World[thing.latitude()][thing.longitude() + 1];
        World[thing.latitude()][thing.longitude()] = LastTile;
        if (temp == Tileset.SAND) {
            LastTile = Tileset.FLOOR;
        } else {
            LastTile = temp;
        }        World[thing.latitude()][thing.longitude() + 1] = Avatar;
        thing.setlongitude(thing.longitude() + 1);
    }

    public void moveleft() {
        TETile temp = World[thing.latitude()-1][thing.longitude()];
        World[thing.latitude()][thing.longitude()] = LastTile;
        if (temp == Tileset.SAND) {
            LastTile = Tileset.FLOOR;
        } else {
            LastTile = temp;
        }        World[thing.latitude() - 1][thing.longitude()] = Avatar;
        thing.setlatitude(thing.latitude() - 1);
    }

    public void movedown() {
        TETile temp = World[thing.latitude()][thing.longitude() - 1];
        World[thing.latitude()][thing.longitude()] = LastTile;
        if (temp == Tileset.SAND) {
            LastTile = Tileset.FLOOR;
        } else {
            LastTile = temp;
        }        World[thing.latitude()][thing.longitude() - 1] = Avatar;
        thing.setlongitude(thing.longitude() - 1);
    }

    public void moveright() {
        TETile temp = World[thing.latitude()+1][thing.longitude()];
        World[thing.latitude()][thing.longitude()] = LastTile;
        if (temp == Tileset.SAND) {
            LastTile = Tileset.FLOOR;
        } else {
            LastTile = temp;
        }
        World[thing.latitude() + 1][thing.longitude()] = Avatar;
        thing.setlatitude(thing.latitude() + 1);
    }
}
