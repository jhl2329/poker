package poker.Hands;

import poker.BaseHandRanking;
import poker.HandRankingValue;

/**
 * Created by bryan on 3/4/2017.
 */
public class Pair extends BaseHandRanking {

    public Pair(HandRankingValue handRanking) {
        this.setHandRanking(handRanking);
    }

    @Override
    protected boolean compare(BaseHandRanking baseHandRanking) {
        return false;
    }

    @Override
    protected boolean compareSameRank(BaseHandRanking baseHandRanking) {
        return false;
    }
}
