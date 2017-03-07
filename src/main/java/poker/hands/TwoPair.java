package poker.hands;

import poker.CardHand;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class TwoPair extends BaseHand<TwoPair>{

    private HashMap<Integer, Integer> occurrence;

    public TwoPair(CardHand cardHand, int[] cardValues, HashMap<Integer, Integer> occurrence) {
        super(cardHand, cardValues);
        this.occurrence = occurrence;
    }

    @Override
    public CardHand compare(TwoPair toCompare) {
        ArrayList<Integer> firstList, secondList;
        firstList = secondList = new ArrayList<>();

        int firstKicker, secondKicker;
        firstKicker = secondKicker = 0;

        for(Integer i : this.occurrence.keySet()) {
            int value = this.occurrence.get(i);
            if(value == 2)
                firstList.add(value);
            else {
                firstKicker = value;
            }
        }

        for(Integer i : toCompare.occurrence.keySet()) {

        }
        return null;
    }
}
