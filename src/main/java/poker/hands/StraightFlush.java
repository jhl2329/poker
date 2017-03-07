package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class StraightFlush extends BaseHand<StraightFlush> {

    public StraightFlush(CardHand cardHand, int[] cardValues) {
        super(cardHand, cardValues);
    }

    @Override
    public CardHand compare(StraightFlush toCompare) {
        return null;
    }
}
