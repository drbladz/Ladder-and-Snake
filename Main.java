import java.util.Arrays;
import java.util.Scanner;

public class Main {
//stuff in main will prolly get moved to playgame methode, in main just to test for now
    public static void main(String[] args) {
        // write your code here
        Scanner kb = new Scanner(System.in);
        System.out.println("How many players are playing today?: ");
        int amountOfPlayers = kb.nextInt();
        LadderAndSnake yo = new LadderAndSnake(amountOfPlayers);

        Players[] playa = new Players[amountOfPlayers];

        for (int i = 0; i < amountOfPlayers; i++) {
            playa[i] = new Players();


        }

        // this whole sequence can be looped for the whole simulation
        int diceRoll = playa[1].flipDice();
        System.out.println("roll value is " + diceRoll);

        System.out.println("position is " + playa[1].initialPosition(diceRoll));

        int diceRoll2 = playa[1].flipDice();
        System.out.println("new roll value is " + diceRoll2);

        System.out.println("new position is " + playa[1].playerPosition(diceRoll2));





    }
}


class LadderAndSnake {

    int nbOfPlayers;
    final int winner = 100;
    public LadderAndSnake(int nbOfPlayers) { //use the number of players to decide how the game loops, create a separate class for players
        this.nbOfPlayers = nbOfPlayers;

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
    public void playGame(){

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

    public int initialPosition(int diceValue){
        PosAfter = startPosition + diceValue;
        return PosAfter;
    }

    public int playerPosition(int diceValue2){
        int ppos = PosAfter + diceValue2;
        return ppos;
    }

}
