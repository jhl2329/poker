package poker;

/**
 * Created by bryan on 3/4/2017.
 */
public enum HandRankings {
    HIGHCARD(0),
    PAIR(1),
    TWOPAIR(2),
    THREEOFAKIND(3),
    STRAIGHT(4),
    FLUSH(5),
    FULLHOUSE(6),
    FOUROFAKIND(7),
    STRAIGHTFLUSH(8),
    ROYALFLUSH(9);

    private final int value;

    HandRankings(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
