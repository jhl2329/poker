package poker.hands;

import poker.CardHand;

import java.util.HashMap;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public abstract class BaseHand<T extends BaseHand<T>> {

    CardHand cardHand;
    int[] cardValues;
    HashMap<Integer, Integer> cardOccurrence;

    public BaseHand(CardHand cardHand, int[] cardValues) {
        this.cardHand = cardHand;
        this.cardValues = cardValues;
        this.cardOccurrence = new HashMap<>();
    }

    public abstract CardHand compare(T toCompare);
}
