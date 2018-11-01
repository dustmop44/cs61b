package byog.Core;
import java.util.Random;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;


public class Generation {
    public static int maxwidth;
    public static int maxheight;
    public Random RANDOM;
    public static TETile[][] world;
    public makerooms roommaker;
    public draw draw;


    public Generation(int framewidth, int frameheight, Random random) {
        maxwidth = framewidth;
        maxheight = frameheight;
        RANDOM = random;
        draw = new draw(maxwidth, maxheight);
        roommaker = new makerooms(maxwidth, maxheight, RANDOM);
        world = new TETile[framewidth][frameheight];
    }


    public void generate() {
        for (int i = 0; i < maxwidth; i++) {
            for (int j = 0; j < maxheight; j++) {
                if (world[i][j] == null) {
                    Tileset.NOTHING.draw(i, j);
                } else if (i == maxwidth - 1 || j == maxheight - 1) {
                    Tileset.WALL.draw(i, j);
                } else {
                    world[i][j].draw(i, j);
                }
            }
        }
        StdDraw.show();
    }

    public void firstroom() {
        //room firstroom = roommaker.makefirstroom();
        room firstroom = roommaker.makesetroom(35, 18, 10, 10);
        draw.drawroom(firstroom, world);
        sprout(firstroom);
        generate();
    }

    public void sprout(room room) {
        /* next to do:
        remember to solve the if hallways sprout next to each other
        aka the position is within two numbers.
        now, make the hallways only hallways.
        create holes in the room and in the hallway.
        */
        int[][] hallwaystodraw = hallwaysprouter.createsprouts(room, RANDOM);
        hallway[] hallways = new hallway[hallwaystodraw.length];
        for (int i = 0; i < hallwaystodraw.length; i++) {
            int position = hallwaystodraw[i][0];
            int side = hallwaystodraw[i][1];
            if (side == 1) {
                if (room.latitude - 3 < 0) {
                    continue;
                }
            } else if (side == 2) {
                if (room.longitude + room.length + 2 >= maxheight) {
                    continue;
                }
            } else if (side == 3) {
                if (room.latitude + room.width + 2 >= maxwidth) {
                    continue;
                }
            } else if (side == 4) {
                if (room.longitude - 2 < 0) {
                    continue;
                }
            }
            if (position == -1) {
                continue;
            }
            hallway hallway = hallwaysprouter.createsprouts(position, side, room, roommaker);
            if (hallway.drawing != null) {
                /*
                to do:
                cut off drawing here if there is already a hallway there.
                then legitimate capping process.
                 */
                draw.drawhallway(hallway, world);
                hallways[i] = hallway;
            }
        }

    }


    /*
    random choose whether room or not.
    random choose position where the room attaches. also make hole there.
    maybe if there is wall, we don't need to resize and combine walls.
     */
    public room cap(hallway hallway) {
        int i = 0;
        //if (RandomUtils.uniform(RANDOM, 3) >= 1) {
            if (hallway.horizontal == 1) {
                if (hallway.latitude - 3 >= 0 && hallway.latitude + hallway.width + 3 < maxwidth) {
                    if (hallway.side == 1 && world[hallway.latitude-3][hallway.longitude] == null) {
                        room room = roommaker.makesidethreeroom(hallway);
                        while (!room.notobstructed() && i < 100) {
                            room = roommaker.makesidethreeroom(hallway);
                            i++;
                        }
                        if (!room.notobstructed()) {
                            room.drawing = null;
                        }
                        if (room.drawing != null) {
                            draw.drawroom(room, world);
                            sprout(room);
                        }

                        return room;
                    } else if (hallway.side == 3 && world[hallway.latitude + hallway.width + 3][hallway.longitude] == null) {
                        room room = roommaker.makesideoneroom(hallway);
                        while (!room.notobstructed() && i < 100) {
                            room = roommaker.makesideoneroom(hallway);
                            i++;
                        }
                        if (!room.notobstructed()) {
                            room.drawing = null;
                        }
                        if (room.drawing != null) {
                            draw.drawroom(room, world);
                            sprout(room);
                        }
                        return room;
                    }
                }
            } else {
                if (hallway.longitude - 3 >= 0 && hallway.longitude + hallway.length + 3 < maxheight) {
                    if (hallway.side == 4 && world[hallway.latitude][hallway.longitude -3] == null) {
                        room room = roommaker.makesidefourroom(hallway);
                        while (!room.notobstructed() && i < 100) {
                            room = roommaker.makesidefourroom(hallway);
                            i++;
                        }
                        if (!room.notobstructed()) {
                            room.drawing = null;
                        }
                        if (room.drawing != null) {
                            draw.drawroom(room, world);
                            sprout(room);
                        }
                        return room;
                    } else if (hallway.side == 2 && world[hallway.latitude][hallway.longitude +hallway.length+3] == null) {
                        room room = roommaker.makesidetworoom(hallway);
                        while (!room.notobstructed() && i < 100) {
                            room = roommaker.makesidetworoom(hallway);
                            i++;
                        }
                        if (!room.notobstructed()) {
                            room.drawing = null;
                        }
                        if (room.drawing != null) {
                            draw.drawroom(room, world);
                            sprout(room);
                        }
                        return room;
                    }
                }
            }
        return new room();}
    //return new room();}
}
