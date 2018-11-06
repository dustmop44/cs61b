package byog.lab5;

import byog.TileEngine.Tileset;

import java.util.Random;

public class hallwaysprouter {

    public static int[][] createsprouts(room room, Random RANDOM) {
        int numhallways = RandomUtils.uniform(RANDOM, 8, 20);
        int[][] hallways = new int[numhallways][2];
        for (int i = 0; i < numhallways; i++) {
            hallways[i][0] = RandomUtils.uniform(RANDOM, room.perimeter);
        }
        int[] corners = new int[]{0, room.length - 1, room.length + room.width - 2, room.length + room.length + room.width - 3};
        for (int i = 0; i < numhallways; i++) {
            for (int j = 0; j < 4; j++) {
                if (hallways[i][0] == corners[j]) {
                    hallways[i][0] = -1;
                    continue;
                }
            }
            if (hallways[i][0] < room.length - 1) {
                hallways[i][1] = 1;
            } else if (hallways[i][0] < room.length+room.width - 2) {
                hallways[i][1] = 2;
            } else if (hallways[i][0] < room.length + room.length + room.width - 3) {
                hallways[i][1] = 3;
            } else if (hallways[i][0] < room.perimeter) {
                hallways[i][1] = 4;
            }
        }
        return hallways;
    }
    /*
    public static int[][] createsprouts(hallway room, Random RANDOM) {
        int numhallways = RandomUtils.uniform(RANDOM, 8, 10);
        int[][] hallways = new int[numhallways][2];
        for (int i = 0; i < numhallways; i++) {
            hallways[i][0] = RandomUtils.uniform(RANDOM, room.perimeter);
        }
        int[] corners = new int[]{0, room.length - 1, room.length+room.width - 2, room.perimeter - room.width + 2};
        for (int i = 0; i < numhallways; i++) {
            for (int j = 0; j < 4; j++) {
                if (hallways[i][0] == corners[j]) {
                    hallways[i][0] = -1;
                }
            }
            if (hallways[i][0] < room.length - 1) {
                hallways[i][1] = 1;
            } else if (hallways[i][0] < room.length+room.width - 2) {
                hallways[i][1] = 2;
            } else if (hallways[i][0] < room.perimeter - room.width + 2) {
                hallways[i][1] = 3;
            } else {
                hallways[i][1] = 4;
            }
        }
        return hallways;
    }
*/
    public static hallway createsprouts(int position, int side, room ROOM, makerooms roommaker) {
        int[] corners = new int[]{0, ROOM.length - 1, ROOM.length+ROOM.width - 2, ROOM.length + ROOM.length + ROOM.width - 3};
        for (int j = 0; j < 4; j++) {
            if (position == corners[j] || position >= ROOM.perimeter) {
                hallway hallway = roommaker.makehallway(3, 3, 3);
                hallway.drawing = null;
                return hallway;
            }
        }

        if (side == 1) {
            hallway hallway = roommaker.makesideonehallway(ROOM, position);
            int s = 0;
            while (hallway.obstructed() && s < 100) {
                hallway = roommaker.makesideonehallway(ROOM, position);
                s++;
            }
            if (hallway.obstructed()) {
                hallway.drawing = null;
            }
            if (hallway.drawing != null) {
                hallway.drawing[hallway.width-1][1] = Tileset.FLOOR;
            }
            return hallway;
        } else if (side == 2) {
            hallway hallway = roommaker.makesidetwohallway(ROOM, position);
            int s = 0;
            while (hallway.obstructed() && s < 100) {
                hallway = roommaker.makesidetwohallway(ROOM, position);
                s++;
            }
            if (hallway.obstructed()) {
                hallway.drawing = null;
            }
            if (hallway.drawing != null) {
                hallway.drawing[1][0] = Tileset.FLOOR;
            }
            return hallway;
        } else if (side == 3) {
            hallway hallway = roommaker.makesidethreehallway(ROOM, position);
            int s = 0;
            while (hallway.obstructed() && s < 100) {
                hallway = roommaker.makesidethreehallway(ROOM, position);
                s++;
            }
            if (hallway.obstructed()) {
                hallway.drawing = null;
            }
            if (hallway.drawing != null) {
                hallway.drawing[0][1] = Tileset.FLOOR;
            }
            return hallway;
        } else if (side == 4) {
            System.out.println("NEW");
            System.out.println(position);
            hallway hallway = roommaker.makesidefourhallway(ROOM, position);
            int s = 0;
            while (hallway.obstructed() && s < 100) {
                hallway = roommaker.makesidefourhallway(ROOM, position);
                s++;
            }
            if (hallway.obstructed()) {
                hallway.drawing = null;
            }
            if (hallway.drawing != null) {
                System.out.println(hallway.position);
                System.out.println(hallway.latitude + " " + position + " " + hallway.longitude + " " + hallway.length);
                System.out.println(ROOM.latitude + " " + ROOM.longitude);
                hallway.drawing[1][hallway.length-1] = Tileset.FLOOR;
            }
            return hallway;
        }
        hallway hallway = roommaker.makehallway(3, 3, 3);
        hallway.drawing = null;
        System.out.println("DING DONG");
        return hallway;
    }
}
