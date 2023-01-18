import pk.Dice;
import pk.Faces;
import pk.Player;

import java.util.Random;

public class PiratenKarpen {

    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        Player winner;
        int num_games = 42;

        System.out.println("Welcome to Piraten Karpen Simulator!");

        //number of games
        for(int i=0; i<num_games;i++){
            System.out.println("\n---GAME " + (i+1) + "---\n");
            winner = game(player1, player2);
            if(winner==player1){
                player1.wins++;
            }
            else if(winner==player2){
                player1.wins++;
            }
        }

        System.out.println("Player 1 won " + player1.wins + " games. \nPlayer 2 won " + player2.wins + " games.");

    }

    public static Player game(Player player1, Player player2){
        Player winner;

        //game ends when a player reaches 6000 points, all other players get another turn
        while(player1.score<6000 && player2.score<6000){
            System.out.println("PLAYER 1\n");
            turn(player1);
            System.out.println();
            System.out.println("PLAYER 2\n");
            turn(player2);

            System.out.println("\nPlayer 1 score: " + player1.score + "\nPlayer 2 score: " + player2.score + "\n\n");
        }

        if(player1.score>player2.score){
            winner = player1;
        }
        else{
            winner = player2;
        }

        return winner;
    }

    public static void turn(Player player){
        int skulls=0,points=0,gold=0,diamond=0;
        Faces[] val = new Faces[8];

        //need 8 separate dice
        Dice[] mydice = new Dice[8];

        System.out.println("You rolled:");

        //initialize and roll all 8 die and record values
        for(int i=0; i<8; i++){
            mydice[i] = new Dice();
            val[i] = mydice[i].roll();
            System.out.print(val[i] + "\t");
            if(val[i]==Faces.SKULL){
                skulls+=1;
            }
        }
    
    }
}
