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

        //instantiate level, number of games, players, dice -- prepare everything for simulation
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

        Game[] game = new Game[games];

        //play all simulation games
        for(int i=0; i<games; i++){
            logger.trace("--------GAME " + (i+1) + "--------");
            //playGame();
            game[i] = new Game(player, mydice);
            wins[game[i].getWinner()]++;

            //reset player scores after each game
            for(Player p : player){
                p.reset();
            }
        }

        //output player winning percentages
        for(int i=0; i<player.length;i++){
            System.out.printf("Player " + (i+1) + " won %1.2f%% of games.\n", wins[i]*100.0/games);
        }
    }

}