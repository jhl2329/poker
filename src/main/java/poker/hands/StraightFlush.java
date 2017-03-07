package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class StraightFlush extends BaseHand<StraightFlush> {

    public StraightFlush(CardHand cardHand, int[] cardValues) {
        super(cardHand, cardValues);
    }

    //The comparison is the same for a StraightFlush compared to a Straight
    @Override
    public CardHand compare(StraightFlush toCompare) {

        //Check which Straight has the higher ranking card
        if(this.cardValues[4] != toCompare.cardValues[4]) {
            return this.cardValues[4] > toCompare.cardValues[4] ? this.cardHand : toCompare.cardHand;
        }

        //If higher ranking card is the same, then two straights are equal
        return null;
    }
}
