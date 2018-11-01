package byog.lab5;
import edu.princeton.cs.introcs.StdDraw;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    TETile[][] world = new TETile[80][40];
    private static final Random RANDOM = new Random();

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(7);
        switch (tileNum) {
            case 0: return Tileset.WATER;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.SAND;
            case 4: return Tileset.MOUNTAIN;
            case 5: return Tileset.TREE;
            case 6: return Tileset.WALL;
            default: return Tileset.NOTHING;
        }
    }

    public static void fillWithTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.GRASS;
            }
        }
    }

    public static void fillWithRandomTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = randomTile();
            }
        }
    }


    public static TETile[][] hexagonmaker(int s) {
        int width = (((s-1)*2) + s);
        int height = s*2;
        TETile[][] hexagon = new TETile[width][height];
        TETile color = randomTile();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i >= s) {
                    int x = height - i - 1;
                    if (j < s - x - 1) {
                        hexagon[j][i] = Tileset.NOTHING;
                    } else if (j < width - (s-x-1)) {
                        hexagon[j][i] = color;
                    } else {
                        hexagon[j][i] = Tileset.NOTHING;
                    }
                } else {
                    if (j < s - i-1) {
                        hexagon[j][i] = Tileset.NOTHING;
                    } else if (j < width - (s - i-1)) {
                        hexagon[j][i] = color;
                    } else {
                        hexagon[j][i] = Tileset.NOTHING;
                    }
                }
            }
        }
        return hexagon;
    }

    public static void addHexagon(int s, int latitude, int longitude) {
        TETile[][] hexagon = hexagonmaker(s);
        int height = hexagon[0].length;
        int width = hexagon.length;

        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                if (hexagon[x][y] == Tileset.NOTHING) {
                    continue;
                }
                hexagon[x][y].draw(x + latitude, y + longitude);
            }
        }
        StdDraw.show();
    }

    public static void tesselateWorld(int hexsize, int wwidth, int wheight) {
        int hexwidth = hexsize + ((hexsize -1)*2);
        int hexheight = hexsize * 2;
        int startwidth = hexsize*2 - 1;
        int startheight = hexsize;
        for (int j = -startwidth; j < wwidth; j += startwidth)
            if (j/startwidth % 2 == 0) {
                for (int i = 0; i < wheight; i += hexheight) {
                    addHexagon(hexsize, j, i);
                }
            } else {
                for (int i = -startheight; i < wheight; i += hexheight) {
                    addHexagon(hexsize, j, i);
                }
            }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(75, 40);
        /*
        TETile[][] Tiles = new TETile[75][40];
        fillWithTiles(Tiles);
        ter.renderFrame(Tiles);
        */
        tesselateWorld(4, 75, 40);
    }
}
