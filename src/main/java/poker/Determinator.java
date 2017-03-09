package poker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poker.hands.BaseHand;
import poker.hands.StraightFlush;

import java.util.*;

/**
 * Created by Jae Lim on 3/7/2017.
 */
public class Determinator {
    Logger logger = LoggerFactory.getLogger(CardHand.class);


    private ArrayList<Card> cards;
    private int[] cardValues;
    private HashMap<Integer, Integer> cardOccurrence;
    private BaseHand baseHand;
    private CardHand cardHand;

    public Determinator(ArrayList<Card> cards, int[] cardValues, HashMap<Integer, Integer> cardOccurrence) {
        this.cards = cards;
        this.cardValues = cardValues;
        this.cardOccurrence = cardOccurrence;
    }

    public HandRankingValue determine() {
        if (cardOccurrence.containsValue(4))
            return checkAboveQuads() != null ? checkAboveQuads() : HandRankingValue.FOUROFAKIND;
        else if (cardOccurrence.containsValue(3))
            return checkAboveTrips() != null ? checkAboveTrips() : HandRankingValue.THREEOFAKIND;
        else if (cardOccurrence.containsValue(2))
            return checkAbovePair();
        else
            return checkAboveHigh();
    }

    public CardHand getBestCards() {
        return this.cardHand;
    }

    private HandRankingValue checkAboveQuads() {
        if(validRoyalFlush())
            return HandRankingValue.ROYALFLUSH;
        else if(validStraightFlush())
            return HandRankingValue.STRAIGHTFLUSH;
        return null;
    }

    private HandRankingValue checkAboveTrips() {
        //Still need to check for Royal Flush and Straight Flush
        if(checkAboveQuads() != null)
            return checkAboveQuads();
        if(validFullHouse())
            return HandRankingValue.FULLHOUSE;
        else if(validFlush())
            return HandRankingValue.FLUSH;
        else if(validStraight())
            return HandRankingValue.STRAIGHT;
        return null;
    }

    private HandRankingValue checkAbovePair() {
        //Check for RF and SF
        HandRankingValue aboveTrips = checkAboveTrips();

        if(aboveTrips != null)
            return aboveTrips;
        else
            return validTwoPair() ? HandRankingValue.TWOPAIR : HandRankingValue.PAIR;
    }

    private HandRankingValue checkAboveHigh() {
        HandRankingValue aboveTrips = checkAboveTrips();
        if(aboveTrips != null)
            return aboveTrips;
        return HandRankingValue.HIGHCARD;

    }

    private boolean validStraightFlush() {
        ArrayList<String> validHand = new ArrayList<>();
        boolean isSF = false;
        for (int i = 0; i < this.cards.size(); i++) {
            //Check 5 cards from i to i + 5
            int j = i;
            validHand.add(this.cards.get(i).toString());
            while(j < i + 4 && j < this.cards.size() -1) {
                Card c1 = this.cards.get(j);
                Card c2 = this.cards.get(j + 1);
                if(c1.sameSuit(c2) && c1.isConsecutive(c2))
                    validHand.add(c2.toString());
                j++;
            }
            if(j == i + 4 && validHand.size() == 5) { //Means while loop was able to get 5 cards and we have a valid hand
                this.cardHand = new CardHand(validHand.toArray(new String[0]));
                isSF = true;
            }
            validHand.clear();
        }
        return isSF;
    }

    private boolean validRoyalFlush() {
        ArrayList<String> validHand = new ArrayList<>();
        boolean isConsecutive = true;
        for (int i = 0; i < this.cards.size(); i++) {
            Card c1 = this.cards.get(i);
            if (c1.getValue() == 10) {
                int j = i;
                validHand.add(this.cards.get(i).toString());
                while(j < i + 4 && isConsecutive && j < this.cards.size() - 1) {
                    c1 = this.cards.get(j);
                    Card c2 = this.cards.get(j + 1);
                    isConsecutive = c1.sameSuit(c2) && c1.isConsecutive(c2);
                    validHand.add(c2.toString());
                    j++;
                }
                if (j == i + 4) {
                    this.cardHand = new CardHand((validHand.toArray(new String[0])));
                    return true;
                }
                validHand.clear();
            }
        }
        return false;
    }

