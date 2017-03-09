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

        c1.findBestHand();
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
        String[] hand2 = {"JS", "8S", "2S", "4S", "3S", "3D", "3C"};
        String[] hand3 = {"3H", "7S", "3S", "QD", "AH", "3D", "4S"};

        CardHand c1 = new CardHand(hand1);
        CardHand c2 = new CardHand(hand2);
        CardHand c3 = new CardHand(hand3);

        c2.findBestHand();

//        assert((c1.compare(c2)).equals(c1));
    }
}