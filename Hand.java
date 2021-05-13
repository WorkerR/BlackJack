import java.util.*;

public class Hand {
	// Object Variable
	private int aceNum = 0;
	private int totalValue = 0;
	private boolean isDealer;
	private boolean isBusted = false;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	// Constructor
	public Hand(boolean isD)
	{
		isDealer = isD;
	}
	
	// Object Methods

	
	public int getTotalValue() 
	{
		return totalValue;
	}
	
		
	public ArrayList<Card> getHand() {
		return hand;
	}

	public boolean getisBusted() {
		return isBusted;
	}

	public void addCard(Card newCard)
	{
		hand.add(newCard);
		totalValue += newCard.getValue();
		if (newCard.getRank() == 1) aceNum++;
		if (totalValue > 21 && aceNum == 0) isBusted = true;
		else if (totalValue > 21 && aceNum >0)
		{
			totalValue -= 10;
			aceNum -= 1;
		}
	}

	public String toString()
	{
		String tempCard = "";
		
		for (int i = 1; i < hand.size(); i++)
		{
			tempCard += "\t" + hand.get(i) + "\n";
		}
		if (isDealer)
			{
			return "Dealer's Hand:\n\tCard: ?????\n" + tempCard;
			}
		else 
		{
			return "Player's Hand:\n\t" + hand.get(0) + "\n" + tempCard + "\tCurrent Total: " + totalValue + "\n";
		}
	}

}
