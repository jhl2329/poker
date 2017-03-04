package poker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;


public class CardHand {
    Logger logger = LoggerFactory.getLogger(CardHand.class);
	
	private boolean isConsecutive;
	private boolean isSameSuit;
	private HashMap<Integer, Integer> cardCombo;
	
	public CardHand(String[] cards) {
		this.cardCombo = new HashMap<Integer, Integer>();
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
		int[] cardValues = new int[cards.length];
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
            if(this.cardCombo.get(cardValues[i]) != null) {
                this.cardCombo.put(cardValues[i], this.cardCombo.get(cardValues[i]) + 1);
            }
            else {
                this.cardCombo.put(cardValues[i], 1);
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


	}

    public boolean getIsConsecutive() {
	    return this.isConsecutive;
    }

    public boolean getIsSameSuit() {
	    return this.isSameSuit;
    }
	
	
}
