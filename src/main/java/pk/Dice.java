package pk;
//import java.util.Arrays;
import java.util.Random;

public class Dice {

    Faces val;

    public Faces roll() {
        int howManyFaces = Faces.values().length;
        Random bag = new Random();
        val = Faces.values()[bag.nextInt(howManyFaces)];
        return val;
    }
    
}
