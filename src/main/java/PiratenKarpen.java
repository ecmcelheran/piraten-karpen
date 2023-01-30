import org.apache.commons.cli.*;
import pk.Simulation;

public class PiratenKarpen {

    public static void main(String[] args) {
        //Handle command line
        //create command line parser
        CommandLineParser parser = new DefaultParser();

        //default settings
        String mode = "fatal";
        int games = 42;
        int[] strategies = new int[2];
        int r=0, c=0;

        //options
        Options options = new Options();
        options.addOption("tr",false,"turn on trace mode");
        options.addOption(Option.builder("games").hasArg().desc("play GAMES many games in a simulation").build());
        options.addOption("r", "random", true, "random strategy for <arg> players");
        options.addOption("c", "combo", true, "combo strategy for <arg> players");

        //parse command line
        try {
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption("tr")){
                mode = "trace";
            }

            if(cmd.hasOption("games")){
                games = Integer.parseInt(cmd.getOptionValue("games"));
            }

            if(cmd.hasOption("r")){
                try{
                    r = Integer.parseInt(cmd.getOptionValue("r"));
                }catch(Exception e){
                    r=1;
                }
            }
            if(cmd.hasOption("c")){
                try{
                    c = Integer.parseInt(cmd.getOptionValue("c"));
                }catch(Exception e){
                    c=1;
                }
            }
            if(r+c>=2 && r+c<=5){
                strategies[0] = r;
                strategies[1] = c;
            }
            else{
                strategies[0] = 2;
            }

        } catch (ParseException exp) {
            System.err.println("Parsing Failed -- replacing with built-in settings\n");
            help(options);
        }

        //run simulation
        Simulation sim = new Simulation(mode, games, strategies);
    }

    public static void help(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("mvn exec:java -Dexec.args=\"[options]\"", options);
        System.out.println();
    }


}