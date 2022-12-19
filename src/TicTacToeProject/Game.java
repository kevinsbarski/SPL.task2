/*
Kevin Sbarski 324589480
Amit Sherman 209284017
 */
package TicTacToeProject;

public  abstract class  Game  {
    protected Turn[][] gameBoard;
    public abstract Turn getTurn();
    public abstract Cell[] getFreeCells();
    public boolean gameOver;
    public final Object lock;

    public Game(){
        gameBoard = new Turn[3][3];
        gameOver = false;
        lock = new Object();
    }
    public synchronized void printBoard() {
        for (int i = 0 ; i < gameBoard.length ; i++){
            for ( int j = 0 ; j < gameBoard[0].length ; j++){
                if(gameBoard[i][j] == null)
                System.out.print(gameBoard[i][j]+"    ");
                else {
                    System.out.print(gameBoard[i][j]+"      ");
                }
            }
            System.out.println();
        }
    }
    public boolean notFull(){
        for (int i = 0 ; i < gameBoard.length ; i++){
            for (int j = 0 ; j < gameBoard[0].length ; j++){
                if (gameBoard[i][j] == null)
                    return true;
            }
        }
        gameOver = true;
        return false;
    }
}
