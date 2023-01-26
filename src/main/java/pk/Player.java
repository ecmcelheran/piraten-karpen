package pk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;

public class Player {
    private static final Logger logger = LogManager.getLogger(Player.class);
    int score;
    //Monkey, Parrot, Gold, Diamond, Saber, Skull;
    int[] values = new int[6];
    String strat;

    public Player(){
        score=0;
        strat = "random";
    }
    public Player(String strat){
        this();
        this.strat = strat;
    }

    public void reset(){
        score=0;
        Arrays.fill(values, 0);
    }

    public void setScore(CardFace card){
        score += (values[2]+values[3])*100;
        //combine parrot and monkey dice if card is Monkey Business
        if(card == CardFace.MonkeyBusiness){
            values[0]+=values[1];
            values[1]=0;
        }
        //check for combos
        for(int i=0; i<values.length-1; i++){
            switch (values[i]) {
                case 3 -> score += 100;
                case 4 -> score += 200;
                case 5 -> score += 500;
                case 6 -> score += 1000;
                case 7 -> score += 2000;
                case 8 -> score += 4000;
                default -> {
                }
            }
        }
    }

    public void setScore(int bonus, CardFace card){
        score+=bonus;
        setScore(card);
    }

    public void penalty(int scoreReduction){
        score-=scoreReduction;
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

    public void rollAll(Dice[] mydice){

        for(int i=0; i<8; i++){
            mydice[i] = new Dice();
            mydice[i].roll();
        }
        logValues(mydice);
    }

    public void playTurn(Dice[] mydice, CardDeck deck){
        Card card;
        Arrays.fill(values, 0);

        card = deck.draw();
        logger.trace("Card Drawn: " + card.face);

        logger.trace("You rolled:");
        rollAll(mydice);
        updateValues(mydice);

        switch (card.face){
            case SeaBattle:
                boolean success = seaBattleStrategy(mydice, card.getSwords());
                if(values[5]<3 && success){
                    setScore(card.points, card.face);
                }
                else{
                    penalty(card.points);
                }
                break;
            default:
                if(strat.equals("combo")){
                    comboStrategy(mydice);
                }
                else{
                    randomStrategy(mydice);
                }
                if(values[5]<3){
                    setScore(card.face);
                }
                break;
        }

    }

    public void randomStrategy(Dice[] mydice){
        Random rand = new Random();

        //continue playing until 3 skulls rolled and while player 'chooses' to continue
        while(values[5]<3 && rand.nextBoolean()){
            Arrays.fill(values, 0);

            randReroll(mydice,2);

            logValues(mydice);

            updateValues(mydice);

        }
    }

    public void comboStrategy(Dice[] mydice){
        //maximize
        Random rand = new Random();

        int max=0;
        Faces goal = Faces.Gold;

        //continue playing until 3 skulls rolled
        while(values[5]<3 && rand.nextBoolean()){
            //find goal
            for(int i=0; i<values.length-1; i++){
                if(values[i]>max){
                    max = values[i];
                    goal = Faces.values()[i];
                }
            }

            logger.trace("GOAL: " + goal);

            Arrays.fill(values, 0);

            comboReroll(mydice, goal);

            logValues(mydice);

            updateValues(mydice);

        }

    }

    public boolean seaBattleStrategy(Dice[] mydice, int goal){
        logger.trace("GOAL: " + goal + " swords in sea battle");

        //continue playing until 3 skulls rolled
        while(values[5]<3 && values[4]<goal){

            for(int i=0;i<values.length;i++){
                values[i]=0;
            }

            comboReroll(mydice, Faces.Sword);

            logValues(mydice);

            updateValues(mydice);

        }

        return values[4] >= goal;

    }

    public void updateValues(Dice[] mydice){
        for (Dice die : mydice) {
            //Monkey, Parrot, Gold, Diamond, Saber, Skull;
            for(Faces f : Faces.values()){
                if(die.val == f){
                    values[f.ordinal()]++;
                }
            }
        }
    }

    public static void comboReroll(Dice[] mydice, Faces goal){
        for(Dice die: mydice) {
            if (die.val != goal && die.val != Faces.Skull) {
                die.val = die.roll();
            }
        }
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

    public void logValues(Dice[] mydice){
        String dicevalues="";
        //log dice values
        for (Dice die : mydice) {
            dicevalues+=(die.val + "\t");
        }
        logger.trace(dicevalues);
    }
}//end of class
