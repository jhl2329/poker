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

    @Test
    public void testSF() {
        String[] hand1 = {"6H", "7H", "8H", "9H", "10H"};

        CardHand c = new CardHand(hand1);

        assert(c.findBestHand() == HandRankingValue.STRAIGHTFLUSH);
    }

    @Test
    public void testSF2() {
        String[] hand1 = {"6H", "7H", "8H", "9H", "10H", "10D", "10C", "10S"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand() == HandRankingValue.STRAIGHTFLUSH);
    }

    @Test
    public void testSF3() {
        String[] hand1 = {"6H", "7H", "8H", "9H", "10H", "10D", "10C", "10S", "JH", "QH", "KH"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand() == HandRankingValue.STRAIGHTFLUSH);
    }

    @Test
    public void testFH() {
        String[] hand1 = {"QH", "QD", "QS", "KH", "KD"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand() == HandRankingValue.FULLHOUSE);
    }

    @Test
    public void testFH2() {
        String[] hand1 = {"2H", "2C", "3H", "3C", "3D", "4H", "4C", "4D"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand() == HandRankingValue.FULLHOUSE);
    }

    @Test
    public void testFlush() {
        String[] hand1 = {"QH", "10H", "8H", "6H", "4H"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand() == HandRankingValue.FLUSH);
    }

    @Test
    public void testFlush2() {
        String[] hand1 = {"QH", "10H", "8H", "6H", "4H", "KD", "AC", "JC"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand() == HandRankingValue.FLUSH);
    }

    @Test
    public void testFlush3() {
        String[] hand1 = {"QH", "10H", "8H", "6H", "4H", "KD", "AC", "JC", "KH", "AH"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand() == HandRankingValue.FLUSH);
    }

    @Test
    public void testStraight() {
        String[] hand1 = {"10C", "JD", "QH", "KC", "AS"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand() == HandRankingValue.STRAIGHT);
    }

    @Test
    public void testStraight2() {
        String[] hand1 = {"10C", "JD", "QH", "KC", "AS", "AD", "AC"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand() == HandRankingValue.STRAIGHT);
    }

    @Test
    public void testStraight3() {
        String[] hand1 = {"8C", "4H", "5D", "6C", "7S","10D", "JD", "QH", "KC", "AS", "AD", "AC"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand() == HandRankingValue.STRAIGHT);
    }
}