package poker.hands;

import org.junit.Test;
import poker.CardHand;

import static org.junit.Assert.*;

/**
 * Created by Jae Lim on 3/6/2017.
 */
public class TwoPairTest {
    @Test
    public void compare1() throws Exception {
        String[] hand1 = {"JH", "JC", "3C", "3S", "2H"};
        String[] hand2 = {"JD", "JS", "4D", "4H", "2H"};

        CardHand c1 = new CardHand(hand1);
        CardHand c2 = new CardHand(hand2);

        assert((c1.compare(c2)).equals(c2));
    }

    @Test
    public void compare2() throws Exception {
        String[] hand1 = {"AH", "AC", "3C", "3S", "2H"};
        String[] hand2 = {"JD", "JS", "4D", "4H", "2H"};

        CardHand c1 = new CardHand(hand1);
        CardHand c2 = new CardHand(hand2);

        assert((c1.compare(c2)).equals(c1));
    }

    @Test
    public void compare3() throws Exception {
        String[] hand1 = {"AH", "AC", "3C", "3S", "2H"};
        String[] hand2 = {"AD", "AS", "3D", "3H", "2H"};

        CardHand c1 = new CardHand(hand1);
        CardHand c2 = new CardHand(hand2);

        assert((c1.compare(c2)) == null);
    }

}