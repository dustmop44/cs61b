package byog.lab5;
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
    public int cappersproutercount;
    public static int k = 0;


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
        hallway[] firsthallways = sprout(firstroom);
        firsthallways = rearray(firsthallways);
        cappersprouter(firsthallways);
        generate();
    }

    public void cappersprouter(hallway[] hallways) {
        while (cappersproutercount < 4) {
            room[] caps = capper(hallways);
            caps = rearray(caps);
            cappersproutercount += 1;
            cappersprouter(sprouter(caps));
        }
    }

    public room[] roomchecker(room[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].perimeter < 1) {
                rooms[i] = null;
            }
        }
        return rearray(rooms);
    }

    public hallway[] sprouter(room[] rooms) {
        roomchecker(rooms);
        hallway[][] hallwayarrayarray = new hallway[rooms.length][];
        for (int i = 0; i < rooms.length; i++) {
            if(rooms[i] != null) {
                hallwayarrayarray[i] = sprout(rooms[i]);
            }
        }
        hallway[] hallwayarray = rearray(hallwayarrayarray);
        return hallwayarray;
    }

    public hallway[] sprout(room room) {
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
                draw.drawhallway(hallway, world);
                cap(hallway);
                hallways[i] = hallway;
            }
        }
        return hallways;
    }

    public room[] capper(hallway[] hallways) {
        hallways = rearray(hallways);
        room[] rooms = new room[hallways.length];
        for (int i = 0; i < hallways.length; i++) {
            if (hallways[i] != null) {
                room roomie = cap(hallways[i]);
                rooms[i] = roomie;
            }
        }
        return rooms;
    }

    public room cap(hallway hallway) {
        int i = 0;
            if (hallway.horizontal == 1) {
                if (hallway.latitude - 3 >= 0 && hallway.latitude + hallway.width + 3 < maxwidth) {
                    if (hallway.side == 1) {
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
                        }
                        return room;
                    } else if (hallway.side == 3) {
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
                        }
                        return room;
                    }

                }
            } else {
                if (hallway.longitude - 3 >= 0 && hallway.longitude + hallway.length + 3 < maxheight) {
                    if (hallway.side == 4) {
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
                        }
                        return room;
                    } else if (hallway.side == 2) {
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
                        }
                        return room;
                    }
                }
            }
        return new room();
    }

    public hallway[] rearray(hallway[][] hallwayarrayarray) {
        int count = 0;
        for (int i = 0; i < hallwayarrayarray.length; i++) {
            if (hallwayarrayarray[i] != null) {
                for (int j = 0; j < hallwayarrayarray[i].length; j++) {
                    if (hallwayarrayarray[i][j] != null) {
                        count++;
                    }
                }
            }
        }
        hallway[] hallways = new hallway[count];
        count = 0;
        for (int i = 0; i < hallwayarrayarray.length; i++) {
            if (hallwayarrayarray[i] != null) {
                for (int j = 0; j < hallwayarrayarray[i].length; j++) {
                    if (hallwayarrayarray[i][j] != null) {
                        hallways[count] = hallwayarrayarray[i][j];
                        count++;
                    }
                }
            }
        }
        return hallways;
    }

    public hallway[] rearray(hallway[] hallways) {
        int count = 0;
        for (int i = 0; i < hallways.length; i++) {
            if (hallways[i] != null) {
                count++;
            }
        }
        hallway[] rearrayed = new hallway[count];
        count = 0;
        for (int i = 0; i < hallways.length; i++) {
            if (hallways[i] != null) {
                rearrayed[count] = hallways[i];
                count++;
            }
        }
        return rearrayed;
    }

    public room[] rearray(room[] rooms) {
        int count = 0;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null) {
                count++;
            }
        }
        room[] rearrayed = new room[count];
        count = 0;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null) {
                rearrayed[count] = rooms[i];
                count++;
            }
        }
        return rearrayed;
    }

}
