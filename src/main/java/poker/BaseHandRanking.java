package poker;

/**
 * Created by Jae Lim on 3/4/2017.
 */
public abstract class BaseHandRanking {
    private HandRankingValue handRanking;

    protected abstract boolean compare(BaseHandRanking baseHandRanking);

    protected abstract boolean compareSameRank(BaseHandRanking baseHandRanking);

    public void setHandRanking(HandRankingValue handRanking) {
        this.handRanking = handRanking;
    }
}
