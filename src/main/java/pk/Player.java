package pk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Player {
    private static final Logger logger = LogManager.getLogger(Player.class);
    int score;
    //Monkey, Parrot, Gold, Diamond, Saber, Skull;
    int[] values = new int[6];
    String strat;

    public Player(){
        score=0;
        for(int i=0; i<values.length; i++){
            values[i]=0;
        }
        strat = "random";
    }
    public Player(String strat){
        this();
        this.strat = strat;
    }

    public void reset(){
        score=0;
        for(int i=0; i<values.length; i++){
            values[i]=0;
        }
    }

    public void setScore(){
        score = (values[2]+values[3])*100;
        //check for combos
        for(int i=0; i<values.length-1; i++){
            switch (values[i]){
                case 3:
                    score+=100;
                    break;
                case 4:
                    score+=200;
                    break;
                case 5:
                    score+=500;
                    break;
                case 6:
                    score+=1000;
                    break;
                case 7:
                    score+=2000;
                    break;
                case 8:
                    score+=4000;
                    break;
                default:
                    break;
            }
        }
    }

    public String showScore(){
        return Integer.toString(score);
    }

    public int getScore(){
        return score;
    }

    public boolean gameContinue(){
        return score<6000;
    }

    public static int rollAll(Dice[] mydice){
        int skulls =0;
        for(int i=0; i<8; i++){
            mydice[i] = new Dice();
            mydice[i].roll();
            logger.trace(mydice[i].val + "\t");
            if(mydice[i].val==Faces.Skull){
                skulls+=1;
            }
        }
        return skulls;
    }

    public void playTurn(Dice[] mydice){
        if(strat.equals("combo")){
            comboStrategy(mydice);
        }
        else{
            randomStrategy(mydice);
        }
    }

    public void randomStrategy(Dice[] mydice){
        Random rand = new Random();
        int skulls=0;

        logger.trace("You rolled:");
        skulls=rollAll(mydice);

        //continue playing until 3 skulls rolled and while payer 'chooses' to continue
        while(skulls<3 && rand.nextBoolean()){
            skulls = 0;

            randReroll(mydice,2);

            //log dice values
            for (Dice die : mydice) {
                logger.trace(die.val);
            }

            //traverse die to check for skulls
            for (Dice die : mydice) {
                if (die.val == Faces.Skull) {
                    skulls += 1;
                }
            }
        }
        if(skulls<3){
            updateScore(mydice);
        }
    }

    public void comboStrategy(Dice[] mydice){
        //maximize
        Random rand = new Random();
        int skulls=0;

        logger.trace("You rolled:");
        skulls=rollAll(mydice);

        //continue playing until 3 skulls rolled
        while(skulls<3 && rand.nextBoolean()){
            skulls = 0;

            randReroll(mydice,2);

            //log dice values
            for (Dice die : mydice) {
                logger.trace(die.val);
            }

            //traverse die to check for skulls
            for (Dice die : mydice) {
                if (die.val == Faces.Skull) {
                    skulls += 1;
                }
            }
        }
        if(skulls<3){
            updateScore(mydice);
        }
    }

    public void updateScore(Dice[] mydice){
        for (Dice die : mydice) {
            //Monkey, Parrot, Gold, Diamond, Saber, Skull;
            for(Faces f : Faces.values()){
                values[f.ordinal()]++;
            }
        }
        setScore();
    }

    public static void randReroll(Dice[] mydice,int roll_num){
        Random rand = new Random();
        int[] num = new int[roll_num];
        logger.trace("Re-roll: ");

        for(int n : num){
            num[n]=(rand.nextInt(6)+1);
        }

        //rolls cannot be skulls
        while(mydice[num[0]].val==Faces.Skull){
            num[0] = (rand.nextInt(6)+1);
        }
        //rolls also cannot be the same die
        while(mydice[num[1]].val==Faces.Skull || num[1]==num[0]){
            num[1] = (rand.nextInt(6)+1);
        }

        for (int n : num) {
            mydice[n].val = mydice[n].roll();
        }
    }


}
