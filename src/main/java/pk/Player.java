package pk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Player {
    private static final Logger logger = LogManager.getLogger(Player.class);
    int score;
    //Monkey, Parrot, Gold, Diamond, Saber, Skull;
    int[] values = new int[6];
    String strat;


    public Player(String strat){
        score = 0;
        this.strat = strat;
        logger.debug("Player strategy: " + strat);
    }

    public void reset(){
        score=0;
        Arrays.fill(values, 0);
    }

    public void setScore(Card card){
        if(card.face.equals(CardFace.Gold)){
            values[2]++;
        }
        else if(card.face.equals(CardFace.Diamond)){
            values[3]++;
        }

        int turnScore = (values[2]+values[3])*100;

        if(card.face.equals(CardFace.SeaBattle)){
            turnScore+=card.getPoints();
        }

        //combine parrot and monkey dice if card is Monkey Business
        if(card.face.equals(CardFace.MonkeyBusiness)){
            values[0]+=values[1];
            //ensure combos are not counted twice
            values[1]=0;
        }
        //check for combos
        for(int i=0; i<values.length-1; i++){
            switch (values[i]) {
                case 3 -> turnScore += 100;
                case 4 -> turnScore += 200;
                case 5 -> turnScore += 500;
                case 6 -> turnScore += 1000;
                case 7 -> turnScore += 2000;
                case 8 -> turnScore += 4000;
                default -> {
                }
            }
        }

        if(card.face.equals(CardFace.Captain)){
            turnScore*=2;
            logger.trace("Player score from turn multiplied by 2");
        }

        score+=turnScore;
    }

    public void setScore(int bonus, Card card){
        score+=bonus;
        setScore(card);
    }

    public void penalty(int scoreReduction){
        score-=scoreReduction;
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
        boolean success;

        card = deck.draw();
        logger.trace("Card Drawn: " + card.face);

        //values is an array of values all 0
        clear(values, card);

        logger.trace("You rolled:");
        rollAll(mydice);
        updateValues(mydice);

        if (card.face.equals(CardFace.SeaBattle) && strat.equals("combo")) {
            success = seaBattleStrategy(mydice, card);
            if(!success){
                penalty(card.points);
            }
        } else if (strat.equals("combo")) {
            success = comboStrategy(mydice, card);
        } else {
            success = randomStrategy(mydice, card);
        }

        //only add to score if turn is a 'success' (less than 3 skulls, and if sea battle, gets enough swords)
        if (success) {
                setScore(card);
        }
    }

    public boolean randomStrategy(Dice[] mydice, Card card){
        Random rand = new Random();

        //continue playing until 3 skulls rolled and while player 'chooses' to continue
        while(values[5]<3 && rand.nextBoolean()){
            clear(values, card);

            randReroll(mydice,2);

            logValues(mydice);

            updateValues(mydice);

        }
        return values[5]<3;
    }

    public boolean comboStrategy(Dice[] mydice, Card card){
        boolean diceToRoll = true;

        int max=0;
        Faces goal = Faces.Gold;

        //continue playing until 3 skulls rolled, or player 'chooses' to stop rolling
        while(values[5]<3 && diceToRoll){
            //find goal
            for(int i=0; i<values.length-1; i++){
                if(values[i]>max){
                    max = values[i];
                    goal = Faces.values()[i];
                }
            }

            logger.trace("GOAL: " + goal);

            diceToRoll = comboReroll(mydice, goal);

            logValues(mydice);

            clear(values, card);

            updateValues(mydice);

        }

        return values[5]<3;

    }

    public boolean seaBattleStrategy(Dice[] mydice, Card card){
        int goal = card.getSwords();
        logger.trace("GOAL: " + goal + " swords in sea battle");

        //continue rolling until swords goal is reached or 3 skulls are rolled
        //maximum swords needed is 4, turn ends if 3 skulls -- there will always be at least 2 dice to roll while turn continues
        while(values[5]<3 && values[4]<goal){

            //reroll with combo strategy - goal is to get swords
            comboReroll(mydice, Faces.Sword);
            //log and update dice values
            logValues(mydice);

            //'clear' values from previous roll
            clear(values, card);
            //update values with die values from new roll
            updateValues(mydice);

        }
        //was player successful in getting the number of swords required?
        return values[4] >= goal && values[5]<3;

    }

    public void updateValues(Dice[] mydice){
        for (Dice die : mydice) {
            //Monkey, Parrot, Gold, Diamond, Saber, Skull;
            //track number of dice with each face value
            for(Faces f : Faces.values()){
                if(die.val == f){
                    values[f.ordinal()]++;
                }
            }
        }
    }

    public boolean comboReroll(Dice[] mydice, Faces goal){

        //check there are at least 2 dice to re-roll
        int roll=0;
        for(int i=0; i<Faces.values().length; i++){
            if(i!=goal.ordinal() && i!=5){
                roll += values[i];
            }
        }
        //if there are enough dice to re-roll, roll dice that are not involved in desired combo
        if(roll>1){
            for(Dice die: mydice) {
                if (die.val != goal && die.val != Faces.Skull) {
                    die.val = die.roll();
                }
            }
            return true;
        }

        return false;

    }


    public void randReroll(Dice[] mydice,int roll_num){
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
        StringBuilder dicevalues= new StringBuilder();
        //log dice values
        for (Dice die : mydice) {
            dicevalues.append(die.val).append("\t");
        }
        logger.trace(dicevalues.toString());
    }

    public void clear(int[] values, Card card){
        Arrays.fill(values, 0);
        //add skulls from card drawn
        if(card.face.equals(CardFace.Skull)){
            values[5] += card.getSkulls();
        }
    }
}//end of class
