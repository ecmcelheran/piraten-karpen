package pk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private static final Logger logger = LogManager.getLogger(Simulation.class);
    int winner;
    Player[] player;
    Dice[] mydice;
    CardDeck deck = new CardDeck();

    public Game(Player[] player, Dice[] mydice){
        this.player = player;
        this.mydice = mydice;;

        playGame();
    }

    public void playGame(){
        //game continues while player scores are all below 6000
        while(gameContinue()){
            for(int i=0; i<player.length; i++){
                logger.trace("Player " + (i+1) + "'s turn");
                player[i].playTurn(mydice, deck);
            }

            logScores();
        }
    }

    public int getWinner(){
        //check winner of game
        for(int i=0; i<player.length; i++){
            if(player[i].getScore()>player[winner].getScore()){
                winner = i;
            }
        }

        return winner;
    }

    public void logScores(){
        StringBuilder scoreInfo= new StringBuilder();
        for(int i=0; i<player.length; i++){
            scoreInfo.append("\nPlayer ").append(i + 1).append(" score: ").append(player[i].getScore());
        }
        logger.info(scoreInfo.toString());
    }

    public boolean gameContinue(){
        //check player scores
        for(Player p: player){
            if(p.getScore()>6000){
                return false;
            }
        }

        return true;
    }




}
