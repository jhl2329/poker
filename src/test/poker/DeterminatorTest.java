package poker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jae Lim on 3/8/2017.
 */
public class DeterminatorTest {

    @Test
    public void testRF() {
        String[] hand1 = {"10H", "JH", "QH", "KH", "AH"};

        CardHand c1 = new CardHand(hand1);

        assert(c1.findBestHand() == HandRankingValue.ROYALFLUSH);
    }

    @Test
    public void testRF2() {
        String[] hand1 = {"10H", "JH", "QH", "KH", "AH", "AD", "AC", "AS"};

        CardHand c1 = new CardHand(hand1);

        assert(c1.findBestHand() == HandRankingValue.ROYALFLUSH);

    }

    @Test
    public void testRF3() {
        String[] hand1 = {"10H", "JH", "QH", "KH", "AH", "9H", "8H", "7H", "6H"};

        CardHand c1 = new CardHand(hand1);

        assert(c1.findBestHand() == HandRankingValue.ROYALFLUSH);

    }
}