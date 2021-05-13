import java.util.*;

public class Deck{
	// Object Variables
	private ArrayList<Card> deck = new ArrayList<Card>();
	Random rand = new Random();

	// Constructor
	public Deck()
	{
		for (int rank = 1; rank <= 13; rank++)
		{
			for (int suit = 1; suit <= 4; suit++)
			{
				deck.add(new Card(rank, suit));
			}
		}
	}

	// Object Methods
	public ArrayList<Card> getDeck() 
	{
		return deck;
	}

	public Card draw()
	{
		Card a = deck.get(deck.size()-1);
		deck.remove(deck.size()-1);
		return a;
	}
	
	public void shuffle()
	{
		for (int i = 0; i < deck.size(); i++)
		{
			int n = rand.nextInt(52);
			Card a = deck.get(i);
			Card b = deck.get(n);
			
			deck.set(i, b);
			deck.set(n, a);
		}
	}
	
	
}
