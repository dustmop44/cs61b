package byog.lab5;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class room {
    public static int frameheight;
    public static int framewidth;
    public int latitude;
    public int longitude;
    public int width;
    public int length;
    public int perimeter;
    public TETile[][] drawing;
    public Random RANDOM;
    public int position;
    public int side;

    public room() {
        }

    public room(int rlatitude, int rlongitude, Random random) {
        latitude = rlatitude;
        longitude = rlongitude;
        RANDOM = random;
        width = 5;
        length = 5;
        //width = RandomUtils.uniform(RANDOM, 4, 12);
        //length = RandomUtils.uniform(RANDOM, 4, 12);
        TETile[][] world = Generation.world;


        if (width == 0 || length == 0) {
            System.out.println("Drawing null");
            drawing = null;
            return;
        }
        perimeter = width + width + length + length - 4;

        drawing = create.createroom(width, length);
    }

    public room(int rlatitude, int rlongitude, int rwidth, int rlength) {
        latitude = rlatitude;
        longitude = rlongitude;
        width = rwidth;
        length = rlength;

        /*
        if (latitude + width >= framewidth) {
            System.out.println("Resize width");
            this.width = width - (latitude + width - framewidth);
        }
        if (longitude + length >= frameheight) {
            System.out.println("Resize height");
            length = length - (longitude + length -frameheight);
        }
        */
        if (width == 0 || length == 0) {
            System.out.println("Drawing null");
            drawing = null;
            return;
        }
        perimeter = width + width + length + length - 4;

        drawing = create.createroom(width, length);
    }

    public boolean notobstructed() {
        if (longitude < 0 || latitude < 0 || longitude + length >= frameheight || latitude + width >= framewidth) {
            if (longitude < 0) {
                int d = longitude;
                length = length + longitude;
                longitude = 0;
                drawing = create.createroom(width, length);
                if (side == 1 || side == 3) {
                    position = position + d;
                }
            }
            if (latitude < 0) {
                int d = latitude;
                width = width + latitude;
                latitude = 0;
                drawing = create.createroom(width, length);
                if (side == 2 || side == 4) {
                    position = position + d;
                }
            }
            if (longitude + length >= frameheight) {
                length = length - (longitude + length -frameheight);
            }
            if (latitude + width >= framewidth) {
                this.width = width - (latitude + width - framewidth);
            }
            drawing = create.createroom(width, length);
            room room = this;
            if (side == 1) {
                room.drawing[0][room.position] = Tileset.FLOOR;
            } else if (side == 2) {
                room.drawing[room.position][0] = Tileset.FLOOR;
            } else if (side == 3) {
                room.drawing[room.width - 1][room.position] = Tileset.FLOOR;
            } else if (side == 4){
                room.drawing[room.position][room.length - 1] = Tileset.FLOOR;
            }
        }

        if (side == 1) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < length; j++) {
                    if (Generation.world[latitude + i][longitude + j] != null) {
                        if (i == 0 && (j == position || j == position - 1 || j == position + 1)) {
                            continue;
                        }
                        return false;
                    }
                }
            }
        } else if (side == 2) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++ ) {
                    if (Generation.world[latitude + j][longitude + i] != null) {
                        if (i == 0 && (j == position || j == position - 1 || j == position + 1)) {
                            continue;
                        }
                        return false;
                    }
                }
            }
            return true;
        } else if (side == 3) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++ ) {
                    if (Generation.world[latitude + j][longitude + i] != null) {
                        if (j == width - 1 && (i == position || i == position - 1 || i == position + 1)) {
                            continue;
                        }
                        return false;
                    }
                }
            }
            return true;
        } else if (side == 4) {
            for (int i = 0; i < length; i ++) {
                for (int j = 0; j < width; j++) {
                    if (Generation.world[latitude + j][longitude + i] != null) {
                        if (i == length - 1 && (j == position || j == position -1 || j == position + 1)) {
                            continue;
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
