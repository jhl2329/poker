package poker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Jae Lim on 3/7/2017.
 */
public class Determinator {
    Logger logger = LoggerFactory.getLogger(CardHand.class);


    private ArrayList<Card> cards;
    private int[] cardValues;
    private HashMap<Integer, Integer> cardOccurrence;

    public Determinator(ArrayList<Card> cards, int[] cardValues, HashMap<Integer, Integer> cardOccurrence) {
        this.cards = cards;
        this.cardValues = cardValues;
        this.cardOccurrence = cardOccurrence;
    }

    public HandRankingValue determine() {
        if(validRoyalFlush() != null)
            return HandRankingValue.ROYALFLUSH;
        else if (validStraightFlush() != null)
            return HandRankingValue.STRAIGHTFLUSH;
        else if (cardOccurrence.containsValue(4))
            return HandRankingValue.FOUROFAKIND;
        else if(validFullHouse() != -1)
            return HandRankingValue.FULLHOUSE;
        else if(validFlush() != null)
            return HandRankingValue.FLUSH;
        else if(validStraight() != null)
            return HandRankingValue.STRAIGHT;
        else if (cardOccurrence.containsValue(3))
            return HandRankingValue.THREEOFAKIND;
        else if (validTwoPair())
            return HandRankingValue.TWOPAIR;
        else if (validPair())
            return HandRankingValue.PAIR;
        else
            return HandRankingValue.HIGHCARD;
/*        if (cardOccurrence.containsValue(4)) {
            if (validStraightFlush() != null)
                return HandRankingValue.STRAIGHTFLUSH;
            else if (validRoyalFlush() != null)
                return HandRankingValue.ROYALFLUSH;
            return HandRankingValue.FOUROFAKIND;
            //At least 4 of a kind is possible so check if above two rankings are possible
        } else if (cardOccurrence.containsValue(3)) {
            if (validFullHouse() != -1)
                return HandRankingValue.FULLHOUSE;
            else if (validFlush() != null)
                return HandRankingValue.FLUSH;
            else if (validStraight() != null)
                return HandRankingValue.STRAIGHT;
            return HandRankingValue.THREEOFAKIND;
        } else {
            if (validTwoPair())
                return HandRankingValue.TWOPAIR;
            else if (validPair())
                return HandRankingValue.PAIR;
            else
                return HandRankingValue.HIGHCARD;
        }*/
    }

    private Card validStraightFlush() {
        Card highestConsecutive = null;
        for (int i = 0; i < this.cards.size(); i++) {
            //Check 5 cards from i to i + 5
            boolean isConsecutive = true;
            for (int j = i; j < i + 4 && isConsecutive && j < this.cards.size() - 1; j++) {
                Card c1 = this.cards.get(j);
                Card c2 = this.cards.get(j + 1);
                isConsecutive = c1.sameSuit(c2) && c1.isConsecutive(c2);
            }
            if (isConsecutive)
                highestConsecutive = this.cards.get(i);
        }
        return highestConsecutive;
    }

    private Card validRoyalFlush() {
        Card startingCard = null;
        for (int i = 0; i < this.cards.size(); i++) {
            boolean isConsecutive = true;
            Card c1 = this.cards.get(i);
            if (c1.getValue() == 10) {
                for (int j = i; j < i + 4 && isConsecutive && j < this.cards.size() - 1; j++) {
                    c1 = this.cards.get(j);
                    Card c2 = this.cards.get(j + 1);
                    isConsecutive = c1.sameSuit(c2) && c1.isConsecutive(c2);
                }
            }
            if (isConsecutive)
                startingCard = c1;
        }
        logger.info(startingCard.toString());
        return startingCard;
    }

    private int validFullHouse() {
        //List of cards where count in cardOccurrence == 2
        ArrayList<Integer> twosList = new ArrayList<>();
        for (Integer key : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(key);
            if (value == 2)
                twosList.add(key);
        }
        if (twosList.isEmpty())
            //No cards with occurrence == 2, so FullHouse not possible
            return -1;
        Collections.sort(twosList);

        //Return highest value in twosList to return highest pair
        return twosList.get(twosList.size() - 1);
    }

    private Card validFlush() {
        Set<Integer> cardSet = this.cardOccurrence.keySet();
        Card highestFlush = null;
        for (int i = 0; i < cardSet.size() - 1; i++) {
            boolean isFlush = true;
            for (int j = i; j < i + 4 && isFlush && j < cardSet.size() - 1; j++) {
                Card c1 = this.cards.get(j);
                Card c2 = this.cards.get(j + 1);
                isFlush = c1.sameSuit(c2);
            }
            if (isFlush)
                highestFlush = this.cards.get(i);
        }
        return highestFlush;
    }

    //Code for validStraight is pretty similar to validStraightFlush except no need to check suit
    private Card validStraight() {
        logger.info("validStraight() method");
        Card highestConsecutive = null;
        for (int i = 0; i < this.cards.size(); i++) {
            //Check 5 cards from i to i + 5
            boolean isConsecutive = true;
            for (int j = i; j < i + 4 && isConsecutive && j < this.cards.size() - 1; j++) {
                Card c1 = this.cards.get(j);
                Card c2 = this.cards.get(j + 1);
                isConsecutive = c1.isConsecutive(c2);
            }
            if (isConsecutive)
                highestConsecutive = this.cards.get(i);
        }
        return highestConsecutive;
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
}
