package poker.Hands;

import poker.BaseHandRanking;
import poker.HandRankingValue;

/**
 * Created by bryan on 3/4/2017.
 */
public class Highcard extends BaseHandRanking {

    public Highcard(HandRankingValue handRanking) {
        this.setHandRanking(handRanking);
    }
    @Override
    public boolean compare(BaseHandRanking baseHandRanking) {
        return false;
    }

    @Override
    public boolean compareSameRank(BaseHandRanking baseHandRanking) {
        return false;
    }
}
