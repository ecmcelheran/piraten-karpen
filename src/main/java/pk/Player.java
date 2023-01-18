package pk;

import java.util.Random;

public class Player {

    int score, gold, diamonds, wins;
    public Player(){
        score=0;
        gold=0;
        diamonds=0;
        wins=0;
    }

    public void addGold() {
        gold++;
    }

    public void addDiamonds(){
        diamonds++;
    }

    public void setScore(){
        score = (gold+diamonds)*100;
    }

    public int getCoins(){
        return gold;
    }

    public int getDiamonds(){
        return diamonds;
    }

    public int getScore(){

        return score;
    }
    public void addWin(){
        wins++;
    }
    public int getWins(){
        return wins;
    }

    public void playTurn(){
        Random rand = new Random();
        int skulls=0,points=0,gold=0,diamond=0;
        Faces[] val = new Faces[8];
        int c= rand.nextInt(2);

        //need 8 separate dice
        Dice[] mydice = new Dice[8];

        System.out.println("You rolled:");

        //initialize and roll all 8 die and record values
        for(int i=0; i<8; i++){
            mydice[i] = new Dice();
            val[i] = mydice[i].roll();
            System.out.print(val[i] + "\t");
            if(val[i]==Faces.SKULL){
                skulls+=1;
            }
        }

        //continue playing until 3 skulls rolled, or player chooses to stop rolling
        while(skulls<3 && c==1){
            skulls = 0;
            //randomly select 2 unique die values to roll again
            int num1 = (rand.nextInt(6)+1);
            int num2 = (rand.nextInt(6)+1);

            //rolls cannot be skulls
            while(val[num1]==Faces.SKULL){
                num1 = (rand.nextInt(6)+1);
            }
            //rolls also cannot be the same die
            while(val[num2]==Faces.SKULL || num2==num1){
                num2 = (rand.nextInt(6)+1);
            }

            val = reroll(mydice, val, num1, num2);

            //reprint dice values
            for (Faces value : val) {
                System.out.print(value + "\t");
            }
            System.out.println("\n");
            //System.out.println("You rolled..." + val[num1] + " and " + val[num2]);

            //traverse die to check for skulls
            for (Faces faces : val) {
                if (faces == Faces.SKULL) {
                    skulls += 1;
                }
            }

            //player choice -- do they want to roll again
            c = rand.nextInt(2);
        }

        for (Faces faces : val) {
            if (faces == Faces.GOLD) {
                gold++;
            } else if (faces == Faces.DIAMOND) {
                diamonds++;
            }
        }

        setScore();
    }

    public static Faces[] reroll(Dice[] dicearr, Faces[] val, int... dice){
        for (int die : dice) {
            val[die] = dicearr[die].roll();
        }
        return val;
    }

}
