package poker;

import java.util.Arrays;

public class CardHand {
	
	private boolean isConsecutive;
	private boolean isSameSuit;
	private String[] cards;
	
	public CardHand(String[] cards) {
		this.cards = Arrays.copyOf(cards, cards.length);
		isSameSuit = true;
		isConsecutive = true;
		
		//Determine if the cards are same suit or not
		for(int i = 0; i < cards.length - 1 && isSameSuit; i++) {
			if(cards[i].charAt(1) != cards[i+1].charAt(1)) {
				isSameSuit = false;
			}
		}
		
		//Determine if cards are consecutive
		int[] cardValues = new int[cards.length];
		for(int i = 0; i < cards.length; i++) {
			char cardNumber = cards[i].charAt(0);
			switch (cardNumber) {
				case 'J' :
					cardValues[i] = 11;
					break;
				case 'Q' :
					cardValues[i] = 12;
					break;
				case 'K':
					cardValues[i] = 13;
					break;
				case 'A':
					cardValues[i] = 14;
					break;
				default:
					cardValues[i] = Character.getNumericValue(cardNumber);
			}
		}
		//Sort cardValue array from low to high
		Arrays.sort(cardValues);
		for(int i = 0; i < cardValues.length - 1 && isConsecutive; i++) {
			if (cardValues[0] != cardValues[1] - 1) {
				isConsecutive = false;
			}
		}
	}
	
	
}
