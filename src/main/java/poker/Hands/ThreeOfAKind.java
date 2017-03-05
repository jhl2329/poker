package poker.Hands;

import poker.BaseHandRanking;
import poker.HandRankingValue;

/**
 * Created by Jae Lim on 3/4/2017.
 */
public class ThreeOfAKind extends BaseHandRanking {

    public ThreeOfAKind(HandRankingValue handRankingValue) {
        this.setHandRanking(handRankingValue);
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
