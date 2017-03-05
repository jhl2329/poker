package poker;

/**
 * Created by bryan on 3/4/2017.
 */
public abstract class Hand {
    private HandRankings handRanking;

    protected abstract boolean compare(Hand hand);

    protected abstract boolean compareSameRank(Hand hand);

    public void setHandRanking(HandRankings handRanking) {
        this.handRanking = handRanking;
    }
}
