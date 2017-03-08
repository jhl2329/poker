package poker;

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
}
