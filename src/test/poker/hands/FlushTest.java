package poker.hands;

import org.junit.Test;
import poker.CardHand;

/**
 * Created by Jae Lim on 3/7/2017.
 */
public class FlushTest {

    @Test
    public void compare1() throws Exception {
        String[] hand1 = {"JH", "8H", "2H", "4H", "3H"};
        String[] hand2 = {"JH", "8H", "2H", "4H", "3H"};

        CardHand c1 = new CardHand(hand1);
        CardHand c2 = new CardHand(hand2);

        assert((c1.compare(c2)) == null);
    }

    @Test
    public void compare2() throws Exception {
        String[] hand1 = {"JH", "8H", "2H", "4H", "3H"};
        String[] hand2 = {"JS", "8S", "2S", "4S", "3S"};

        CardHand c1 = new CardHand(hand1);
        CardHand c2 = new CardHand(hand2);

        assert((c1.compare(c2)) == null);
    }


    @Test
    public void compare3() throws Exception {
        String[] hand1 = {"QH", "8H", "2H", "4H", "3H"};
        String[] hand2 = {"JS", "8S", "2S", "4S", "3S"};

        CardHand c1 = new CardHand(hand1);
        CardHand c2 = new CardHand(hand2);

        assert((c1.compare(c2)).equals(c1));
    }
}