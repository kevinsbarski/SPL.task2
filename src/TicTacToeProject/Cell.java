/*
Kevin Sbarski 324589480
Amit Sherman 209284017
 */
package TicTacToeProject;

public class Cell {

    int x;
    int y;
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public Cell(int x,int y){
        this.x =x;
        this.y =y;
    }
}
