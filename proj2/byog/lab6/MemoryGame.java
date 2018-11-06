package byog.lab6;

import byog.lab5.RandomUtils;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;


public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40);
        game.rand = new Random(seed);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();


        //TODO: Initialize random number generator
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String RandomString = "";
        for (int i = 0; i < n; i++) {
            RandomString = RandomString + CHARACTERS[RandomUtils.uniform(rand, CHARACTERS.length)];
        }
        return RandomString;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear();
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(20, 20, s);
        Font font1 = new Font("Arial", Font.PLAIN, 20);
        StdDraw.setFont(font1);
        StdDraw.text(3, 39, "Round: " + round);
        if (playerTurn) {
            StdDraw.text(20, 39, "Type!");
        } else {
            StdDraw.text(20, 39, "Watch!");
        }
        StdDraw.text(35, 39, ENCOURAGEMENT[RandomUtils.uniform(rand, ENCOURAGEMENT.length)]);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (int i = 0; i < letters.length(); i++) {
            drawFrame(Character.toString(letters.charAt(i)));
            try {
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            StdDraw.clear();
            StdDraw.show();
            try {
                Thread.sleep(500);

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String returnstring = "";
        for (int i = 0; i < n; i++) {
            while (!StdDraw.hasNextKeyTyped()) {
                try {
                    Thread.sleep(500);

                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            String answer = Character.toString(StdDraw.nextKeyTyped());
            System.out.println(answer);
            returnstring = returnstring + answer;
            drawFrame(returnstring);
        }
        try {
            Thread.sleep(500);

        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return returnstring;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round = 1;
        drawFrame("Round: " + round);
        StdDraw.show();
        try {
            Thread.sleep(1000);

        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        String answer = generateRandomString(round);
        flashSequence(answer);
        String input = solicitNCharsInput(round);
        while (new String(answer).equals(input)) {
            round++;
            drawFrame(ENCOURAGEMENT[RandomUtils.uniform(rand, ENCOURAGEMENT.length)]);
            StdDraw.show();
            try {
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            drawFrame("Round: " + round);
            StdDraw.show();
            try {
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            answer = generateRandomString(round);
            flashSequence(answer);
            input = solicitNCharsInput(round);
        }
        drawFrame("Game Over! You've made it to Round: " + round);

        //TODO: Establish Game loop
    }

}
