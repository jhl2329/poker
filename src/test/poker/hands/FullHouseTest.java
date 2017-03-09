package poker.hands;

import org.junit.Test;
import poker.CardHand;

import static org.junit.Assert.*;

/**
 * Created by Jae Lim on 3/7/2017.
 */
public class FullHouseTest {
    @Test
    public void compare1() throws Exception {
        String[] hand1 = {"JH", "JD", "JS", "4H", "4S"};
        String[] hand2 = {"QS", "QD", "QH", "4C", "4D"};
        String[] hand3 = {"10H", "JH", "QH", "KH", "AH", "9D", "3H", "9H", "JC"};

        CardHand c1 = new CardHand(hand1);
        CardHand c2 = new CardHand(hand2);
        CardHand c3 = new CardHand(hand3);

        c1.findBestHand();
        c2.findBestHand();
        c3.findBestHand();
        assert((c1.compare(c2)).equals(c2));
    }

}