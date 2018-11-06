package byog.Core;

import edu.princeton.cs.introcs.StdDraw;

public class Menu {

    public static String MainMenu() {
        StdDraw.setCanvasSize(640, 480);
        StdDraw.picture(.5, .5, "Torch.png");
        while (!StdDraw.hasNextKeyTyped()) {
        }
        String command = Character.toString(StdDraw.nextKeyTyped());
        return command;
    }

    public static String SeedMenu() {
        Display.DisplaySeed("");
        String seed = "";
        String nextnum = Wait.Wait();
        while(!new String(nextnum).equals("s")) {
            if ("1234567890".toLowerCase().contains(nextnum.toLowerCase())) {
                seed += nextnum;
                Display.DisplaySeed(seed);
            }
            nextnum = Wait.Wait();
        }
        return seed;
    }

    public static String WinMenu() {
        Display.DisplayWin();
        String command = Wait.Wait();
        Display.DisplayContinue();
        command = Wait.Wait();
        while (! new String(command).equals("c") && ! new String(command).equals("q")) {
            command = Wait.Wait();
        }
        if (new String(command).equals("c")) {
            GameHub.StartNextLevel();
        } else {
            return command;
        }
        return command;
    }

    public static String LoseMenu() {
        Display.DisplayLose();
        String command = Wait.Wait();
        while (! new String(command).equals("r") && ! new String(command).equals("q")) {
            command = Wait.Wait();
        }
        if (new String(command).equals("r")) {
            GameHub.RestartGame();
        } else {
            Display.DisplayGameOver();
            return command;
        }
        return command;
    }
}
