package byog.Core;

public class CheckInteraction {

    public static Boolean WinCondition(GameInterface Game) {
        Player PLAYER = Game.PLAYER;
        Exit EXIT = Game.EXIT;
        if (PLAYER.latitude == EXIT.latitude && PLAYER.longitude == EXIT.longitude) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean LoseCondition(GameInterface Game) {
        Player PLAYER = Game.PLAYER;
        Ghosts GHOSTS = Game.GHOSTS;
        for (int i = 0; i < GHOSTS.length(); i++) {
            if (PLAYER.latitude == GHOSTS.GhostArray[i].latitude && PLAYER.longitude == GHOSTS.GhostArray[i].longitude) {
                return true;
            }
        }
        return false;
    }
}
