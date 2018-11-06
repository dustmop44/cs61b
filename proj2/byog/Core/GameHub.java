package byog.Core;

public class GameHub {
    public static int seed;

    public static void StartNewGame() {
        String firstseed = Menu.SeedMenu();
        seed = Integer.parseInt(firstseed);
        PrepareGame NewGame = new PrepareGame(Integer.parseInt(firstseed));
        NewGame.StartGame();
    }

    public static void StartNextLevel() {
        seed = seed + 1;
        PrepareGame NewGame = new PrepareGame(seed);
        NewGame.StartGame();
    }

    public static void RestartGame() {
        PrepareGame NewGame = new PrepareGame(seed);
        NewGame.StartGame();
    }
}
