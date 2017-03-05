package poker.Hands;

import poker.Hand;
import poker.HandRankings;

/**
 * Created by bryan on 3/4/2017.
 */
public class TwoPair extends Hand {

    public TwoPair(HandRankings handRankings) {
        this.setHandRanking(handRankings);
    }

    @Override
    protected boolean compare(Hand hand) {
        return false;
    }

    @Override
    protected boolean compareSameRank(Hand hand) {
        return false;
    }
}
