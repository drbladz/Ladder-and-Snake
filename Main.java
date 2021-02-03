import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // write your code here
       LadderAndSnake yo = new LadderAndSnake(2);

    }
}


class LadderAndSnake {

int nbOfPlayers;
    public LadderAndSnake(int nbOfPlayers) {
        this.nbOfPlayers = nbOfPlayers;

        //THE GAME BOARD IN GOOD ORDER TOO xD
        int board[][] = new int[10][10];
        int maxNumber = 100;
        int reverseNum = 90;
        for (int i = 0; i<10; i++){
            if (i%2==0) {
                for (int j = 0; j < 10; j++) {
                    board[i][j] = maxNumber;
                    System.out.print(board[i][j] + "\t");
                    maxNumber--;
                    reverseNum++;
                }
            }
            else{
                reverseNum = maxNumber-9;
                for(int j = 0; j<10; j++){
                    board[i][j] = reverseNum;
                    System.out.print(board[i][j]+"\t");
                    maxNumber--;
                    reverseNum++;
                }
            }
            System.out.println();
        }


    }


}

