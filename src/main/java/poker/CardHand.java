package poker;

import java.util.Arrays;

public class CardHand {
	
	private boolean isConsecutive;
	private boolean isSameSuit;
	private String[] cards;
	
	public CardHand(String[] cards) {
		this.cards = cards.clone();
		isSameSuit = true;
		isConsecutive = true;
		
		//Determine if the cards are same suit or not
		for(int i = 0; i < cards.length - 1 && isSameSuit; i++) {
			if(cards[i].charAt(cards[i].length() - 2) != cards[i+1].charAt(cards[i+1].length() - 2)) {
				isSameSuit = false;
			}
		}
		
		//Determine if cards are consecutive
		int[] cardValues = new int[cards.length];
//		System.out.println(Arrays.toString(cards));
		for(int i = 0; i < cards.length; i++) {
			String cardNumber = cards[i].substring(1, cards[i].length() - 2);
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

		}
		//Sort cardValue array from low to high
		Arrays.sort(cardValues);
		System.out.println(Arrays.toString(cardValues));
		for(int i = 0; i < cardValues.length - 1 && isConsecutive; i++) {
			if (cardValues[i] != cardValues[i + 1] - 1) {
				isConsecutive = false;
			}
		}
	}

	public String toString() {
	    return Arrays.toString(cards);
    }

    public boolean getIsConsecutive() {
	    return this.isConsecutive;
    }

    public boolean getIsSameSuit() {
	    return this.isSameSuit;
    }
	
	
}
