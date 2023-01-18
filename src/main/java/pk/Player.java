package pk;

public class Player {

    public int score, gold, diamonds, wins;
    public Player(){
        score=0;
        gold=0;
        diamonds=0;
        wins=0;
    }

    public void setCoins(int gold) {
        this.gold = gold;
    }

    public void setDiamonds(int diamonds){
        this.diamonds = diamonds;
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
}
