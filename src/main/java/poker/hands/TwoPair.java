package poker.hands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poker.Application;
import poker.CardHand;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class TwoPair extends BaseHand<TwoPair>{
    Logger logger = LoggerFactory.getLogger(TwoPair.class);

    private HashMap<Integer, Integer> occurrence;

    public TwoPair(CardHand cardHand, int[] cardValues, HashMap<Integer, Integer> occurrence) {
        super(cardHand, cardValues);
        this.occurrence = occurrence;
    }

    @Override
    public CardHand compare(TwoPair toCompare) {
        logger.info("" + Arrays.toString(this.cardValues));
        logger.info("" + Arrays.toString(toCompare.cardValues));
        ArrayList<Integer> firstList = new ArrayList<>();
        ArrayList<Integer> secondList = new ArrayList<>();

        int firstKicker, secondKicker;
        firstKicker = secondKicker = 0;

        //Get pairs and kickers for two hands
        for(Integer i : this.occurrence.keySet()) {
            int value = this.occurrence.get(i);
            if(value == 2)
                firstList.add(i);
            else
                firstKicker = i;
        }

        for(Integer i : toCompare.occurrence.keySet()) {
            int value = toCompare.occurrence.get(i);
            if(value == 2)
                secondList.add(i);
            else
                secondKicker = i;
        }

        //Sort pairs by order and see which has the higher pair
        Collections.sort(firstList);
        Collections.sort(secondList);

        for(int i = firstList.size() - 1; i >=  0; i--) {
            logger.info("Compare " + firstList.get(i) + " with " + secondList.get(i));
            if(firstList.get(i) != secondList.get(i))
                return firstList.get(i) > secondList.get(i) ? this.cardHand : toCompare.cardHand;
        }

        //Compare kickers
        if(firstKicker != secondKicker)
            return firstKicker > secondKicker ? this.cardHand : toCompare.cardHand;

        //Two hands must be equal
        return null;
    }
}
