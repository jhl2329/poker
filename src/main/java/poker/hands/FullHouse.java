package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class FullHouse extends BaseHand {

    public FullHouse(CardHand cardHand, int[] cardValues) {
        super(cardHand, cardValues);
    }

    @Override
    CardHand compareSameRank(CardHand toCompare) {
        return null;
    }
}
