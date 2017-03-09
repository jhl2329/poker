package poker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jae Lim on 3/8/2017.
 */
public class DeterminatorTest {

    @Test
    public void testRF1() {
        String[] hand1 = {"10H", "JH", "QH", "KH", "AH"};

        CardHand c1 = new CardHand(hand1);

        assert(c1.findBestHand().equals("Rank: ROYALFLUSH, values: 10H JH QH KH AH "));
    }

    @Test
    public void testRF2() {
        String[] hand1 = {"10H", "JH", "QH", "KH", "AH", "AD", "AC", "AS"};

        CardHand c1 = new CardHand(hand1);

        assert(c1.findBestHand().equals("Rank: ROYALFLUSH, values: 10H JH QH KH AH "));
    }

    @Test
    public void testRF3() {
        String[] hand1 = {"10H", "JH", "QH", "KH", "AH", "9H", "8H", "7H", "6H"};

        CardHand c1 = new CardHand(hand1);

        assert(c1.findBestHand().equals("Rank: ROYALFLUSH, values: 10H JH QH KH AH "));
    }

    @Test
    public void testSF1() {
        String[] hand1 = {"6H", "7H", "8H", "9H", "10H"};

        CardHand c = new CardHand(hand1);

        assert(c.findBestHand().equals("Rank: STRAIGHTFLUSH, values: 6H 7H 8H 9H 10H "));
    }

    @Test
    public void testSF2() {
        String[] hand1 = {"6H", "7H", "8H", "9H", "10H", "10D", "10C", "10S"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: STRAIGHTFLUSH, values: 6H 7H 8H 9H 10H "));
    }

    @Test
    public void testSF3() {
        String[] hand1 = {"6H", "7H", "8H", "9H", "10H", "10D", "10C", "10S", "JH", "QH", "KH"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: STRAIGHTFLUSH, values: 9H 10H JH QH KH "));
    }

    @Test
    public void testQuads1() {
        String[] hand1 = {"AH", "AD", "AC", "AS", "KH"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: FOUROFAKIND, values: AC AD KH AH AS "));
    }

    @Test
    public void testQuads2() {
        String[] hand1 = {"AH", "AD", "AC", "AS", "KH", "KD", "KS", "KC"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: FOUROFAKIND, values: AC AD AH KS AS "));
    }

    @Test
    public void testQuads3() {
        String[] hand1 = {"QH", "QD", "QS", "KH", "KD", "QC"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: FOUROFAKIND, values: QC QD QH KH QS "));
    }
    @Test
    public void testFH1() {
        String[] hand1 = {"QH", "QD", "QS", "KH", "KD"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: FULLHOUSE, values: QD KD QH KH QS "));
    }

    @Test
    public void testFH2() {
        String[] hand1 = {"2H", "2C", "3H", "3C", "3D", "4H", "4C", "4D"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: FULLHOUSE, values: 3C 4C 3D 4D 4H "));
    }

    @Test
    public void testFlush1() {
        String[] hand1 = {"QH", "10H", "8H", "6H", "4H"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: FLUSH, values: 4H 6H 8H 10H QH "));
    }

    @Test
    public void testFlush2() {
        String[] hand1 = {"QH", "10H", "8H", "6H", "4H", "KD", "AC", "JC"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: FLUSH, values: 4H 6H 8H 10H QH "));
    }

    @Test
    public void testFlush3() {
        String[] hand1 = {"QH", "10H", "8H", "6H", "4H", "KD", "AC", "JC", "KH", "AH"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: FLUSH, values: 8H 10H QH KH AH "));
    }

    @Test
    public void testStraight1() {
        String[] hand1 = {"10C", "JD", "QH", "KC", "AS"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: STRAIGHT, values: 10C KC JD QH AS "));
    }

    @Test
    public void testStraight2() {
        String[] hand1 = {"10C", "JD", "QH", "KC", "AS", "AD", "AC"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: STRAIGHT, values: 10C KC JD QH AS "));
    }

    @Test
    public void testStraight3() {
        String[] hand1 = {"8C", "4H", "5D", "6C", "7S","10D", "JD", "QH", "KC", "AS", "AD", "AC"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: STRAIGHT, values: KC 10D JD QH AS "));
    }

    @Test
    public void testTrips1() {
        String[] hand1 = {"10C", "10D", "10H", "KC", "AS"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: THREEOFAKIND, values: 10C KC 10D 10H AS "));
    }

    @Test
    public void testTrips2() {
        String[] hand1 = {"10C", "10D", "10H", "2C", "3D", "AH"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: THREEOFAKIND, values: 10C 3D 10D 10H AH "));
    }

    @Test
    public void testTwoPair1() {
        String[] hand1 = {"2H", "2D", "5D", "5H", "KS"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: TWOPAIR, values: 2D 5D 2H 5H KS "));
    }

    @Test
    public void testTwoPair2() {
        String[] hand1 = {"2H", "2D", "5D", "5H", "KS", "KD"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: TWOPAIR, values: 5D KD 2H 5H KS "));
    }

    @Test
    public void testTwoPair3() {
        String[] hand1 = {"2H", "2D", "5D", "KS", "KD", "AS", "AD"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: TWOPAIR, values: 5D KD AD KS AS "));
    }

    @Test
    public void testPair1() {
        String[] hand1 = {"2H", "2D", "3D", "5H", "QS", "KD"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: PAIR, values: 2D KD 2H 5H QS "));
    }

    @Test
    public void testHighCard() {
        String[] hand1 = {"2H", "4C", "6D", "8C", "AH"};

        CardHand c = new CardHand(hand1);
        assert(c.findBestHand().equals("Rank: HIGHCARD, values: 4C 8C 6D 2H AH "));
    }
}