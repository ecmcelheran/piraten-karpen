package pk;

import java.util.Random;

public class PlayGame {
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
                player1.addWin();
            }
            else if(winner==player2){
                player1.addWin();
            }
        }

        System.out.println("Player 1 won " + player1.getWins() + " games. \nPlayer 2 won " + player2.getWins() + " games.");

    }

    public static Player game(Player player1, Player player2){
        Player winner;

        //game ends when a player reaches 6000 points, all other players get another turn
        while(player1.getScore()<6000 && player2.getScore()<6000){
            System.out.println("PLAYER 1\n");
            turn(player1);
            System.out.println();
            System.out.println("PLAYER 2\n");
            turn(player2);

            System.out.println("\nPlayer 1 score: " + player1.getScore() + "\nPlayer 2 score: " + player2.getScore() + "\n\n");
        }

        if(player1.getScore()>player2.getScore()){
            winner = player1;
        }
        else{
            winner = player2;
        }

        return winner;
    }

    public static void turn(Player player) {
        player.playTurn();
    }
}
