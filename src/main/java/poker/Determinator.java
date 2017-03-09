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
        boolean isConsecutive = true;
        for (int i = 0; i < this.cards.size(); i++) {
            //Check 5 cards from i to i + 5
            int j = i;
            validHand.add(this.cards.get(i).toString());
            while(j < i + 4 && isConsecutive && j < this.cards.size() -1) {
                Card c1 = this.cards.get(j);
                Card c2 = this.cards.get(j + 1);
                isConsecutive = c1.sameSuit(c2) && c1.isConsecutive(c2);
                if(isConsecutive)
                    validHand.add(c2.toString());
                j++;
            }
            if(j == i + 4) {
                this.cardHand = new CardHand(validHand.toArray(new String[0]));
            }
            validHand.clear();
        }
        if (isConsecutive) {
            return true;
        }
        return false;
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
        ArrayList<Integer> twosList = new ArrayList<>();
        ArrayList<Integer> threesList = new ArrayList<>();
        for (Integer key : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(key);
            if (value == 2)
                twosList.add(key);
            else if (value == 3)
                threesList.add(key);
        }
        if (twosList.isEmpty() || threesList.isEmpty())
            //No cards with occurrence == 2, so FullHouse not possible
            return false;
//            return -1;
        Collections.sort(twosList);

        //If above conditional didn't trigger, must be something in twosList
        return true;
//        return twosList.get(twosList.size() - 1);
    }

    private boolean validFlush() {
        Set<Integer> cardSet = this.cardOccurrence.keySet();
        boolean isFlush = true;
        ArrayList<String> validHand = new ArrayList<>();
        Card highestFlush = null;
        for (int i = 0; i < cardSet.size() - 1; i++) {
            int j = i;
            validHand.add(this.cards.get(i).toString());
            while(j < i + 4 && isFlush && j < cardSet.size() - 1) {
                Card c1 = this.cards.get(j);
                Card c2 = this.cards.get(j + 1);
                isFlush = c1.sameSuit(c2);
                if(isFlush)
                    validHand.add(c2.toString());
                j ++;
            }
            if(j == i + 4) {
                this.cardHand = new CardHand(validHand.toArray(new String[0]));
                logger.info("Valid flush:");
            }
//                highestFlush = this.cards.get(i);
        }

        return isFlush ? true : false;
//        return highestFlush;
    }

    //Code for validStraight is pretty similar to validStraightFlush except no need to check suit
    private boolean validStraight() {
        Card highestConsecutive = null;
        for (int i = 0; i < this.cards.size(); i++) {
            //Check 5 cards from i to i + 5
            int j = i;
            boolean isConsecutive = true;
            while(j < i + 4 && isConsecutive && j < this.cards.size() - 1) {
                Card c1 = this.cards.get(j);
                Card c2 = this.cards.get(j + 1);
                isConsecutive = c1.isConsecutive(c2);
                j++;
            }
            if(j == i + 4)
                return true;
//                highestConsecutive = this.cards.get(i);
        }
        return false;
//        return highestConsecutive;
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
