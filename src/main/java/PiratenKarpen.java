import pk.Simulation;
import org.apache.logging.log4j.Level;


public class PiratenKarpen {

    public static void main(String[] args) {
        //command line arguments determine mode (i.e. trace mode), number of games and player strategies
        String mode = "fatal";
        int games = 42;
        String[] strategies;

        if(args.length>7){
            System.out.println("Cannot use requested settings for simulation -- using built-in settings");
            strategies = new String[0];
        }
        else{
            //mode and games
            try{
                mode = args[0];
                games = Integer.parseInt(args[1]);
                strategies = init(args,2);

                if(Level.getLevel(mode.toUpperCase())==null){
                    throw new Exception();
                }

            }catch(Exception e1){
                //just mode
                try{
                    mode = args[0];
                    strategies = init(args,1);
                    if(Level.getLevel(mode.toUpperCase())==null){
                        throw new Exception();
                    }
                }
                catch(Exception e2){
                    //just game
                    try{
                        games = Integer.parseInt(args[1]);
                        strategies = init(args,1);
                    }
                    //all strategy
                    catch(Exception e3){
                        strategies = init(args,0);
                    }
                }
            }
        }

        Simulation sim = new Simulation(mode, games, strategies);

    }

    public static String[] init(String[] args, int start){
        String[] array = new String[args.length-start];
        if (args.length - start >= 0) System.arraycopy(args, start, array, 0, args.length - start);
        return array;
    }
}