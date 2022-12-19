package TicTacToeProject;

import java.util.Scanner;
public class Main {

    public static void main(String[] args){
        System.out.println("In order to initialize user vs comp version insert 1.");
        System.out.println("In order to initialize user vs comp insert any other integer number.");

        Scanner s = new Scanner(System.in);
        int typeOfGame = s.nextInt();
        if(typeOfGame == 1){
            System.out.println("the bored index are from 0 - 2\tinsert index SPACE index");
            UserGame newGame = new UserGame();
            newGame.StartGame();
        }
        else{
            SelfGame selfGame = new SelfGame();
            selfGame.StartGame();
        }



    }
}
