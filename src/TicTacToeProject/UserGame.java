/*
Kevin Sbarski 324589480
Amit Sherman 209284017
 */
package TicTacToeProject;

public class UserGame extends  Game{
    boolean playerTurn;

    public UserGame(){
        gameBoard = new Turn[3][3];
        playerTurn = true;
    }



    public synchronized void checkIfWon(){
        for(int i = 0 ; i < 3 ; i ++)
            if(gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0]  == gameBoard[i][2] && gameBoard[i][0] != null) {
                System.out.println(gameBoard[i][0] + " WON!");
                gameOver = true;
            }
        for(int i = 0 ; i < 3 ; i ++)
            if(gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i]  == gameBoard[2][i] && gameBoard[0][i] != null) {
                System.out.println(gameBoard[0][i] + " WON!");
                gameOver = true;
            }
        if(((gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) || (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0])) && gameBoard[1][1] != null) {
            System.out.println(gameBoard[1][1] + " WON!");
            gameOver = true;

        }
    }

    @Override
    public synchronized Turn getTurn() {
        if (playerTurn)
            return Turn.X;
        return Turn.O;
    }

    @Override
    public synchronized Cell[] getFreeCells() {
        int count = 0;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(gameBoard[i][j] == null){
                    count++;
                }
            }
        }
        Cell[] emptyCells = new Cell[count];
        int index = 0;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(gameBoard[i][j] == null){
                    emptyCells[index++] = new Cell(i,j);
                }
            }
        }
        return emptyCells;
    }

    public void StartGame(){
        UserPlayer user = new UserPlayer(Turn.X,this);
        SelfPlayer ai = new SelfPlayer(Turn.O,this);
        Thread userThread = new Thread(user);
        Thread aiThread = new Thread(ai);
        userThread.start();
        aiThread.start();
    }
}
