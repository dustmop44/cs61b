package byog.lab5;

import byog.TileEngine.Tileset;

import java.util.Random;

public class makerooms {
    public static int maxwidth;
    public static int maxheight;
    public Random RANDOM;

    public makerooms(int framewidth, int frameheight, Random random) {
        RANDOM = random;
        maxwidth = framewidth;
        maxheight = frameheight;
        room.frameheight = maxheight;
        room.framewidth = maxwidth;
    }

    public room makefirstroom() {
        return new room(RandomUtils.uniform(RANDOM, maxwidth), RandomUtils.uniform(RANDOM, maxheight), 4, 4);
    }

    public room makerandomroom(int latitude, int longitude) {
        return new room(latitude, longitude, RANDOM);
    }

    public room makesetroom(int latitude, int longitude, int width, int length) {
        return new room(latitude, longitude, width, length);
    }

    public hallway makehallway(int latitude, int longitude, int horizontal) {
        return new hallway(latitude, longitude, horizontal, RANDOM);
    }

    public room makesideoneroom(hallway hallway) {
        room room = new room(0, 0, RANDOM);
        room.side = 1;
        room.position = RandomUtils.uniform(RANDOM, 1, room.length - 1);
        room.longitude = hallway.longitude - room.position + 1;
        room.latitude = hallway.latitude + hallway.width - 1;
        room.drawing[0][room.position] = Tileset.FLOOR;

        return room;
    }

    public room makesidefourroom(hallway hallway) {
        room room = new room(0, 0, RANDOM);
        room.side = 4;
        room.position = RandomUtils.uniform(RANDOM, 1, room.width - 1);
        room.longitude = hallway.longitude - room.length + 1;
        room.latitude = hallway.latitude - room.position + 1;
        room.drawing[room.position][room.length - 1] = Tileset.FLOOR;
        return room;
    }

    public room makesidethreeroom(hallway hallway) {
        room room = new room(0, 0, RANDOM);
        room.side = 3;
        room.position = RandomUtils.uniform(RANDOM, 1, room.length - 1);
        room.longitude = hallway.longitude - room.position + 1;
        room.latitude = hallway.latitude - room.width + 1;
        room.drawing[room.width-1][room.position] = Tileset.FLOOR;
        return room;
    }

    public room makesidetworoom(hallway hallway) {
        room room = new room(0, 0, RANDOM);
        room.side = 2;
        room.position = RandomUtils.uniform(RANDOM, 1, room.width - 1);
        room.longitude = hallway.longitude + hallway.length - 1;
        room.latitude = hallway.latitude - room.position + 1;
        room.drawing[room.position][0] = Tileset.FLOOR;
        return room;
    }

    public hallway makesideonehallway(room ROOM, int position) {
        hallway hallway = new hallway(0, 0, 1, RANDOM);
        hallway.latitude = ROOM.latitude - hallway.width + 1;
        hallway.longitude = ROOM.longitude + position - 1;
        return hallway;
    }

    public hallway makesidetwohallway(room ROOM, int position) {
        position = position - ROOM.length;
        int latitude = ROOM.latitude + position;
        int longitude = ROOM.longitude + ROOM.length - 1;
        hallway hallway = new hallway(latitude, longitude, 2, RANDOM);
        return hallway;
    }

    public hallway makesidethreehallway(room ROOM, int position) {
        position = position + 2 - ROOM.length - ROOM.width;
        int latitude = ROOM.latitude + ROOM.width - 1;
        int longitude = ROOM.longitude + ROOM.length - position - 2;
        hallway hallway = new hallway(latitude, longitude, 3, RANDOM);
        return hallway;
    }

    public hallway makesidefourhallway(room ROOM, int position) {
        hallway hallway = new hallway(0, 0, 4, RANDOM);
        position = position - (ROOM.length * 2) - ROOM.width + 2;
        if (position > ROOM.width - 2) {
            position = position - (position - ROOM.width + 2);
        }
        hallway.latitude = ROOM.latitude + position;
        hallway.longitude = ROOM.longitude - hallway.length + 1;
        return hallway;
    }
}
