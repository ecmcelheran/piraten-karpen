package pk;

import java.util.Random;

public class Player {
    public int score, gold, diamonds;
    public Player(){
        score=0;
        gold=0;
        diamonds=0;
    }

    public void reset(){
        score=0;
        gold=0;
        diamonds=0;
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

    public int getScore(){

        return score;
    }

    public static int rollAll(Dice[] mydice){
        int skulls =0;
        for(int i=0; i<8; i++){
            mydice[i] = new Dice();
            mydice[i].roll();
            System.out.print(mydice[i].val + "\t");
            if(mydice[i].val==Faces.SKULL){
                skulls+=1;
            }
        }
        System.out.println();
        return skulls;
    }

    public void playTurn(Dice[] mydice){
        Random rand = new Random();
        int skulls=0;

        System.out.println("You rolled:");
        skulls=rollAll(mydice);

        //continue playing until 3 skulls rolled
        while(skulls<3){
            skulls = 0;

            reroll(mydice,2);

            //reprint dice values
            for (Dice die : mydice) {
                System.out.print(die.val + "\t");
            }
            System.out.println("\n");
            //System.out.println("You rolled..." + val[num1] + " and " + val[num2]);

            //traverse die to check for skulls
            for (Dice die : mydice) {
                if (die.val == Faces.SKULL) {
                    skulls += 1;
                }
            }
        }

        updateScore(mydice);
    }

    public void updateScore(Dice[] mydice){
        for (Dice die : mydice) {
            if (die.val == Faces.GOLD) {
                gold++;
            } else if (die.val == Faces.DIAMOND) {
                diamonds++;
            }
        }
        setScore();
    }

    public static void reroll(Dice[] mydice,int roll_num){
        Random rand = new Random();
        int[] num = new int[roll_num];
        System.out.println("Re-roll: ");

        for(int n : num){
            num[n]=(rand.nextInt(6)+1);
        }

        //rolls cannot be skulls
        while(mydice[num[0]].val==Faces.SKULL){
            num[0] = (rand.nextInt(6)+1);
        }
        //rolls also cannot be the same die
        while(mydice[num[1]].val==Faces.SKULL || num[1]==num[0]){
            num[1] = (rand.nextInt(6)+1);
        }

        for (int n : num) {
            mydice[n].val = mydice[n].roll();
        }
    }


}
