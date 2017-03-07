package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class RoyalFlush extends BaseHand<RoyalFlush> {

    public RoyalFlush(CardHand cardHand, int[] cardValues) {
        super(cardHand, cardValues);
    }

    //Royal Flushes are equal in rank to other Royal Flushes
    @Override
    public CardHand compare(RoyalFlush toCompare) {
        return null;
    }
}
