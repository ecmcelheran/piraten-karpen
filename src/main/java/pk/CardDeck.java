package pk;
import java.util.Stack;
import java.util.Collections;

public class CardDeck {
    Stack<Card> cards = new Stack<>();

    public CardDeck(){
        setCards();
    }

    public void setCards(){
        //initialize cards
        for(int i=0; i<6; i++){
            cards.add(new Card(CardFace.SeaBattle, (i*100),i));
        }
        for(int i=6; i<35; i++){
            cards.add(new Card(CardFace.nop, 0,0));
        }

        shuffle();
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public Card draw(){
        if(!isEmpty()){
            return cards.pop();
        }
        else{
            //shuffle and reuse deck
            setCards();
            return draw();
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }


}
