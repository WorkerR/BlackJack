
public class Card {
	// Object Variables
	public int rank;
	public int suit;
	public int value;
	
	// Constructor
	public Card(int r, int s)
	{
		rank = r;
		suit = s;
		if (r > 10) value = 10;
		else if (r == 1) value = 11;
		else value = rank;
	}

	// Object Methods
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString()
	{
		String r;
		String s;
				
		if (rank == 1) r = "Ace";
		else if (rank == 11) r = "Jack";
		else if (rank == 12) r = "Queen";
		else if (rank == 13) r = "King";
		else r = rank + "";
		
		if (suit == 1) s = "Spades";
		else if (suit == 2) s = "Hearts";
		else if (suit == 3) s = "Clubs";
		else s = "Diamonds";
		
		return "Card: " + r + " of " + s;
	}
	
}
