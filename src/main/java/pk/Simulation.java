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

    public Simulation(String mode, int games, int[] args){
        System.out.println("Welcome to Piraten Kapern!");
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.getLevel(mode.toUpperCase()));
        this.games = games;
        int num_players = args[0] + args[1];

        player = new Player[num_players];

        wins = new int[num_players];

        //instantiate dice
        for(int i=0; i<mydice.length; i++){
            mydice[i] = new Dice();
        }
        //instantiate players
        for(int i=0; i<args[0]; i++){
            player[i] = new Player("random");
        }
        for(int i=args[0]; i<num_players; i++){
            player[i] = new Player("combo");
        }

        for(int i=0; i<games; i++){
            logger.trace("--------GAME " + (i+1) + "--------");
            playGame();
            for(Player p : player){
                p.reset();
            }
        }
        for(int i=0; i<player.length;i++){
            System.out.printf("Player " + (i+1) + " won %1.2f%% of games.\n", wins[i]*100.0/games);
        }
    }

    public void playGame(){
        CardDeck deck = new CardDeck();
        int winner = 0;
        boolean gameContinue = true;

        //game continues as long as player scores are lower than 6000
        while(gameContinue){

            for(int i=0; i<player.length; i++){
                logger.debug("Player " + (i+1) + "'s turn");
                player[i].playTurn(mydice, deck);
            }

            //check player scores
            for(Player p: player){
                if(!p.gameContinue()){
                    gameContinue = false;
                    break;
                }
            }

            StringBuilder scoreInfo= new StringBuilder();
            for(int i=0; i<player.length; i++){
                scoreInfo.append("\nPlayer ").append(i + 1).append(" score: ").append(player[i].getScore());
            }
            logger.info(scoreInfo.toString());
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