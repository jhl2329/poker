package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class Flush extends BaseHand<Flush> {

    public Flush(CardHand cardHand, int[] cardValues) {
        super(cardHand, cardValues);
    }

    @Override
    public CardHand compare(Flush toCompare) {
        //To compare Flushes, compare the highest ranking card until no cards are left
        for(int i = 4; i >= 0; i--) {
            if(this.cardValues[i] != toCompare.cardValues[i])
                return this.cardValues[i] > toCompare.cardValues[i] ? this.cardHand : toCompare.cardHand;
        }

        //Cards in both hands must be the same
        return null;
    }
}
