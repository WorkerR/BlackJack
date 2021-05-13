import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class BlackJack extends JPanel  implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// amounthhh is the amount of money player has
	// bethhh is the bet the player decides to play
	private static int amounthhh = 1000;
	private static int bethhh = 0;
	
	// deck it a deck of card
	// playerHand is what player has and dealerHand is what dealer has
	private static Deck deck;
	private static Hand playerHand = null;
	private static Hand dealerHand = null;

	// myFrame is the main frame
	// image stores 53 cards 
	// pic stores images
	// mC is the mystery card dealer has
	// amount and bet is same as the amounthhh and bethhh 
	private static JFrame myFrame = new JFrame("BlackJack");
	private static ImageIcon[] image;
	private static JLabel pic = new JLabel();
	private static JLabel mC = new JLabel();	
	private static JLabel amount = new JLabel();
	private static JLabel bet = new JLabel();
	
	// pV is player's value and dV is dealer's value
	private static JLabel pV = new JLabel();
	private static JLabel dV = new JLabel();	

	// "this" is the main panel which uses borderLayout 
	// oP uses gridLayout that inserts into the center of "this" and splits center into four parts
	// bS is the bet status which goes to the west of "this"
	// pS and dS are the player status and the dealer status
	// pH and dH are player's hand and dealer's hand
	private static JPanel oP = new JPanel();
	private static JPanel bS = new JPanel();
	private static JPanel pS = new JPanel();
	private static JPanel dS = new JPanel();
	private static JPanel pH = new JPanel();
	private static JPanel dH = new JPanel();
	
	// hit and stand is the button for player to play
	private static JButton hit;
	private static JButton stand;
	
	// gO represents game over?
	private static boolean gO = false;

	// this is the action when the game ends
	public void endGame()
	{
		String win = "";
		if (gO && (playerHand.getisBusted() || (!dealerHand.getisBusted() && dealerHand.getTotalValue() > playerHand.getTotalValue())))
		{
			win = "l";
		}
		else if (gO && (dealerHand.getisBusted() || (!playerHand.getisBusted() && dealerHand.getTotalValue() < playerHand.getTotalValue())))
		{
			win = "w";
			amounthhh += (bethhh * 2);
		}
		else 
		{
			win = "t";
			amounthhh += bethhh;
		}
		gO = false;
		JFrame trdF = new JFrame("Get Bet");
		JPanel trdP = new JPanel();
		JPanel trdP2 = new JPanel();
		JButton trdB = new JButton("Restart");
		JLabel trdL = new JLabel("");
		if (win.equals("l"))
		{
			trdL.setText("You lose!");
		}
		else if (win.equals("w"))
		{
			trdL.setText("You win!");
		}
		else
		{
			trdL.setText("Tie!");
		}
		
		trdF.setSize(350,150);
		trdF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		trdP.setLayout(new BorderLayout());
		trdP2.setLayout(new GridBagLayout());
		trdL.setAlignmentX(CENTER_ALIGNMENT);
		trdP2.add(trdL);
		trdP.add(trdB, BorderLayout.SOUTH);
		trdP.add(trdP2, BorderLayout.CENTER);
		trdF.add(trdP);
		trdF.setVisible(true);
		trdP.repaint();
		trdP.setBackground(Color.white);
		trdB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(amounthhh > 0) 
				{
					trdF.setVisible(false);
					trdF.dispose();
					myFrame.setVisible(false);
					myFrame.dispose();
					getBet();
				}
				else System.exit(0);
			}
		});
	}
	
	// this is the action when a new game starts
	public static void getBet()
	{
		JFrame secF = new JFrame("Get Bet");
		JPanel secP = new JPanel();
		JLabel secL = new JLabel("Thank you for playing Black Jack!");
		JLabel secL2 = new JLabel("You have $" + amounthhh + " to play! How much would you like to Play!");
		JLabel secL3 = new JLabel();
		JTextField secT = new JTextField(15);
		JButton secB = new JButton("Confirm");
		
		secF.setSize(350,150);
		secF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		secP.add(secL);
		secP.add(secL2);
		secP.add(secT);
		secP.add(secB);
		secP.add(secL3);
		secF.add(secP);
		secF.setVisible(true);
		secB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try
				{
					if(Integer.parseInt(secT.getText()) <= amounthhh && Integer.parseInt(secT.getText()) >= 1)
					{
						bethhh = Integer.parseInt(secT.getText());
						amounthhh -= bethhh; 
						amount.setText("Balance: $" + amounthhh);
						bet.setText("Bet: $" + bethhh);
						pH.removeAll();
						dH.removeAll();
						pS.removeAll();
						dS.removeAll();
						oP.removeAll();
						bS.removeAll();
						
						secF.setVisible(false);
						secF.dispose();
						myFrame.setVisible(false);
						myFrame.dispose();
						
						new BlackJack();
					}
					else if(Integer.parseInt(secT.getText()) > amounthhh)
					{
						secT.setText("");
						secL3.setText("Out of your balance!");
					}
					else
					{
						secT.setText("");
						secL3.setText("It is an Invalid input!");
					}
				}
				catch(NumberFormatException f)
				{		
					secT.setText("");
					secL3.setText("Invalid input");
				}
			}
		});
	}

	// this is the main function
	public BlackJack()
	{
		myFrame = new JFrame("BlackJack");
		myFrame.setSize(1024,600);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(new Color(71, 113, 72));
		this.setLayout(new BorderLayout());
		oP.setLayout(new GridLayout(2, 2, 10, 10));
		oP.setBackground(new Color(71, 113, 72));
		bS.setBackground(new Color(54, 90, 55));
		pH.setBackground(new Color(71, 113, 72));
		dH.setBackground(new Color(71, 113, 72));	
		
		pH.setLayout(new GridBagLayout());
		dH.setLayout(new GridBagLayout());
		pS.setLayout(new GridBagLayout());

		pS.setBackground(new Color(71, 113, 72));
		dS.setBackground(new Color(71, 113, 72));
		
		image = new ImageIcon [53];
		
		for (int imageNo = 1; imageNo <= 13; imageNo++)
		{
			String imageFileName1;
			String imageFileName2;
			String imageFileName3;
			String imageFileName4;
			
			if (imageNo < 10) 
			{
				imageFileName1 = "0" + imageNo + "c.gif";
				imageFileName2 = "0" + imageNo + "d.gif";
				imageFileName3 = "0" + imageNo + "s.gif";
				imageFileName4 = "0" + imageNo + "h.gif";
			}
			else
			{
				imageFileName1 = imageNo + "c.gif";
				imageFileName2 = imageNo + "d.gif";
				imageFileName3 = imageNo + "s.gif";
				imageFileName4 = imageNo + "h.gif";
			}
			image [imageNo * 4 - 4] = new ImageIcon (imageFileName1);
			image [imageNo * 4 - 3] = new ImageIcon (imageFileName2);
			image [imageNo * 4 - 2] = new ImageIcon (imageFileName3);
			image [imageNo * 4 - 1] = new ImageIcon (imageFileName4);
		}
		image [52] = new ImageIcon ("01.gif");
		mC = new JLabel(image[52]);
		amount.setText("Balance: $" + amounthhh );
		bet.setText("Bet: $" + bethhh );
		amount.setAlignmentX (JLabel.CENTER_ALIGNMENT);
		bet.setAlignmentX (JLabel.CENTER_ALIGNMENT);
		hit = new JButton("Hit");
		stand = new JButton("Stand");
		pS.add(hit);
		pS.add(stand);
		pS.add(pV);
		oP.add(dS);
		oP.add(dH);
		oP.add(pH);
		oP.add(pS);
		bS.add(amount);
		bS.add(bet);
		this.add(bS, BorderLayout.WEST);
		this.add(oP, BorderLayout.CENTER);
		myFrame.add(this);
		myFrame.setVisible(true);
		
		playerHand = new Hand(false);
		dealerHand = new Hand(true);
		deck = new Deck();
		deck.shuffle();
		if(playerHand.getHand().size() < 1 && dealerHand.getHand().size() < 1)
		{
			for (int i = 0; i < 2 ; i++)
			{
				draw(false);		
				draw(true);			
			}
		}
		System.out.println(playerHand);
		pV.setText("You have a Value of : " + playerHand.getTotalValue());
		playerTurn();
	}

	// this is the draw method use to draw cards
	// OverWriting
	public static void draw(boolean isDealer)
	{
		Card temp = deck.draw();
		draw(isDealer, temp);
		if (isDealer)
		{
			dealerHand.addCard(temp);
		}
		else
		{
			playerHand.addCard(temp);
		}
	}

	// this is the overloaded method
	private static void draw(boolean isDealer, Card card)
	{
		if(card.getSuit() == 1) 
		{
			pic = new JLabel(image[card.getRank() * 4 - 1]);
		}
		else if(card.getSuit() == 2)
		{
			pic = new JLabel(image[card.getRank() * 4 - 2]);
		}			
		else if(card.getSuit() == 3)
		{
			pic = new JLabel(image[card.getRank() * 4 - 3]);
		}
		else if(card.getSuit() == 4)
		{
			pic = new JLabel(image[card.getRank() * 4 - 4]);
		}
		if(isDealer && dealerHand.getHand().size() < 1)
		{
			dH.add(mC);
		}
		else if(isDealer)
		{
			dH.add(pic);
		}
		else
		{
			pH.add(pic);
		}
	}
	
	// this is the player's action
	public void playerTurn()
	{
		hit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (!playerHand.getisBusted())
				{
					draw(false);		
					pH.revalidate();
					pH.repaint();
					if (playerHand.getisBusted())
					{
						gO = true;
						endGame();
					}
				}
				pV.setText("Value: " + playerHand.getTotalValue());
			}
		});
		stand.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dealerTurn(0);
			}
		});
	}
	
	// this is the dealer's action
	public void dealerTurn(int i) 
	{
		if (dealerHand.getTotalValue() > 17) 
		{
			dV.setText("The dealer has a Value of: " + dealerHand.getTotalValue());
			dS.add(dV);
			mC.setIcon(image[dealerHand.getHand().get(0).getRank() * 4 - dealerHand.getHand().get(0).getSuit()]);
			dH.revalidate();
			dH.repaint();
			dS.revalidate();
			dS.repaint();
			if (dealerHand.getTotalValue() > 17 && i == 0)
			{
				gO = true;
				endGame();
			}
			return;
		}
		else
		{
			draw(true);		
			JLabel hitt = new JLabel("The dealer decides to hit!");
			dS.add(hitt);
			dH.revalidate();
			dH.repaint();
			dS.revalidate();
			dS.repaint();
			dealerTurn(i+1);
			if (dealerHand.getTotalValue() > 17 && i == 0)
			{
				gO = true;
				endGame();
			}
			return;
		}
	}
	
	// this is main
	public static void main(String[] args) throws IOException
	{
		getBet();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
