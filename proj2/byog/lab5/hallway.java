package byog.lab5;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Arrays;
import java.util.Random;

public class hallway extends room {
    public int latitude;
    public int longitude;
    public int width;
    public int length;
    public int perimeter;
    public int horizontal;
    public TETile[][] drawing;
    public Random RANDOM;
    public int side;

    public hallway() {

    }

    public hallway(int rlatitude, int rlongitude, int rside, Random random) {
        latitude = rlatitude;
        longitude = rlongitude;
        side = rside;
        if (side == 1 || side == 3) {
            horizontal = 1;
        } else {
            horizontal = -1;
        }
        RANDOM = random;
        if (horizontal == 1) {
            this.width = RandomUtils.uniform(RANDOM, 3,4);
            this.length = 3;
        } else {
            this.width = 3;
            this.length = RandomUtils.uniform(RANDOM, 3, 4);
        }
        /*
        Also, the failsafe here for drawing off the map wouldn't work with side 1 hallways.
        Also, forgot to include the cutoffs at zero side map.
         */
        if (latitude + width >= framewidth) {
            this.width = width - (latitude + width - framewidth);
        }
        if (longitude + length >= frameheight) {
            length = length - (longitude + length - frameheight);
        }
        if (width <= 1 || length <= 1) {
            System.out.println("Drawing null");
            drawing = null;
            return;
        }
        if (width < 3 && horizontal != 1) {
            System.out.println("Drawing null");
            drawing = null;
            return;
        }
        if (length < 3 && horizontal == 1) {
            System.out.println("Drawing null");
            drawing = null;
            return;
        }
        this.perimeter = width + width + length + length - 4;

        this.drawing = create.createroom(width, length);

    }

    public Boolean obstructed() {
        if (longitude  < 0 || latitude < 0 || latitude + width > framewidth || longitude + length > frameheight) {
            return true;
        }
        if (side == 1) {
            for (int i = 0; i < width-1; i++) {
                for (int j = 0; j < length; j++) {
                    if (Generation.world[latitude + i][longitude + j] != null) {
                        return true;
                    }
                }
            }
            return false;
        } else if (side == 2) {
            for (int i = 0; i < width; i++) {
                for (int j = 1; j < length; j++) {
                    if (Generation.world[latitude + i][longitude + j] != null) {
                        return true;
                    }
                }
            }
            return false;
        } else if (side == 3) {
            for (int i = 1; i < width; i++) {
                for (int j = 0; j < length; j++) {
                    if (Generation.world[latitude + i][longitude + j] != null) {
                        return true;
                    }
                }
            }
            return false;
        } else if (side == 4) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < length - 1; j++) {
                    if (Generation.world[latitude + i][longitude + j] != null) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public void resize() {

        if (longitude < 0 || latitude < 0) {
            if (longitude < 0) {
                length = length + longitude;
                longitude = 0;
                if (length + longitude < 0 || width + latitude < 0) {
                    drawing = null;
                    return;
                }
                drawing = create.createroom(width, length);
            }
            if (latitude < 0) {
                width = width + latitude;
                latitude = 0;
                if (length + longitude < 0 || width + latitude < 0) {
                    drawing = null;
                    return;
                }
                drawing = create.createroom(width, length);
            }
        }

        if (longitude + length >= frameheight) {
            length = length - (longitude + length -frameheight);
        }
        if (latitude + width >= framewidth) {
            this.width = width - (latitude + width - framewidth) - 1;
        }
        if (longitude > frameheight) {
            drawing = null;
            return;
        }
        if (latitude > framewidth) {
            drawing = null;
            return;
        }
        if (side == 3) {
            if (length < 3) {
                drawing = null;
                return;
            }
        }
        if (drawing != null) {
            if (side == 1) {
                int[] lengths = new int[3];
                for (int i = 0; i < 3; i++) {
                    int k = 0;
                    for (int j = 0; j < width - 1; j++) {
                        if (Generation.world[latitude + width - 2 - j][longitude + i] != null) {
                            break;
                        }
                        k++;
                    }
                    lengths[i] = k;
                }
                int min = Arrays.stream(lengths).min().getAsInt();
                if (min == 0) {
                    drawing = null;
                } else {
                    latitude = latitude + width - min - 1;
                    width = min + 1;
                }

            }
            if (side == 2) {
                int[] lengths = new int[3];
                for (int i = 0; i < 3; i++) {
                    int k = 0;
                    for (int j = 0; j < length - 1; j++) {
                        if (Generation.world[latitude + i][longitude + 1 + j] != null) {
                            break;
                        }
                        k++;
                    }
                    lengths[i] = k;
                }
                int min = Arrays.stream(lengths).min().getAsInt();
                if (min == 0) {
                    drawing = null;
                } else {
                    length = min + 1;
                }

            }
            if (side == 3) {
                int[] lengths = new int[3];
                for (int i = 0; i < 3; i++) {
                    int k = 0;
                    for (int j = 0; j < width - 1; j++) {
                        if (Generation.world[latitude + j + 1][longitude + i] != null) {
                            break;
                        }
                        k++;
                    }
                    lengths[i] = k;
                }
                int min = Arrays.stream(lengths).min().getAsInt();

                if (min == 0) {
                    drawing = null;
                } else {
                    width = min + 1;
                }

            }
            if (side == 4) {
                int[] lengths = new int[3];
                for (int i = 0; i < 3; i++) {
                    int k = 0;
                    for (int j = 0; j < length - 1; j++) {
                        if (Generation.world[latitude + i][longitude + 1 + j] != null) {
                            break;
                        }
                        k++;
                    }
                    lengths[i] = k;
                }
                int min = Arrays.stream(lengths).min().getAsInt();
                if (min == 0) {
                    drawing = null;
                } else if (Generation.world[latitude + 1][longitude + length] != Tileset.FLOOR){
                    drawing = null;
                } else {
                    longitude = longitude + length - 2 - min;
                    length = min + 1;
                }
            }
        }
    }
}


