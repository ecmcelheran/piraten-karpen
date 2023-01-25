import pk.Simulation;

public class PiratenKarpen {

    public static void main(String[] args) {
        //command line arguments determine mode (i.e. trace mode), number of games and player strategies
        //args mode, games, strategies
        try {
            if (args.length == 4) {
                String[] strategies = {args[2], args[3]};
                Simulation sim = new Simulation(args[0], Integer.parseInt(args[1]), strategies);
            } else if (args.length == 3) {
                String[] strategies = {args[1], args[2]};
                Simulation sim = new Simulation("fatal", Integer.parseInt(args[0]), strategies);

            } else if (args.length == 2) {
                Simulation sim = new Simulation("fatal", 42, args);
            } else {
                String arr[] = new String[0];
                try {
                    Simulation sim = new Simulation("fatal", Integer.parseInt(args[0]), arr);
                } catch (Exception e) {
                    Simulation sim = new Simulation(args[0], 42, arr);
                }
            }
        }catch(Exception e) {
            String arr[] = new String[0];
            Simulation sim = new Simulation("fatal", 42, arr);
        }

    }
}