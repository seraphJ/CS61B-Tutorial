package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
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
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
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
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(CHARACTERS[rand.nextInt(CHARACTERS.length)]);
        }
        return sb.toString();
    }

    public void drawFrame(String s, String instruction) {
        //TODO: Take the string and display it in the center of the screen
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 30));
        StdDraw.text(width / 2, height / 2, s);
        StdDraw.setFont(new Font("Arial", Font.PLAIN, 20));
        StdDraw.text(width / 8, 31 * height / 32, "Round: " + this.round);
        StdDraw.text(width/2, 31 * height / 32, instruction);
        StdDraw.text(7 * width / 8, 31 * height / 32, ENCOURAGEMENT[this.round % ENCOURAGEMENT.length]);
        StdDraw.show();
        //TODO: If game is not over, display relevant game information at the top of the screen
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (char c : letters.toCharArray()) {
            drawFrame(String.valueOf(c), "Watch!");
            StdDraw.pause(1000);
            drawFrame("", "Watch!");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        this.drawFrame("", "Type!");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < n) {
            if (StdDraw.hasNextKeyTyped()) {
                sb.append(StdDraw.nextKeyTyped());
                this.drawFrame(sb.toString(), "Type!");
                i++;
            }
        }
        StdDraw.pause(500);
        return sb.toString();
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        this.round = 1;
        //TODO: Establish Game loop
        while (!gameOver) {
            this.drawFrame("Round: " + this.round, "Watch!");
            StdDraw.pause(1000);
            String generatedStr = this.generateRandomString(this.round);
            this.flashSequence(generatedStr);
            String typedStr = this.solicitNCharsInput(this.round);
            if (generatedStr.equals(typedStr)) {
                this.round++;
            } else {
                this.drawFrame("Game Over! You made it to round: " + this.round, "");
                StdDraw.pause(1000);
                gameOver = true;
            }
        }
    }

}
