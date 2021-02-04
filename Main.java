import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner kb = new Scanner(System.in);
        System.out.println("How many players are playing today?: ");
        int amountOfPlayers = kb.nextInt();
        LadderAndSnake yo = new LadderAndSnake(amountOfPlayers);

        Players[] playa = new Players[amountOfPlayers];

        for (int i = 0; i < amountOfPlayers; i++) {
            playa[i] = new Players();

            System.out.println(playa[i].flipDice());
        }





    }
}


class LadderAndSnake {

    int nbOfPlayers;

    public LadderAndSnake(int nbOfPlayers) { //use the number of players to decide how the game loops, create a separate class for players
        this.nbOfPlayers = nbOfPlayers;

        //THE GAME BOARD IN GOOD ORDER TOO xD
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

}


class Players {
    private static int count = 0;

    public Players() {
        count++;
    }

    public static void numberOfPlayers() {
        System.out.println(count);
    }
    public int flipDice() {

        return (int) (Math.random() * 6) + 1;
    }

}
