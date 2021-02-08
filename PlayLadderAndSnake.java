/**
 Sam Mojaverian (40174101) and Tahmid Rahman (40174589)
 COMP249
 Assignment #1
 Due Monday February 8, 2021
 */





import java.util.*;

/**
 * Driver Class, runs the game
 *
 * @author Sam Mojaverian & Tahmid Rahman
 */
public class PlayLadderAndSnake {



    public static void main(String[] args) {

        System.out.println("Welcome to the Ladder And Snake game, made by Sam Mojaverian and Tahmid Rahman!");
        // write your code here
        LadderAndSnake k = new LadderAndSnake();
        k.playGame();

    }
}

/**
 * Ladder and Snake Class
 * <p>
 *     creates the game board, determines the number of players and the order of play.
 * </p>
 * <p>
 *     contains the playGame method that runs the game
 * </p>
 *
 * @author Sam Mojaverian & Tahmid Rahman
 */
class LadderAndSnake {

    int nbOfPlayers;
    final int winner = 100;

    /**
     * Create the game board
     */
    public LadderAndSnake() {


        Tile[][] tiles = new Tile[10][10];
        int maxNumber = 100;
        int reverseNum = 90;
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                tiles[i][j] = new Tile();
            }
        }
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 10; j++) {
                    tiles[i][j].setValue(maxNumber);
                    maxNumber--;
                    reverseNum++;
                }
            } else {
                reverseNum = maxNumber - 9;
                for (int j = 0; j < 10; j++) {
                    tiles[i][j].setValue(reverseNum);
                    maxNumber--;
                    reverseNum++;
                }
            }
        }
        //Print board
        Board board = new Board(tiles);
        board.printBoard();
    }

    /**
     *
     * @param tiles2
     * @return the tile 2d array
     */
    public Tile[][] Copy(Tile[][] tiles2) {
        Tile[][] tiles = new Tile[10][10];
        int maxNumber = 100;
        int reverseNum = 90;
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                tiles[i][j] = new Tile();
            }
        }
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 10; j++) {
                    tiles[i][j].setValue(maxNumber);
                    maxNumber--;
                    reverseNum++;
                }
            } else {
                reverseNum = maxNumber - 9;
                for (int j = 0; j < 10; j++) {
                    tiles[i][j].setValue(reverseNum);
                    maxNumber--;
                    reverseNum++;
                }
            }
        }
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                tiles2[i][j] = tiles[i][j];
            }
        }
        return tiles2;
    }

    /**
     *
     * @return
     */
    public int getNbOfPlayers() {
        return nbOfPlayers;
    }

    public void setNbOfPlayers(int nbOfPlayers) {
        this.nbOfPlayers = nbOfPlayers;
    }


    public String toString() {
        return "";
    }

    /**
     * Determines the order of play with an initial diceroll
     * @param nbOfPlayers takes the declared number of players and creates User objects
     */
    public void determineOrder(int nbOfPlayers) {
        int tieCount = 0;
        Players[] User = new Players[nbOfPlayers];
        Integer[] values = new Integer[nbOfPlayers];
        for (int i = 1; i <= nbOfPlayers; i++) {
            User[i - 1] = new Players();
            values[i - 1] = User[i - 1].flipDice();
        }
        Arrays.sort(values, Collections.reverseOrder());
        System.out.println(Arrays.toString(values));



        for (int i = 1; i < values.length; i++) {
            if (values[i - 1].equals(values[i])) {
                int p1 = (int) (Math.random() * 6) + 1;
                int p2 = (int) (Math.random() * 6) + 1;
                while (p1 == p2) {
                    p1 = (int) (Math.random() * 6) + 1;
                    p2 = (int) (Math.random() * 6) + 1;
                    System.out.println("tie again!");
                }
                System.out.println("Player " + i + " rolled " + p1);
                System.out.println("Player " + (i+1) + " rolled " + p2);
                if (p1 > p2) {
                    values[i - 1] = p1;
                    values[i] = p2;
                    System.out.println("Player " + i + " will go before Player " + (i+1));
                } else {
                    System.out.println("Player " + (i+1) + " will go before Player " + i);
                    values[i - 1] = p2;
                    values[i] = p1;
                }
                i++;

            }
        }
        for (int i = 1; i <= nbOfPlayers; i++) {
            System.out.println("The dice has been rolled, User " + i + " rolled " + values[i - 1]);
        }
        System.out.println("-----------------------------------------------------------------------------");



        System.out.println("The order of Players will be the following: ");
        for (int i = 1; i <= nbOfPlayers; i++) {
            System.out.println("Player " + i + ": User with the dice roll of " + values[i - 1]);
        }


        System.out.println("-----------------------------------------------------------------------------");


    }

    /**
     * The game loop, asks how many players are playing and starts the game
     */
    public void playGame() {
        Scanner kb = new Scanner(System.in);
        int attempt = 0;
        while (true) {
            System.out.println("How many players are playing today?: ");
            int amountOfPlayers = kb.nextInt();
            if (amountOfPlayers >= 2 && amountOfPlayers <= 4) {
                System.out.println("Game is played by " + amountOfPlayers + " players!");

                determineOrder(amountOfPlayers);

                Players[] playa = new Players[amountOfPlayers];

                for (int i = 1; i <= amountOfPlayers; i++) {
                    playa[i - 1] = new Players();
                }


                int diceRoll2 = 0;



                int turnCount = 1;
                while (turnCount < 100) {
                    for (int i = 1; i <= amountOfPlayers; i++) {


                        diceRoll2 = playa[i - 1].flipDice();
                        System.out.println("Player " + i + " rolls " + diceRoll2);
                        System.out.println("Player " + i + "'s new position is " + playa[i - 1].playerPosition(diceRoll2));
                        diceRoll2 = 0;
                        if (playa[i - 1].playerPosition(diceRoll2) == 100) {
                            System.out.println("Game Over! Player " + i + " Won!");
                            System.exit(0);
                        }


                    }
                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.println("Turn " + turnCount);
                    System.out.println("-----------------------------------------------------------------------------");
                    turnCount++;


                }


            } else {
                attempt++;
                if (attempt < 4) {
                    System.out.println("Bad attempt " + attempt + " - Invalid # of players. Please enter a # between 2 and 4 inclusively: ");
                }
                if (attempt == 4) {
                    System.out.println("Bad attempt " + attempt + "! You have exhausted all your chances. Program will terminate!");
                    System.exit(0);
                }
            }

        }


    }

}

