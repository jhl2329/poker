package poker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;


public class CardHand {
    Logger logger = LoggerFactory.getLogger(CardHand.class);
	
	private boolean isConsecutive;
	private boolean isSameSuit;
	private HashMap<Integer, Integer> cardOccurence;
	private int[] cardValues;
	private HandRankingValue handRankingValue;
	
	public CardHand(String[] cards) {
		this.cardOccurence = new HashMap<>();
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
            if(this.cardOccurence.get(cardValues[i]) != null) {
                this.cardOccurence.put(cardValues[i], this.cardOccurence.get(cardValues[i]) + 1);
            }
            else {
                this.cardOccurence.put(cardValues[i], 1);
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

    public void determineHand() {
	    int keyCount = this.cardOccurence.keySet().size();
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
    If the number of keys in cardOccurence hashmap is 2, then you either have a 4 of a kind, or a full house.
    Eg. 4 Aces and 1 King or 3 Aces and 2 Kings

    2 Possible Values for Map
        4 Of a Kind: 4, 1
        Full House: 3, 2
     */
    public void handle2Key() {
        int value = cardOccurence.get(cardOccurence.keySet().toArray()[0]);
        if(value == 1 || value == 4) {
            // 4 Of a Kind
            this.handRankingValue = HandRankingValue.FOUROFAKIND;
        }
        else {
            // Full House
            this.handRankingValue = HandRankingValue.FULLHOUSE;
        }
    }

    public void handle3Key() {
        for(int key : cardOccurence.keySet()) {
            int value = cardOccurence.get(key);
            // If 3 of a kind, value is eventually going to be 3 (Full House is some variation of 1-1-3)
            if(value == 3) {
                this.handRankingValue = HandRankingValue.THREEOFAKIND;
            }
        }
        if(this.handRankingValue == null) {
            // hrv was never set above, so must be a two pair
            this.handRankingValue = HandRankingValue.TWOPAIR;
        }
    }

    /*
    If num keys == 4, only a hand with a pair and 3 singles will end up with 4 keys in hashmap
     */
    public void handle4Key() {
        this.handRankingValue = HandRankingValue.PAIR;
    }

    /*
    Royal Flush: Since cardValues[] is already sorted, take the first value check if it's 10. Then check the boolean
        flags
    Straight Flush: Same suit and consecutive, doesn't matter what the first card is
    Straight: Consecutive but not same suit
    Flush: Same suit but not consecutive
    Highcard: When all else fails, it's a high card!
     */
    public void handle5Key() {
        if(this.isConsecutive && this.isSameSuit && this.cardValues[0] == 10) {
            this.handRankingValue = HandRankingValue.ROYALFLUSH;
        }
        else if(this.isConsecutive && this.isSameSuit) {
            this.handRankingValue = HandRankingValue.STRAIGHTFLUSH;
        }
        else if(this.isConsecutive) {
            this.handRankingValue = HandRankingValue.STRAIGHT;
        }
        else if(this.isSameSuit) {
            this.handRankingValue = HandRankingValue.FLUSH;
        }
        else {
            this.handRankingValue = HandRankingValue.HIGHCARD;
        }
    }
    public boolean getIsConsecutive() {
	    return this.isConsecutive;
    }

    public boolean getIsSameSuit() {
	    return this.isSameSuit;
    }
	
	
}
