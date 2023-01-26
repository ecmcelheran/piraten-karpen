package pk;

public class Card {

    CardFace face;

    int swords, points;

    public Card(CardFace face){
        this.face = face;
    }

    public Card(CardFace face, int points, int swords){
        this(face);
        this.points = points;
        this.swords = swords;
    }

    public int getSwords(){
        return swords;
    }

}
