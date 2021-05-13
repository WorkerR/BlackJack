import java.util.*;

public class CardTester {
	/*public static void main(String[] arg)
	{
		ArrayList<Card> deck = new ArrayList<Card>();
		
		
		for (int suit = 1; suit <= 4; suit++)
		{
			for (int rank = 1; rank <= 13; rank++)
			{
				deck.add(new Card(rank, suit));
			}
		}
		for (Card c : deck)
		{
			System.out.println(c);
		}
	}
	*/
	/*
	public static void main(String[] args)
	{
		Deck d = new Deck();
		d.shuffle();
		for (int i = 0; i <= 51; i++)
		{
		System.out.println(d.draw());
		}
	}
		*/
	public static void main(String[] args)
	{
	Hand h = new Hand(false);
	Deck d = new Deck();
	d.shuffle();
	h.addCard(d.draw());
	h.addCard(d.draw()); 
	h.addCard(d.draw());
	System.out.println(h);
	}
}
