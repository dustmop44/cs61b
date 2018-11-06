package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 45;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        StdDraw.setCanvasSize(640, 480);
        StdDraw.picture(.5, .5, "Torch.png");
        while (!StdDraw.hasNextKeyTyped()) {
        }
        String command = Character.toString(StdDraw.nextKeyTyped());
        if (new String(command).equals("n") || new String(command).equals("N")) {
            StdDraw.clear(Color.black);
            StdDraw.setPenColor(Color.orange);
            StdDraw.text(.5, .5, "Seed: ");
            while(!StdDraw.hasNextKeyTyped()) {
            }
            String seed = "";
            String nextnum = Character.toString(StdDraw.nextKeyTyped());
            while(! new String(nextnum).equals("s")) {
                seed += nextnum;
                StdDraw.clear(Color.black);
                StdDraw.text(.5, .5, "Seed: " + seed);
                while(!StdDraw.hasNextKeyTyped()) {
                }
                nextnum = Character.toString(StdDraw.nextKeyTyped());
            }
            WorldGenerator.Start(Integer.parseInt(seed));
            while(!StdDraw.hasNextKeyTyped()) {
            }
            String quit = Character.toString(StdDraw.nextKeyTyped());
            while (! new String(quit).equals("q")) {
                WorldGenerator.Start();
                while(!StdDraw.hasNextKeyTyped()) {
                }
                quit = Character.toString(StdDraw.nextKeyTyped());
            }
        }

    }

    /**
     * 1. Have traps.
     * 2. Torches run out. aka Light runs out.
     * 3. Place torches at intersections.
     * 4. Bag of torches.
     *
     *
     *
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
}
