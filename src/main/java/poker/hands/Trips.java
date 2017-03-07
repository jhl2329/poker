package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class Trips extends BaseHand<Trips> {

    public Trips(CardHand cardHand, int[] cardValues) {
        super(cardHand, cardValues);
    }

    @Override
    public CardHand compare(Trips toCompare) {
        //Find the 3 cards
        int firstTrip, secondTrip;
        firstTrip = secondTrip = 0;

        for(Integer i : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(i);
            if(value == 3)
                firstTrip = i;
        }

        for(Integer i : toCompare.cardOccurrence.keySet()) {
            int value = toCompare.cardOccurrence.get(i);
            if(value == 3)
                secondTrip = i;
        }

        //Compare two trips
        if(firstTrip != secondTrip)
            return firstTrip > secondTrip ? this.cardHand : toCompare.cardHand;

        //If two trips are same, compare the kickers.
        for(int i = this.cardValues.length - 1; i >= 0 ; i--) {
            if(this.cardValues[i] != toCompare.cardValues[i])
                return this.cardValues[i] > toCompare.cardValues[i] ? this.cardHand : toCompare.cardHand;
        }

        //Two hands must be equal
        return null;
    }
}