/**
 * The class that contains the player positions and  the snake and ladder positions
 *
 * @author Sam Mojaverian
 */
class Players {
    static Map<Integer, Integer> snake = new HashMap<Integer, Integer>();
    static Map<Integer, Integer> ladder = new HashMap<Integer, Integer>();

    {
        snake.put(16, 6);
        snake.put(48, 30);
        snake.put(64, 60);
        snake.put(79, 19);
        snake.put(93, 68);
        snake.put(95, 24);
        snake.put(97, 76);
        snake.put(98, 78);

        ladder.put(1, 38);
        ladder.put(4, 14);
        ladder.put(9, 31);
        ladder.put(21, 42);
        ladder.put(28, 84);
        ladder.put(36, 44);
        ladder.put(51, 67);
        ladder.put(71, 91);
        ladder.put(80, 100);


    }

    private static int count = 0;
    private static int startPosition = 0;
    private int PosAfter = 0;

    /**
     *
     */
    public Players() {
        count++;
    }

    public static void numberOfPlayers() {
        System.out.println(count);
    }

    /**
     * 6 sided dice RNG
     * @return the value on a dice
     */
    public int flipDice() {
        int diceValue = (int) (Math.random() * 6) + 1;
        return diceValue;
    }


    /**
     * Player position method, takes into account snakes and ladders
     * @param diceValue2 the value of the dice
     * @return the position of the player who rolled a dice
     */
    public int playerPosition(int diceValue2) {
        int newPos = PosAfter + diceValue2;
        if (newPos > 100) {
            newPos = 100 - (newPos - 100);
            PosAfter = newPos;
            return newPos;
        }
        if (null != snake.get(PosAfter)) {
            System.out.println("Swallowed by a snake");
            PosAfter = snake.get(PosAfter);
            return PosAfter;
        }
        if (null != ladder.get(PosAfter)) {
            System.out.println("Go up the ladder");
            PosAfter = ladder.get(PosAfter);
            return PosAfter;
        } else {
            PosAfter += diceValue2;
            PosAfter = newPos;
            return newPos;
        }


    }


}

/**
 * The tiles on the board
 * @author Tahmid Rahman
 */
class Tile {
    int value;
    String player;

    public Tile() {
        value = 0;
        player = null;
    }

    public Tile(int value) {
        this.value = value;
        player = "";
    }

    public Tile(int value, String player) {
        this.value = value;
        this.player = player;
    }

    /**
     * Copy Constructor
     * @param t
     */
    public Tile(Tile t) {
        value = t.value;
        player = t.player;
    }

    public int getValue() {
        return value;
    }

    public String getPlayer() {
        return player;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPlayer(String Player) {
        this.player = Player;
    }

    /**
     * This is ultimately setting up what each tile of the board will look like when printed. Creating the layout of the tiles.
     * @return the layout of the tiles
     */
    public String toString() {
        String res = "";
        String res2 = "";

        if(player == "")
            return "";
        else if(player == "P1")
            res = "(P1)";
        else if(player == "P2")
            res = "(P2)";
        else if(player == "P3")
            res = "(P3)";
        else if(player == "P4")
            res = "(P4)";
        else if(player == "1+")
            res = "(1+)";


        switch(value) {
            case 1:
                res2 = "L->38";
                break;
            case 4:
                res2 = "L->14";
                break;
            case 9:
                res2 = "L->31";
                break;
            case 21:
                res2 = "L->42";
                break;
            case 36:
                res2 = "L->44";
                break;
            case 28:
                res2 = "L->84";
                break;
            case 51:
                res2 = "L->67";
                break;
            case 71:
                res2 = "L->91";
                break;
            case 80:
                res2 = "L->100";
                break;

            case 16:
                res2 = "S->6";
                break;
            case 48:
                res2 = "S->30";
                break;
            case 64:
                res2 = "S->60";
                break;
            case 79:
                res2 = "S->19";
                break;
            case 93:
                res2 = "S->68";
                break;
            case 95:
                res2 = "S->24";
                break;
            case 97:
                res2 = "S->76";
                break;
            case 98:
                res2 = "S->78";
                break;
        }
        return ("|  "+value+" "+res2+" "+res+"|");

    }

}

/**
 * The game board
 *
 * @author Tahmid Rahman
 */
class Board {
    private Tile[][] board;

    /**
     *
     * @param tiles
     */
    public Board(Tile[][] tiles) {
        board = tiles;

    }
    /**
     * Method that prints the game board
     */
    public void printBoard() {

        for (int i = 0; i < 10; i++) {
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.printf("%16s", board[i][j]);
            }
            System.out.println();
        }
    }

}


