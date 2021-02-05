import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    //stuff in main will prolly get moved to playgame methode, in main just to test for now
    //STORE THE VALUES OF THE PLAYER POSITIONS IN 4 VARIABLES THAT KEEP GETTING UPDATE

    static Map<Integer, Integer> snake = new HashMap<Integer,Integer>();
    static Map<Integer, Integer> ladder = new HashMap<Integer,Integer>();
    {
        snake.put(16,6);
        snake.put(48,30);
        snake.put(64,60);
        snake.put(79,19);
        snake.put(93,68);
        snake.put(95,24);
        snake.put(97,76);
        snake.put(98,78);

        ladder.put(1,38);
        ladder.put(4,14);
        ladder.put(9,31);
        ladder.put(21,42);
        ladder.put(28,84);
        ladder.put(36,44);
        ladder.put(51,67);
        ladder.put(71,91);
        ladder.put(80,100);


    }

    public static void main(String[] args) {
        // write your code here
        Scanner kb = new Scanner(System.in);




        int attempt = 0;
        while (true) {
            System.out.println("How many players are playing today?: ");
            int amountOfPlayers = kb.nextInt();
            if (amountOfPlayers >= 2 && amountOfPlayers <= 4) {
                System.out.println("Game is played by " + amountOfPlayers + " players!");
                LadderAndSnake yo = new LadderAndSnake(amountOfPlayers);

                Players[] playa = new Players[amountOfPlayers];

                for (int i = 1; i <= amountOfPlayers; i++) {
                    playa[i - 1] = new Players();
                }
                int diceRoll = 0;
                int diceRoll2 = 0;
                int diceRoll3 = 0;
                for (int i = 1; i <= amountOfPlayers; i++) {
                    diceRoll = playa[i - 1].flipDice();
                    System.out.println("Player " + i + " rolls " + diceRoll);
                    //PUT HASHMAP IF STATEMENT HERE
                    System.out.println("Player " + i + "'s new position is " + playa[i - 1].initialPosition(diceRoll));
                    diceRoll = 0;
                }


                // this whole sequence can be looped for the whole simulation
                int turnCount = 0;
                while (turnCount < 100) {
                    for (int i = 1; i <= amountOfPlayers; i++) {


                        diceRoll2 = playa[i - 1].flipDice();
                        System.out.println("Player " + i + " rolls " + diceRoll2);
                        //PUT HASHMAP IF STATEMENT HERE
                        System.out.println("Player " + i + "'s new position is " + playa[i - 1].playerPosition(diceRoll2));
                        diceRoll2 = 0;
                        if (playa[i - 1].playerPosition(diceRoll2) == 100) {
                            System.out.println("Game Over! Player " + i + " Won!");
                            System.exit(0);
                        }


                    }
                    System.out.println("Turn " + turnCount);
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

class LadderAndSnake {

    int nbOfPlayers;
    final int winner = 100;

    public LadderAndSnake(int nbOfPlayers) { //use the number of players to decide how the game loops, create a separate class for players
        this.nbOfPlayers = nbOfPlayers;

        if (nbOfPlayers < 2 || nbOfPlayers > 4) {
            System.out.println("Error, please enter a number between 2 and 4");
        }

        //THE GAME BOARD IN GOOD ORDER TOO xD, probably use this just for some sort of gui.
        int board[][] = new int[10][10];
        int maxNumber = 100;
        int reverseNum = 90;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 10; j++) {
                    board[i][j] = maxNumber;
                    System.out.print(board[i][j] + "\t");
                    maxNumber--;
                    reverseNum++;
                }
            } else {
                reverseNum = maxNumber - 9;
                for (int j = 0; j < 10; j++) {
                    board[i][j] = reverseNum;
                    System.out.print(board[i][j] + "\t");
                    maxNumber--;
                    reverseNum++;
                }
            }
            System.out.println();
        }


    }


    public int getNbOfPlayers() {
        return nbOfPlayers;
    }

    public void setNbOfPlayers(int nbOfPlayers) {
        this.nbOfPlayers = nbOfPlayers;
    }


    public String toString() {
        return "";
    }

    public void playGame() {

    }

}


class Players {
    private static int count = 0;
    private static int startPosition = 0;
    private int PosAfter = 0;

    public Players() {
        count++;
    }

    public static void numberOfPlayers() {
        System.out.println(count);
    }

    public int flipDice() {
        int diceValue = (int) (Math.random() * 6) + 1;
        return diceValue;
    }

    public int initialPosition(int diceValue) {
        PosAfter = startPosition + diceValue;
        return PosAfter;
    }


    public int playerPosition(int diceValue2) {
        int newPos = PosAfter + diceValue2;
        if (newPos > 100) {
            newPos = 100 - (newPos - 100);
            PosAfter = newPos;
            return newPos;
        } else {
            PosAfter += diceValue2;
            PosAfter = newPos;
            return newPos;
        }


    }
}
