package poker;

import java.util.*;

/**
 * Created by Jae Lim on 3/7/2017.
 */
public class Determinator {

    private ArrayList<Card> cards;
    private HashMap<Integer, Integer> cardOccurrence;
    private CardHand cardHand;

    public Determinator(ArrayList<Card> cards, HashMap<Integer, Integer> cardOccurrence) {
        this.cards = cards;
        this.cardOccurrence = cardOccurrence;
        determine();
    }

    public HandRankingValue determine() {
        HandRankingValue hrv;
        if (cardOccurrence.containsValue(4)) {
            hrv = checkAboveQuads();
            if (hrv == null) {
                makeQuads();
                return HandRankingValue.FOUROFAKIND;
            }
            return hrv;
        }
        else if (cardOccurrence.containsValue(3)) {
            hrv = checkAboveTrips();
            if (hrv == null) {
                makeTrips();
                return HandRankingValue.THREEOFAKIND;
            }
            return hrv;
        }
        else if (cardOccurrence.containsValue(2))
            return checkAbovePair();
        else
            return checkAboveHigh();
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
        else {
            if(validTwoPair())
                return HandRankingValue.TWOPAIR;
            makePair();
            return HandRankingValue.PAIR;
        }
    }

    private HandRankingValue checkAboveHigh() {
        HandRankingValue aboveTrips = checkAboveTrips();
        if(aboveTrips != null)
            return aboveTrips;
        makeHigh();
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

    private void makeQuads() {
        int highestFour, highestKicker;
        highestFour = highestKicker = -1;
        for (Integer key : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(key);
            if(value == 4 && key > highestFour) {
                if(highestFour > highestKicker)
                    highestKicker = highestFour;
                highestFour = key;
            }
            else
                highestKicker = key;
        }
//        System.out.printf("four: %d kicker: %d", highestFour, highestKicker);

        //Go through Cards ArrayList to get the actual cards pertaining
        ArrayList<String> validHand = new ArrayList<>();
        Collections.sort(this.cards, Card.COMPARE_BY_VALUE);

        //Get the 4s
        for(Card c : this.cards) {
            if(c.sameValue(highestFour))
                validHand.add(c.toString());
        }

        //Get the kicker
        for(Card c : this.cards) {
            if(c.sameValue(highestKicker) && validHand.size() < 5)
                validHand.add(c.toString());
        }
        this.cardHand = new CardHand(validHand.toArray(new String[0]));
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
        if (highestTwo == -1 || highestThree == -1) {
            return false;
        }
            //FullHouse only possible with 2s and 3s

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

    private void makeTrips() {
        //Let secondKicker be the lower value
        int highestThree, firstKicker, secondKicker;
        highestThree = firstKicker = secondKicker  = -1;
        for (Integer key : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(key);
            if(value == 3 && value > highestThree)
                highestThree = key;
            else {
                if(key > firstKicker) {
                     secondKicker = firstKicker;
                     firstKicker = key;
                }
                else if(key > secondKicker)
                    secondKicker = key;
            }
        }

        //Go through Cards ArrayList to get the actual cards pertaining
        ArrayList<String> validHand = new ArrayList<>();
        for(int i = 0; i < this.cards.size(); i++) {
            Card c = this.cards.get(i);
            if(c.sameValue(highestThree))
                validHand.add(c.toString());
            else if(c.sameValue(firstKicker) || c.sameValue(secondKicker))
                validHand.add(c.toString());
        }
        this.cardHand = new CardHand(validHand.toArray(new String[0]));
    }

    private boolean validTwoPair() {
        int highTwo, lowTwo, kicker;
        highTwo = lowTwo = kicker = -1;

        for (Integer key : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(key);
            if (value == 2) {
                if (key > highTwo) {
                    if(lowTwo > kicker)
                        kicker = lowTwo;
                    lowTwo = highTwo;
                    highTwo = key;
                }
                else if (key > lowTwo) {
                    if (lowTwo > kicker)
                        kicker = lowTwo;
                    lowTwo = key;
                }
                else {
                    if(key > kicker)
                        kicker = key;
                }
            }
            else {
                if (key > kicker)
                    kicker = key;
            }
        }
        if(highTwo == -1 || lowTwo == -1 || kicker == -1)
            return false;
        ArrayList<String> validHands = new ArrayList<>();

        //Add the pairs
        for(Card c : this.cards) {
            if(c.sameValue(highTwo) || c.sameValue(lowTwo))
                validHands.add(c.toString());
        }

        //Add the kicker
        for(Card c : this.cards) {
            if(c.sameValue(kicker) && validHands.size() < 5)
                validHands.add(c.toString());
        }
        this.cardHand = new CardHand(validHands.toArray(new String[0]));
        return true;
    }

    private boolean makePair() {
        //No need to check for additional pairs because if two or more pairs exist, it'd be considered a TwoPair
        int pairs = -1;
        for (Integer key : this.cardOccurrence.keySet()) {
            int value = this.cardOccurrence.get(key);
            if (value == 2)
                pairs = key;
        }
        //Means no keys w/ occurrence 2
        if (pairs == -1)
            return false;
        ArrayList<String> validHands = new ArrayList<>();
        Collections.sort(this.cards, Card.COMPARE_BY_VALUE);
        int i = this.cards.size() - 1;

        //First find the pair and add them to the validHands
        while(i >= 0) {
            Card c = this.cards.get(i);
            if(c.sameValue(pairs))
                validHands.add(c.toString());
            i--;
        }

        //Then add kickers in order of magnitude
        i = this.cards.size() - 1;
        while(i >= 0 && validHands.size() < 5) {
            Card c = this.cards.get(i);
            if(!c.sameValue(pairs))
                validHands.add(c.toString());
            i--;
        }
        this.cardHand = new CardHand(validHands.toArray(new String[0]));
        return true;
    }

    //Since no other combinations, just sort and get the last 5
    public void makeHigh() {
        Collections.sort(this.cards, Card.COMPARE_BY_VALUE);
        ArrayList<String> validHand = new ArrayList<>();
        for(int i = this.cards.size() - 5; i < this.cards.size(); i++)
            validHand.add(this.cards.get(i).toString());
        this.cardHand = new CardHand(validHand.toArray(new String[0]));
    }

    @Override
    public String toString() {
        return this.cardHand.toString();
    }
}
