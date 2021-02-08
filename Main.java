import java.util.*;

public class Main {
    //stuff in main will prolly get moved to playgame methode, in main just to test for now
    //STORE THE VALUES OF THE PLAYER POSITIONS IN 4 VARIABLES THAT KEEP GETTING UPDATE


    public static void main(String[] args) {
        // write your code here
        LadderAndSnake k = new LadderAndSnake();
        k.playGame();

    }
}

class LadderAndSnake {

    int nbOfPlayers;
    final int winner = 100;

    public LadderAndSnake() { //use the number of players to decide how the game loops, create a separate class for players


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


//        for (int i = 0; i < values.length; i++) {
//            for (int j = i + 1; j < values.length; j++) {
//                if (values[i].equals(values[j])) {
//                    System.out.println("There was a tie ");
//                    System.out.println("They have rerolled");
//
//                }
//            }
//        }


        System.out.println("The order of Players will be the following: ");
        for (int i = 1; i <= nbOfPlayers; i++) {
            System.out.println("Player " + i + ": User with the dice roll of " + values[i - 1]);
        }


        System.out.println("-----------------------------------------------------------------------------");


    }

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

                //dicerolls
                int diceRoll = 0;
                int diceRoll2 = 0;


//                for (int i = 1; i <= amountOfPlayers; i++) {
//                    diceRoll = playa[i - 1].flipDice();
//                    System.out.println("Player " + i + " rolls " + diceRoll);
//                    System.out.println("Player " + i + "'s new position is " + playa[i - 1].initialPosition(diceRoll));
//                    diceRoll = 0;
//                }
//                System.out.println("-----------------------------------------------------------------------------");
//                System.out.println("Turn 0");
//                System.out.println("-----------------------------------------------------------------------------");

                // this whole sequence can be looped for the whole simulation
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
        }
        if (null != snake.get(PosAfter)) {
            System.out.println("swallowed by snake");
            PosAfter = snake.get(PosAfter);
            return PosAfter;
        }
        if (null != ladder.get(PosAfter)) {
            System.out.println("Climb up the ladder");
            PosAfter = ladder.get(PosAfter);
            return PosAfter;
        } else {
            PosAfter += diceValue2;
            PosAfter = newPos;
            return newPos;
        }


    }


}



