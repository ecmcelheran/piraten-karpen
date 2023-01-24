package pk;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Simulation {
    private static final Logger logger = LogManager.getLogger(Simulation.class);

    int[] wins = {0,0};
    Player[] player = new Player[2];
    //game comes with 8 dice
    Dice[] mydice = new Dice[8];

    int games;


    public Simulation(int games, String[] args){
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.TRACE);
        System.out.println("Welcome to Piraten Kapern!");

        this.games = games;

        //instantiate dice
        for(int i=0; i<mydice.length; i++){
            mydice[i] = new Dice();
        }
        //instantiate players
        for(int i=0; i<player.length; i++){
            try {
                player[i] = new Player(args[i]);
            } catch(Exception e){
                player[i] = new Player();
            }
        }

        for(int i=0; i<games; i++){
            logger.trace("--------GAME " + (i+1) + "--------");
            playGame();
            player[0].reset();
            player[1].reset();
        }
        System.out.printf("Player 1 won %1.2f%% of games.\nPlayer 2 won %1.2f%% of games.",wins[0]*100.0/42.0,wins[1]*100.0/42.0);
    }

    public void playGame(){
        //game continues as long as player scores are lower than 6000
        while(player[0].gameContinue() && player[1].gameContinue()){

            logger.info("-PLAYER 1-\n");
            player[0].playTurn(mydice);
            logger.info("-PLAYER 2-\n");
            player[1].playTurn(mydice);

            logger.info("Player 1 score: "+ player[0].showScore()+"\nPlayer 2 score: "+ player[1].showScore());
        }

        if(player[0].getScore()>player[1].getScore()){
            wins[0]++;
        }
        else{
            wins[1]++;
        }
    }
}