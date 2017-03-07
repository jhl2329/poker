package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class Straight extends BaseHand<Straight> {

    public Straight(CardHand cardHand, int[] cardValues) {
        super(cardHand, cardValues);
    }

    @Override
    public CardHand compare(Straight toCompare) {

        //Check which Straight has the higher ranking card
        if(this.cardValues[this.cardValues.length - 1] != toCompare.cardValues[toCompare.cardValues.length - 1]) {
            return this.cardValues[this.cardValues.length - 1] > toCompare.cardValues[toCompare.cardValues.length - 1] ?
                    this.cardHand : toCompare.cardHand;
        }

        //If higher ranking card is the same, then two straights are equal
        return null;
    }
}
