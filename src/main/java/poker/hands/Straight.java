package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class Straight extends BaseHand {

    public Straight(CardHand cardHand, int[] cardValues) {
        super(cardHand, cardValues);
    }

    @Override
    CardHand compareSameRank(CardHand toCompare) {
        return null;
    }
}
