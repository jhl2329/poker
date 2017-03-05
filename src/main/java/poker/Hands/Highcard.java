package poker.Hands;

import poker.Hand;
import poker.HandRankings;

/**
 * Created by bryan on 3/4/2017.
 */
public class Highcard extends Hand {

    public Highcard(HandRankings handRanking) {
        this.setHandRanking(handRanking);
    }
    @Override
    public boolean compare(Hand hand) {
        return false;
    }

    @Override
    public boolean compareSameRank(Hand hand) {
        return false;
    }
}
