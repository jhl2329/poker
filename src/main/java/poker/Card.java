package poker;

import java.util.Comparator;

/**
 * Created by Jae Lim on 3/7/2017.
 */
public class Card implements Comparable<Card>{

    private int value;
    private char suit;

    public Card(int value, char suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public char getSuit() {
        return suit;
    }

    public boolean sameSuit(Card c) {
        return this.suit == c.suit;
    }

    public boolean isConsecutive(Card c) {
        return this.value + 1 == c.value;
    }

    public String prettyCard() {
        StringBuilder sb = new StringBuilder();
        switch(this.value) {
            case 11:
                sb.append("J");
                break;
            case 12:
                sb.append("Q");
                break;
            case 13:
                sb.append("K");
                break;
            case 14:
                sb.append("A");
                break;
            default:
                sb.append(this.value);
                break;
        }
        sb.append(this.suit);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "" + this.value + this.suit;
    }

    @Override
    public int compareTo(Card c) {
        if(this.suit == c.suit)
            return this.value > c.value ? 1 : -1;
        return this.suit > c.suit ? 1 : -1;
    }

    public boolean sameValue(int i) {
        return this.value == i;
    }

    //Although most Cards will needed be sorted by suit and then flush for easier processing of things like
    //Straight Flush or Royal Flush, there is an instance where sorting by value is much easier
    public static Comparator<Card> COMPARE_BY_VALUE = new Comparator<Card>() {

        @Override
        public int compare(Card c1, Card c2) {
            return c1.getValue() > c2.getValue() ? 1 : -1;
        }
    };
}
