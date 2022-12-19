/*
Kevin Sbarski 324589480
Amit Sherman 209284017
 */
package TicTacToeProject;

public abstract class Player implements Runnable{
    Turn turn;
    public Player(Turn p){
        this.turn = p;
    }
}
