package poker.hands;

import poker.CardHand;

import java.util.HashMap;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class Quads extends BaseHand<Quads> {

    private HashMap<Integer, Integer> cardOccurrence;

    public Quads(CardHand cardHand, int[] cardValues, HashMap<Integer, Integer> cardOccurrence) {
        super(cardHand, cardValues);
        this.cardOccurrence = cardOccurrence;
    }

    //Higher ranked set of 4 wins
    @Override
    public CardHand compare(Quads toCompare) {
        //Find 4s
        int firstFour, secondFour;
        firstFour = secondFour = 0;

        for(Integer i : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(i);
            if(value == 4)
                firstFour = i;
        }

        for(Integer i : toCompare.cardOccurrence.keySet()) {
            int value = toCompare.cardOccurrence.get(i);
            if(value == 4)
                secondFour = i;
        }

        return firstFour > secondFour ? this.cardHand : toCompare.cardHand;
    }
}
