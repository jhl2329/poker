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
        return null;
    }
}
