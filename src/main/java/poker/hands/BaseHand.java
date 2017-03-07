package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public abstract class BaseHand {

    private CardHand cardHand;

    public CardHand compare(CardHand toCompare) {
        if(this.cardHand.sameRank(toCompare)) {
            return compareSameRank(toCompare);
        }
        return compareDifferentRank(toCompare);
    }

    private CardHand compareDifferentRank(CardHand toCompare) {
        return this.cardHand.compare(toCompare);
    }

    abstract CardHand compareSameRank(CardHand toCompare);
}
