package pk;
import java.util.Stack;
import java.util.Collections;

public class CardDeck {
    Stack<Card> cards = new Stack<>();
    int[] seaPoints = {100,500,1000};

    public CardDeck(){
        setCards();
    }

    public void setCards(){
        //initialize cards
        //sea battle - 6 cards
        //2 of each  -> 2 swords and 100 points, 3 swords and 500 points, 4 swords and 1000 points
        for(int a=0; a<2; a++){
            for(int i=0; i<3; i++){
                cards.add(new Card(CardFace.SeaBattle, seaPoints[i],i+2));
            }
        }
        //gold - 4 cards
        for(int i=0; i<4; i++){
            cards.add(new Card(CardFace.Gold));
        }
        //diamond - 4 cards
        for(int i=0; i<4; i++){
            cards.add(new Card(CardFace.Diamond));
        }
        //monkey business - 4 cards
        for(int i=0; i<4; i++){
            cards.add(new Card(CardFace.MonkeyBusiness));
        }
        //captain - 4 cards
        for(int i=10; i<35; i++){
            cards.add(new Card(CardFace.Captain));
        }
        //skull - 5 cards
        //2 cards with 2 skulls, 3 cards with 1 skull
        for(int i=0; i<2; i++){
            cards.add(new Card(CardFace.Skull,2));
        }
        for(int i=0; i<3; i++){
            cards.add(new Card(CardFace.Skull, 1));
        }

        //remaining cards nop - no impact
        for(int i=0; i<8; i++){
            cards.add(new Card(CardFace.nop));
        }

        shuffle();
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public Card draw(){
        if(isEmpty()) {
            //shuffle and reuse deck
            setCards();
            return draw();
        }
        else{
            return cards.pop();
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }


}
