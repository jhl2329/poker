package poker.hands;

import poker.CardHand;

import java.util.HashMap;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class FullHouse extends BaseHand<FullHouse> {

    private HashMap<Integer, Integer> cardOccurrence;

    public FullHouse(CardHand cardHand, int[] cardValues, HashMap<Integer, Integer> cardOccurrence) {
        super(cardHand, cardValues);
        this.cardOccurrence = cardOccurrence;
    }

    //Card with the higher ranking set of 3 cards wins
    @Override
    public CardHand compare(FullHouse toCompare) {
        int firstThree, secondThree;
        firstThree = secondThree = 0;

        for(Integer i : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(i);
            if(value == 3)
                firstThree = i;
        }

        for(Integer i : toCompare.cardOccurrence.keySet()) {
            int value = toCompare.cardOccurrence.get(i);
            if(value == 3)
                secondThree = i;
        }

        //In this case, no need to return null because having more than 4 cards of same value is impossible
        return firstThree > secondThree ? this.cardHand : toCompare.cardHand;
    }
}
