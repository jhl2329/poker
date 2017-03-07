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
        return null;
    }
}
