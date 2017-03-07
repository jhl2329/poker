package poker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poker.hands.*;

import java.util.Arrays;
import java.util.HashMap;


public class CardHand {
    Logger logger = LoggerFactory.getLogger(CardHand.class);
	
	private boolean isConsecutive;
	private boolean isSameSuit;

	private HashMap<Integer, Integer> cardOccurrence;
	private int[] cardValues;
	private HandRankingValue handRankingValue;
	private BaseHand hand;
	
	public CardHand(String[] cards) {
		this.cardOccurrence = new HashMap<>();
		isSameSuit = true;
		isConsecutive = true;


		//Determine if the cards are same suit or not
		for(int i = 0; i < cards.length - 1 && isSameSuit; i++) {
			//Card Layout: "10H"
            char firstSuit = cards[i].charAt(cards[i].length() - 1);
            char secondSuit = cards[i+1].charAt(cards[i+1].length() - 1);
			if(firstSuit != secondSuit) {
				isSameSuit = false;
			}
		}
		//Determine if cards are consecutive
		this.cardValues = new int[cards.length];
        for(int i = 0; i < cards.length; i++) {
			String cardNumber = cards[i].substring(0, cards[i].length() - 1); //"10H" -> 10H
			if(cardNumber.equals("J"))
			    cardValues[i] = 11;
			else if(cardNumber.equals("Q"))
			    cardValues[i] = 12;
			else if(cardNumber.equals("K"))
			    cardValues[i] = 13;
			else if(cardNumber.equals("A"))
			    cardValues[i] = 14;
			else
			    cardValues[i] = Integer.parseInt(cardNumber);

			//Put card into HashMap for later methods to determine hand combo info
            if(this.cardOccurrence.get(cardValues[i]) != null) {
                this.cardOccurrence.put(cardValues[i], this.cardOccurrence.get(cardValues[i]) + 1);
            }
            else {
                this.cardOccurrence.put(cardValues[i], 1);
            }
		}

		//Sort cardValue array from low to high
		Arrays.sort(cardValues);

		//Go through array and check if values are consecutive
		for(int i = 0; i < cardValues.length - 1 && isConsecutive; i++) {
			if (cardValues[i] != cardValues[i + 1] - 1) {
				isConsecutive = false;
			}
		}

		determineHand();
        logger.info(""+this.handRankingValue);
	}

    private void determineHand() {
	    int keyCount = this.cardOccurrence.keySet().size();
	    switch (keyCount) {
            case 2: //4 of a kind or full house
                handle2Key();
                break;
            case 3:
                handle3Key();
                break;
            case 4:
                handle4Key();
                break;
            case 5:
                handle5Key();
                break;
            default:
                //Should always be one of the 4 cases above, b/c can't have 1 key in valid poker
                break;
        }
    }

    /*
    If the number of keys in cardOccurrence hashmap is 2, then you either have a 4 of a kind, or a full house.
    Eg. 4 Aces and 1 King or 3 Aces and 2 Kings

    2 Possible Values for Map
        4 Of a Kind: 4, 1
        Full House: 3, 2
     */
    private void handle2Key() {
        int value = cardOccurrence.get(cardOccurrence.keySet().toArray()[0]);
        if(value == 1 || value == 4) {
            // 4 Of a Kind
            this.handRankingValue = HandRankingValue.FOUROFAKIND;
            this.hand = new Quads();
        }
        else {
            // Full House
            this.handRankingValue = HandRankingValue.FULLHOUSE;
            this.hand = new FullHouse();
        }
    }

    private void handle3Key() {
        for(int key : cardOccurrence.keySet()) {
            int value = cardOccurrence.get(key);
            // If 3 of a kind, value is eventually going to be 3 (Full House is some variation of 1-1-3)
            if(value == 3) {
                this.handRankingValue = HandRankingValue.THREEOFAKIND;
                this.hand = new Trips();
            }
        }
        if(this.handRankingValue == null) {
            // hrv was never set above, so must be a two pair
            this.handRankingValue = HandRankingValue.TWOPAIR;
            this.hand = new TwoPair();
        }
    }

    /*
    If num keys == 4, only a hand with a pair and 3 singles will end up with 4 keys in hashmap
     */
    private void handle4Key() {
        this.handRankingValue = HandRankingValue.PAIR;
        this.hand = new Pair();
    }

    /*
    Royal Flush: Since cardValues[] is already sorted, take the first value check if it's 10. Then check the boolean
        flags
    Straight Flush: Same suit and consecutive, doesn't matter what the first card is
    Straight: Consecutive but not same suit
    Flush: Same suit but not consecutive
    Highcard: When all else fails, it's a high card!
     */
    private void handle5Key() {
        if(this.isConsecutive && this.isSameSuit && this.cardValues[0] == 10) {
            this.handRankingValue = HandRankingValue.ROYALFLUSH;
            this.hand = new RoyalFlush();
        }
        else if(this.isConsecutive && this.isSameSuit) {
            this.handRankingValue = HandRankingValue.STRAIGHTFLUSH;
            this.hand = new StraightFlush();
        }
        else if(this.isConsecutive) {
            this.handRankingValue = HandRankingValue.STRAIGHT;
            this.hand = new Straight();
        }
        else if(this.isSameSuit) {
            this.handRankingValue = HandRankingValue.FLUSH;
            this.hand = new Flush();
        }
        else {
            this.handRankingValue = HandRankingValue.HIGHCARD;
            this.hand = new HighCard();
        }
    }

    public CardHand compare(CardHand secondHand) {
        if(Arrays.equals(this.cardValues, secondHand.cardValues))
            return null;
        else
            return this.handRankingValue.getValue() > secondHand.handRankingValue.getValue() ? this : secondHand;
    }

    /*
    Given CardHand secondHand, check if secondHand is a higher rank than the hand it's being compared with.
    e.g.: If secondHand == 4 of a kind and calling CardHand == 3 of a kind, then return value would be true since 4 of a
    kind beats 3 of a kind
     */

    /*
    If the rank of two cards are the same, usually involves something simple such as checking the highs
     */
    public boolean sameRankComparison(CardHand secondHand) {
        //Trivial case, see if two CardHands are actually the same
        if(Arrays.equals(this.cardValues, secondHand.cardValues)) {
            logger.info("Same hand");
            return false;
        }
        return false;
    }

    public HandRankingValue getHandRankingValue() {
        return this.handRankingValue;
    }



    public boolean getIsConsecutive() {
	    return this.isConsecutive;
    }

    public boolean getIsSameSuit() {
	    return this.isSameSuit;
    }


    public boolean sameRank(CardHand toCompare) {
        return this.handRankingValue == toCompare.handRankingValue;
    }
}
