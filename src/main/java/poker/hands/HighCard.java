package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class HighCard extends BaseHand {
    @Override
    CardHand compareSameRank(CardHand toCompare) {
        return null;
    }
}
