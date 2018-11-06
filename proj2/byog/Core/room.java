package byog.Core;

import byog.TileEngine.TETile;

public class room {
    public int latitude;
    public int longitude;
    public int width;
    public int height;
    public TETile[][] drawing;
    public int position;
    public int horizontal;
    public int updownleftright;

    public room() {

    }

    public room(int rwidth, int rheight, int rlatitude, int rlongitude) {
        width = rwidth;
        height = rheight;
        latitude = rlatitude;
        longitude = rlongitude;
        drawing = DrawRoom.DrawRoom(width, height);
    }
}
