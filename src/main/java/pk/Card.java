package pk;

public class Card {

    CardFace face;

    int swords, points, skulls;

    public Card(CardFace face){
        this.face = face;
    }
    public Card(CardFace face, int skulls){
        this(face);
        this.skulls = skulls;
    }

    public Card(CardFace face, int points, int swords){
        this(face);
        this.points = points;
        this.swords = swords;
    }


    public int getSwords(){
        return swords;
    }

    public int getSkulls(){
        return skulls;
    }

    public int getPoints(){
        return points;
    }


}
