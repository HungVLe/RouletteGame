package Hw7;
/**
 * Name of program: RouletteGame
 * @author Hung Le
 * Date: 12/8/12
 * IDE: Eclipse Java EE IDE for Web Developers.
 */

import java.awt.Color;

public class RouletteWheel 
{
	public static final int MAX = 36;
	public static final int MIN = 0;
	private int chosen;
	private Color [] wheel = new Color [37];
	
	public RouletteWheel()
	{
		for (int i = 0; i < wheel.length; i++)
		{
			if (i == 0)
				wheel [i] = Color.green;
			else
			{
				if (i == 2 || i == 4 || i == 6 || i == 8 || i == 10 || i == 11 || i == 13 || i == 15
						 || i == 17 || i == 20 || i == 22 || i == 24 || i == 26 || i == 28 || i == 29
								 || i == 31 || i == 33 || i == 35)
					wheel [i] = Color.black;
				else
					wheel [i] = Color.red;
			}
		}
		
	}// end constructor
	
	public void SpinTheWheel () { chosen = (int)(Math.random() * (MAX-MIN + 1) ) + MIN; }
	
	public int getNum () { return chosen;}
	
	public Color getColor () { return wheel [chosen];}
	
}// end class