    private boolean validFullHouse() {
        //List of cards where count in cardOccurrence == 2
        int highestTwo, highestThree;
        highestTwo = highestThree = -1;
        for (Integer key : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(key);
            if (value == 2 && key > highestTwo)
                highestTwo = key;
            else if (value == 3 && key > highestThree) {
                //If there exists a higher 3 count, the old 3 count might be bigger than the twoCount
                if(highestThree > highestTwo)
                    highestTwo = highestThree;
                highestThree = key;
            }
        }
        if (highestTwo == -1 || highestThree == -1)
            //FullHouse only possible with 2s and 3s
            return false;

        ArrayList<String> validHand = new ArrayList<>();
        int twosCount, threesCount;
        twosCount = threesCount = 0;
        for(int i = 0; i < this.cards.size(); i ++) {
            Card c = this.cards.get(i);
            if(c.sameValue(highestTwo) && twosCount < 2){
                twosCount++;
                validHand.add(c.toString());
            }
            else if(c.sameValue(highestThree) && threesCount < 3) {
                threesCount++;
                validHand.add(c.toString());
            }
        }
        //If above conditional didn't trigger, must be valid FullHouse of some sort
        this.cardHand = new CardHand(validHand.toArray(new String[0]));
        return true;
    }

    private boolean validFlush() {
        boolean flush = false;
        ArrayList<String> validHand = new ArrayList<>();
        for (int i = 0; i < this.cards.size(); i++) {
            int j = i;
            validHand.add(this.cards.get(i).toString());
            while(j < i + 4 && j < this.cards.size() - 1) {
                Card c1 = this.cards.get(j);
                Card c2 = this.cards.get(j + 1);
                if(c1.sameSuit(c2))
                    validHand.add(c2.toString());
                j++;
            }
            if(j == i + 4 && validHand.size() == 5) {
                this.cardHand = new CardHand(validHand.toArray(new String[0]));
                flush = true;
            }
            validHand.clear();
        }
        return flush;
    }

    //Code for validStraight is pretty similar to validStraightFlush except no need to check suit
    private boolean validStraight() {
        Collections.sort(this.cards, Card.COMPARE_BY_VALUE);
        logger.info(this.cards.toString());
        ArrayList<String> validHand = new ArrayList<>();
        boolean isStraight = false;
        for (int i = 0; i < this.cards.size(); i++) {
            //Check 5 cards from i to i + 5
            int j = i;
            validHand.add(this.cards.get(i).toString());
            while(j < i + 4 && j < this.cards.size() - 1) {
                Card c1 = this.cards.get(j);
                Card c2 = this.cards.get(j + 1);
                if(c1.isConsecutive(c2))
                    validHand.add(c2.toString());
                j++;
            }
            if(j == i + 4 && validHand.size() == 5) {
                this.cardHand = new CardHand(validHand.toArray(new String[0]));
                isStraight = true;
            }
            validHand.clear();
        }
        return isStraight;
    }

    private boolean validTwoPair() {
        ArrayList<Integer> twos = new ArrayList<>();
        ArrayList<Integer> ones = new ArrayList<>(); //Ones can be empty if there are 6 cards and they're all pairs
        for (Integer key : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(key);
            if (value == 2)
                //Add pairs
                twos.add(key);
            else if (value == 1)
                //Add kickers
                ones.add(key);
        }
        //To be a valid two pair, must have at least two pairs
        return twos.size() >= 2;
    }

    private boolean validPair() {
        //No need to check for additional pairs because if two or more pairs exist, it'd be considered a TwoPair
        for (Integer key : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(key);
            if (value == 2)
                return true;
        }
        //If no keys have occurrence of 2, then no pairs exist
        return false;
    }

    @Override
    public String toString() {
        return this.cardHand.toString();
    }
}
