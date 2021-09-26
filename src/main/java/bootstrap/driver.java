package bootstrap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.Player;
import domain.board;
import domain.dice;
public class driver {

    private static dice die = new dice();
    public static void main(String[] args)throws IOException{
        System.out.println("*********************************Welcome to Snakes & Ladders by Trevor D'Costa******************************");
        Scanner scan = new Scanner (System.in);

        int noOfPlayer = 0;
        System.out.print("Enter the no. of player (2-6): " );
        noOfPlayer = scan.nextInt();

        if (noOfPlayer <= 1 ){
            System.out.println("Invalid number");
            return;
        }
        else if (noOfPlayer > 6)
        {
            System.out.println("Sorry We can't take in "+noOfPlayer+" players");
            return;
        }

        List<Player> players = new ArrayList();
        for (int id = 0; id < noOfPlayer; id++){
            Player player = new Player("P" + id);
            players.add(player);
        }

        board board = new board(players);
        boolean done = false;
        int playerId = 0;
        while (!done){
            Player currPlayer = players.get(playerId);
            int roll = currPlayer.takeTurn();
            done = board.movePlayer(currPlayer, roll);
            System.out.println(board);
            System.out.println("-----------------------\n");
            if (done){
                System.out.println(currPlayer + " wins");
            }
            playerId++;
            if (playerId == noOfPlayer){
                playerId = 0;
            }
        }
    }
}
