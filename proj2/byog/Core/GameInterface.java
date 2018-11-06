package byog.Core;

import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

public class GameInterface {
    public Player PLAYER;
    public TETile[][] RealWorld;
    public Exit EXIT;
    public Ghosts GHOSTS;
    public TETile[][] BlindedWorld;

    public GameInterface(TETile[][] NewWorld) {
        RealWorld = NewWorld;
    }

    public TETile[][] RunGame() {
        BlindedWorld = BlindWorld.update(RealWorld, PLAYER);
        Display.DisplayMap(BlindedWorld);
        String command = Wait.Wait();
        while (!new String(command).equals("q")) {
            if (new String(command).equals("w")) {
                PLAYER.moveup();
            } else if (new String(command).equals("a")) {
                PLAYER.moveleft();
            } else if (new String(command).equals("d")) {
                PLAYER.moveright();
            } else if (new String(command).equals("s")) {
                PLAYER.movedown();
            }
            if (CheckInteraction.LoseCondition(this)) {
                Menu.LoseMenu();
            }
            BlindedWorld = BlindWorld.update(RealWorld, PLAYER);
            Display.DisplayMap(BlindedWorld);
            GHOSTS.update();
            BlindedWorld = BlindWorld.update(RealWorld, PLAYER);
            Display.DisplayMap(BlindedWorld);
            if (CheckInteraction.WinCondition(this)) {
                Menu.WinMenu();
                return RealWorld;
            } else if (CheckInteraction.LoseCondition(this)) {
                Menu.LoseMenu();
                return RealWorld;
            }
            command = Wait.Wait();
        }
        return RealWorld;
    }

}
