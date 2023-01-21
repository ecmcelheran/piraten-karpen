package pk;

public class Simulation {
    int[] wins = {0,0};
    Player[] player = new Player[2];
    Dice[] mydice = new Dice[8];

    int games;


    public Simulation(int games){
        System.out.println("Welcome to Piraten Kapern!");

        this.games = games;

        //instantiate dice
        for(int i=0; i<mydice.length; i++){
            mydice[i] = new Dice();
        }
        //instantiate players
        for(int i=0; i<player.length; i++){
            player[i] = new Player();
        }

        for(int i=0; i<games; i++){
            System.out.println("--------GAME " + (i+1) + "--------");
            playGame();
            player[0].reset();
            player[1].reset();
        }
        System.out.printf("Player 1 won %1.2f%% of games.\nPlayer 2 won  %1.2f%% of games.",wins[0]*100.0/42.0,wins[1]*100.0/42.0);
    }

    public void playGame(){
        //game ends when a player reaches 6000 points
        while(player[0].getScore()<6000 && player[1].getScore()<6000){

            System.out.println("-PLAYER 1-\n");
            player[0].playTurn(mydice);
            System.out.println();
            System.out.println("\n-PLAYER 2-\n");
            player[1].playTurn(mydice);

            System.out.println("\nPlayer 1 score: " + player[0].getScore() + "\nPlayer 2 score: " + player[1].getScore() + "\n\n");
        }

        if(player[0].getScore()>player[1].getScore()){
            wins[0]++;
        }
        else{
            wins[1]++;
        }
    }
}