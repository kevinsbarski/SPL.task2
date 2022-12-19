package TicTacToeProject;

public abstract class Player implements Runnable{
    Turn turn;
    public Player(Turn p){
        this.turn = p;
    }
}
