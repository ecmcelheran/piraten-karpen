package pk;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Simulation {
    private static final Logger logger = LogManager.getLogger(Simulation.class);

    int[] wins;
    Player[] player;
    //game comes with 8 dice
    Dice[] mydice = new Dice[8];

    int games;

    public Simulation(String mode, int games, String... args){
        System.out.println("Welcome to Piraten Kapern!");
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.getLevel(mode.toUpperCase()));
        this.games = games;
        if(args.length>1){
            player = new Player[args.length];
        }
        else{
            player = new Player[2];
        }

        wins = new int[player.length];
        for(int w: wins){
            w=0;
        }

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
        for(int i=0; i<player.length;i++){
            System.out.printf("Player " + (i+1) + " won %1.2f%% of games.\n", wins[i]*100.0/games);
        }
    }

    public void playGame(){
        int winner = 0;
        boolean gameContinue = true;

        //game continues as long as player scores are lower than 6000
        while(gameContinue){

            for(int i=0; i<player.length; i++){
                logger.debug("Player " + (i+1) + "'s turn");
                player[i].playTurn(mydice);
            }

            //check player scores
            for(Player p: player){
                if(!p.gameContinue()){
                    gameContinue = false;
                    break;
                }
            }

            logger.info("Player 1 score: "+ player[0].showScore()+"\nPlayer 2 score: "+ player[1].showScore());
        }

        //check winner
        for(int i=0; i<player.length; i++){
            if(player[i].getScore()>player[winner].getScore()){
                winner = i;
            }
        }

        wins[winner]++;
    }
}