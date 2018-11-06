package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Ghost implements MapObject{
    public int latitude;
    public int longitude;
    public TETile[][] World;
    public int direction;
    public MoveObject Move;
    public static TETile Avatar = Tileset.SAND;

    public Ghost(int glatitude, int glongitude, TETile[][] gWorld) {
        latitude = glatitude;
        longitude = glongitude;
        World = gWorld;
        direction = 0;
        Move = new MoveObject(this);
    }


    public void update() {
        while(true) {
            if (direction == 0) {
                if (World[latitude-1][longitude] != Tileset.WALL) {
                    Move.moveleft();
                    direction = 2;
                    return;
                } else if (World[latitude][longitude + 1] != Tileset.WALL) {
                    Move.moveup();
                    return;
                } else {
                    direction = 1;
                }
            }


            if (direction == 1) {
                if (World[latitude][longitude + 1] != Tileset.WALL) {
                    Move.moveup();
                    direction = 0;
                    return;
                } else if (World[latitude + 1][longitude] != Tileset.WALL) {
                    Move.moveright();
                    return;
                } else {
                    direction = 3;
                }
            }


            if (direction == 2) {
                if (World[latitude][longitude - 1] != Tileset.WALL) {
                    Move.movedown();
                    direction = 3;
                    return;
                } else if (World[latitude - 1][longitude] != Tileset.WALL) {
                    Move.moveleft();
                    return;
                } else {
                    direction = 0;
                }
            }

            if (direction == 3) {
                if (World[latitude + 1][longitude] != Tileset.WALL) {
                    Move.moveright();
                    direction = 1;
                    return;
                } else if (World[latitude][longitude - 1] != Tileset.WALL) {
                    Move.movedown();
                    return;
                } else {
                    direction = 2;
                }
            }
        }
    }


    public int latitude() {
        return latitude;
    }

    public int longitude() {
        return longitude;
    }

    public TETile[][] World() {
        return World;
    }

    public TETile Avatar() {
        return Avatar;
    }

    public void setlatitude(int newlatitude) {
        latitude = newlatitude;
    }

    public void setlongitude(int newlongitude) {
        longitude = newlongitude;
    }
}
