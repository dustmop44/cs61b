package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Display {

    public static void DisplayMap(TETile[][] Mapper) {
        for (int i = 0; i < Mapper.length; i++) {
            for (int j = 0; j < Mapper[0].length; j++) {
                if (Mapper[i][j] == null) {
                    Tileset.NOTHING.draw(i, j);
                } else {
                    Mapper[i][j].draw(i, j);
                }
            }
        }
        StdDraw.show();
    }

    public static void DisplaySeed(String seed) {
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.orange);
        StdDraw.text(.5, .5, "Seed: " + seed);
        if (seed.length() > 0) {
            StdDraw.text(.5, .4, "PRESS S TO CONTINUE");
        }
        StdDraw.show();
    }

    public static void DisplayWin() {
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.orange);
        StdDraw.text(40, 22.5, "YOU WIN!");
        StdDraw.show();
        return;
    }

    public static void DisplayContinue() {
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.orange);
        StdDraw.text(40, 22.5, "Continue?");
        StdDraw.text(40, 21, "Press c to continue. Press q to quit.");
        StdDraw.show();
        return;
    }

    public static void DisplayLose() {
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.orange);
        StdDraw.text(40, 22.5, "YOU LOSE!");
        StdDraw.text(40, 21, "Press r to restart. Press q to quit.");
        StdDraw.show();
        return;
    }

    public static void DisplayGameOver() {
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.orange);
        StdDraw.text(40, 22.5, "GAME OVER");
        StdDraw.show();
        return;
    }
}
