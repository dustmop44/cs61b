package byog.lab5;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class draw {
    public static int maxwidth;
    public static int maxheight;


    public draw(int framewidth, int frameheight) {
        maxwidth = framewidth;
        maxheight = frameheight;
    }


    public void drawroom(room roomtodraw, TETile[][] world) {
        TETile[][] drawing = roomtodraw.drawing;
        if (drawing == null) {
            return;
        }
        int latitude = roomtodraw.latitude;
        int longitude = roomtodraw.longitude;
        for (int i = 0; i < roomtodraw.width; i++) {
            for (int j = 0; j < roomtodraw.length; j++) {
                if (i + latitude == maxwidth - 1 || j + longitude == maxheight - 1) {
                    world[i + latitude][j + longitude] = Tileset.WALL;
                } else {
                //} else if (i + latitude >= maxwidth || j + longitude >= maxheight) {
                //    continue;
                    world[i + latitude][j + longitude] = drawing[i][j];
                }
            }
        }
    }

    public void drawhallway(hallway roomtodraw, TETile[][] world) {
        TETile[][] drawing = roomtodraw.drawing;
        int latitude = roomtodraw.latitude;
        int longitude = roomtodraw.longitude;
        for (int i = 0; i < roomtodraw.width; i++) {
            for (int j = 0; j < roomtodraw.length; j++) {
                if (i + latitude == maxwidth - 1 || j + longitude == maxheight - 1) {
                    world[i + latitude][j + longitude] = Tileset.WALL;
                //} else if (i + latitude >= maxwidth || j + longitude >= maxheight) {
                //    continue;
                } else {
                world[i + latitude][j + longitude] = drawing[i][j];
                }
            }
        }
    }
}
