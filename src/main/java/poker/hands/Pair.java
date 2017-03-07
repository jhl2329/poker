package poker.hands;

import poker.CardHand;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class Pair extends BaseHand<Pair> {

    public Pair(CardHand cardHand, int[] cardValues) {
        super(cardHand, cardValues);
    }

    @Override
    public CardHand compare(Pair toCompare) {
        Set<Integer> firstOccurrence = new HashSet<>();
        Set<Integer> secondOccurrence = new HashSet<>();
        int firstPair, secondPair;
        firstPair = secondPair = 0;

        //Find which cards are the pairs for the two hands
        for(int i = 0; i < this.cardValues.length; i++) {
            if(!firstOccurrence.add(cardValues[i]))
                firstPair = cardValues[i];
            if(!secondOccurrence.add(toCompare.cardValues[i]))
                secondPair = toCompare.cardValues[i];
        }

        //Return the higher pair
        if (firstPair != secondPair)
            return firstPair > secondPair ? this.cardHand : toCompare.cardHand;

        //Compare kickers
        for(int i = 0; i < this.cardValues.length; i++) {
            if (this.cardValues[i] != toCompare.cardValues[i])
                return this.cardValues[i] > toCompare.cardValues[i] ? this.cardHand : toCompare.cardHand;
        }

        //Cards are equal
        return null;
    }

}
