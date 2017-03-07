package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class Trips extends BaseHand {

    public Trips(CardHand cardHand, int[] cardValues) {
        super(cardHand, cardValues);
    }

    @Override
    CardHand compareSameRank(CardHand toCompare) {
        return null;
    }
}
