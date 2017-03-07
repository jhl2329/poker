package poker.hands;

import poker.CardHand;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class HighCard extends BaseHand {

    public HighCard(CardHand cardHand, int[] cardValues) {
        super(cardHand, cardValues);
    }

    @Override
    public CardHand compare(BaseHand toCompare) {
        int[] first = this.cardValues;
        int[] second = toCompare.cardValues;

        for(int i = 0; i < first.length; i++) {
            if(first[i] != second[i]) {
                return first[i] > second[i] ? this.cardHand : toCompare.cardHand;
            }
        }
        //If two hands are the same, would return null
        return null;
    }
}
