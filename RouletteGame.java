package Hw7;
/**
 * Name of program: RouletteGame
 * @author Hung Le
 * Date: 12/8/12
 * IDE: Eclipse Java EE IDE for Web Developers.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class RouletteGame extends JFrame implements ActionListener
{
	private RouletteTable chosen = new RouletteTable ();
	private RouletteWheel result = new RouletteWheel ();
	private JButton number [] = new JButton [37];
	private JButton ODD = new JButton ("ODD");
	private JButton EVEN = new JButton ("EVEN");
	private JButton BLACK = new JButton ("BLACK");
	private JButton RED = new JButton ("RED");
	private JButton FIRST = new JButton ("FIRST 12");
	private JButton SECOND = new JButton ("SECOND 12");
	private JButton THIRD = new JButton ("THIRD 12");
	private JButton SPIN = new JButton ("Spin Wheel");
	private JTextField tf;
	private JLabel display = new JLabel();
	private boolean numClicks = false;
	
	public RouletteGame()
	{
		//Set layout
		super("Program 7 Roullette Game");
		setLayout(new BorderLayout());
		
		chosen.initializing();// initialize payoff and set bet to 0
		
		// JPanel for EVEN, BLACK, ODD, RED
		JPanel left = new JPanel (new GridLayout (4, 1));
		
		//Define EVEN button
		EVEN.addActionListener(this);
		left.add(EVEN);
		
		//Define BLACK button
		BLACK.setBackground(Color.black);
		BLACK.setForeground(Color.yellow);
		BLACK.addActionListener(this);
		left.add(BLACK);
		
		//Define RED button
		RED.setBackground(Color.red);
		RED.setForeground(Color.yellow);
		RED.addActionListener(this);
		left.add(RED);
		
		//Define ODD button
		ODD.addActionListener(this);
		left.add(ODD);
		
		// JPanel for FIRST, SECOND, THIRD
		JPanel right = new JPanel (new GridLayout (3, 1));
		
		//Define FIRST button
		FIRST.addActionListener(this);
		right.add(FIRST);
		
		//Define SECOND button
		SECOND.addActionListener(this);
		right.add(SECOND);
		
		//Define THIRD button
		THIRD.addActionListener(this);
		right.add(THIRD);
		
		// JPanel for 1-36 number buttons
		JPanel center1 = new JPanel (new GridLayout (12, 30, 0, 0));
		
		//Define 1-36 number buttons
		for (int i = 1; i < number.length; i++)
		{
			number [i] = new JButton ("" + i);
			number[i].addActionListener(this);
			
			if (i == 2 || i == 4 || i == 6 || i == 8 || i == 10 || i == 11 || i == 13 || i == 15
					 || i == 17 || i == 20 || i == 22 || i == 24 || i == 26 || i == 28 || i == 29
							 || i == 31 || i == 33 || i == 35)
				number [i].setForeground(Color.black);
			else
				number [i].setForeground(Color.red);
			
			center1.add(number[i]);
		}
		
		// JPanel for 0-36 numbers
		JPanel center2 = new JPanel (new BorderLayout());// set Layout
		
		//Define 0 button
		number[0] = new JButton ("0");
		number[0].setForeground(Color.green);
		number[0].addActionListener(this);
		center2.add(number[0], BorderLayout.NORTH);
		
		center2.add(center1, BorderLayout.CENTER); // add JPanel center1 into JPanel center2
		
		// JPanel for betting text field
		JPanel top = new JPanel (new FlowLayout());
		tf = new JTextField(10);// displays about 10 chars at a time
        tf.setEditable(true);// default
        top.add(new JLabel("Enter bet here, then click on spot to place bet bellow"));
        top.add(tf);
		
        
        // JPanel for SPIN button
		JPanel bot= new JPanel (new FlowLayout());
		
		// Define SPIN button
		SPIN.setEnabled(false);// SPIN button cannot be pressed
		SPIN.addActionListener(this);
		bot.add(SPIN);
		
		bot.add(display);// display the result after spun
		
		// Arrange and add to JFrame
		add (top, BorderLayout.NORTH);
		add (left, BorderLayout.WEST);
		add (right, BorderLayout.EAST);
		add (center2, BorderLayout.CENTER);
		add (bot, BorderLayout.SOUTH);
			
	}// end RouletteGame - constructor
	
	public static void main( String [] args )
	// instantiate the game GUI class
	{
		RouletteGame qt = new RouletteGame();
      	qt.setSize(600, 600);
      	qt.setVisible(true);
      	qt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} // end main
	
	public void getInput(String str, double bet)
	// set bet and display it on button spot
	{		
		StringTokenizer strok = new StringTokenizer (str);
		str = strok.nextToken();
		if (!isDouble(str))
		{
			if (str.equals("ODD"))
			{
				chosen.setODD(bet);
				ODD.setText("ODD" + " [" + bet + "]");
			}
			
			else if (str.equals("EVEN"))
			{
				chosen.setEVEN(bet);
				EVEN.setText("EVEN" + " [" + bet + "]");
			}
			
			else if (str.equals("RED"))
			{
				chosen.setRED(bet);
				RED.setText("RED" + " [" + bet + "]");
			}
			
			else if (str.equals("BLACK"))
			{
				chosen.setBLACK(bet);
				BLACK.setText("BLACK" + " [" + bet + "]");
			}
			
			else if (str.equals("FIRST"))
			{
				chosen.setFIRST(bet);
				FIRST.setText("FIRST 12" + " [" + bet + "]");
			}
			
			else if (str.equals("SECOND"))
			{
				chosen.setSECOND(bet);
				SECOND.setText("SECOND 12" + " [" + bet + "]");
			}
			
			else if (str.equals("THIRD"))
			{
				chosen.setTHIRD(bet);
				THIRD.setText("THIRD 12" + " [" + bet + "]");
			}
			
		}
		else 
		{
			int index = Integer.parseInt(str);
			if (0 <= index && index < 37)
			{
				chosen.setNUM (index, bet);
				number[index].setText("" + index + " [" + bet + "]");
			}		
		}// end --- if(!isInteger(str))
	}// end getInput()
	
	public String displayResults() 
	// get the result from the spin
	{
		String color = null;
		StringBuilder str = new StringBuilder();
		if (result.getColor() == Color.black)
			color = "Black";
		if (result.getColor() == Color.red)
			color = "Red";
		if (result.getColor() == Color.green)
			color = "Green";
		
		str.append("The winning number = " + result.getNum());
		str.append(" (" + color + ") ");
		str.append(" ***You won $" + totalWin() + "***");
		
		return str.toString();
	}// end displayResults
	
	public double getBet ()
	// get bet from the text filed
	{
		double i = 0;
		
		try
		{
			i = Double.parseDouble(tf.getText());//may trigger NumberFormatException
			if (i < 0)
			{
				i = 0;
				JOptionPane.showMessageDialog(null, "Must enter non-negative number!", "Input Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(NumberFormatException nfe)
        {
        	JOptionPane.showMessageDialog(null, "Must enter digits only!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
		tf.setText("");
		
		return i;
	}// end getBet
	
	public boolean isDouble(String str)
	// check if the String is all digits
	{
		int count = 0;
		
		for (int i = 0; i < str.trim().length(); i++)
		{
			char c = str.charAt(i);
			if (c < '.' || c >= ':' || c == '/')// c is NOT from '0' to '9' OR '.'
				return false;
			if (c == '.')
				count++;
		}// end loop
		
		// The numeric string is allowed to have only one '.' character
		if (count > 1) 
			return false;
		
		return true;
	}// end isDouble method
		
	public double totalWin ()
	// Calculate the total win after the wheel spun
	{
		int num = result.getNum();
		double win = 0;
		
		//Calculate Color bet
		if (result.getColor() == Color.black)
			win = chosen.winBLACK();
		if (result.getColor() == Color.red)
			win = chosen.winRED();
		
		//Calculate First/Second/Third bet
		if (0 < num && num <= 12)
			win = win + chosen.winFIRST();	
		if (12 < num && num <= 24)
			win = win + chosen.winSECOND();	
		if (24 < num && num <= 36)
			win = win + chosen.winTHIRD();
		
		//Calculate Odd/Even bet
		if (num != 0)
		{
			if ((num % 2) == 0)
				win = win + chosen.winEVEN();
			else
				win = win + chosen.winODD();
		}
		
		//Calculate Number bet
		win = win + chosen.winNUM(num);
			
		return win;// return total
	}// end totalWin
	
	public void actionPerformed (ActionEvent e)
	// Override the actionPerformed of ActionListener
	{
		double bet = 0;
		JButton source = (JButton) e.getSource();
		
		if (source == SPIN)
		{
			result.SpinTheWheel();// spin the wheel
			display.setText(displayResults());// display the results
			
			//Set back to the Starting state
			chosen.initializing();// initialize payoff and set bet to 0
			numClicks = false;
			BLACK.setText("BLACK");
			RED.setText("RED");
			EVEN.setText("EVEN");
			ODD.setText("ODD");
			FIRST.setText("FIRST 12");
			SECOND.setText("SECOND 12");
			THIRD.setText("THIRD 12");
			for (int i = 0; i < number.length; i++)
				number[i].setText("" + i);
		}
		else // if not SPIN button
		{
			bet = getBet();
			if (bet > 0)
			{	
				getInput (source.getText(), bet);// plug in the bet
				numClicks = true;
			}									
		}
		
		if (numClicks)
		{
			SPIN.setEnabled(true);// SPIN button can be pressed
			display.setText("");// erase the previous result
		}
		else
			SPIN.setEnabled(false);// SPIN button cannot be pressed
	}// end actionPerformed
}// end class




















