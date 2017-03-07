package poker.hands;

import org.junit.Test;
import poker.CardHand;

import static org.junit.Assert.*;

/**
 * Created by Jae Lim on 3/7/2017.
 */
public class StraightTest {
    @Test
    public void compare1() throws Exception {
        String[] hand1 = {"JH", "10C", "9D", "8S", "7H"};
        String[] hand2 = {"JH", "10S", "9D", "8S", "7H"};

        CardHand c1 = new CardHand(hand1);
        CardHand c2 = new CardHand(hand2);

        assert((c1.compare(c2)) == null);
    }

    @Test
    public void compare2() throws Exception {
        String[] hand1 = {"QH", "JC", "10D", "9S", "8H"};
        String[] hand2 = {"JH", "10S", "9D", "8S", "7H"};

        CardHand c1 = new CardHand(hand1);
        CardHand c2 = new CardHand(hand2);

        assert((c1.compare(c2)).equals(c1));
    }

}