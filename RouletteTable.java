package Hw7;
/**
 * Name of program: RouletteGame
 * @author Hung Le
 * Date: 12/8/12
 * IDE: Eclipse Java EE IDE for Web Developers.
 */

public class RouletteTable 
{
	public static final int MAX = 37;
	
	// Private instance variables:
	private RouletteSpot ODD;
	private RouletteSpot EVEN;
	private RouletteSpot RED;
	private RouletteSpot BLACK;
	private RouletteSpot FIRST;
	private RouletteSpot SECOND;
	private RouletteSpot THIRD;
	private RouletteSpot [] number = new RouletteSpot [MAX];
	
	//Methods:
	public RouletteTable () {}// default constructor
	
	public void initializing ()
	{
		ODD = new RouletteSpot (1);
		EVEN = new RouletteSpot (1);
		RED = new RouletteSpot (1);
		BLACK = new RouletteSpot (1);
		FIRST = new RouletteSpot (2);
		SECOND = new RouletteSpot (2);
		THIRD = new RouletteSpot (2);
		
		for (int i = 0; i < number.length; i++)
		{
			number[i] = new RouletteSpot (35);
		}
	}// end initializing
	
	//Assigning a bet to each of the above RouletteSpots (calling its mutator).
	public void setODD (double i) { ODD.setBet(i);}
	
	public void setEVEN (double i) { EVEN.setBet(i);}
	
	public void setRED (double i) { RED.setBet(i);}
	
	public void setBLACK (double i) { BLACK.setBet(i);}
	
	public void setFIRST (double i) { FIRST.setBet(i);}
	
	public void setSECOND (double i) { SECOND.setBet(i);}
	
	public void setTHIRD (double i) { THIRD.setBet(i);}
	
	public void setNUM (int index, double i) { number[index].setBet(i);}
	
	//Returning the winnings for each spot.
	public double winODD () { return ODD.winning(); }
	
	public double winEVEN () { return EVEN.winning(); }
	
	public double winRED () { return RED.winning(); }
	
	public double winBLACK () { return BLACK.winning(); }
	
	public double winFIRST () { return FIRST.winning(); }
	
	public double winSECOND () { return SECOND.winning(); }
	
	public double winTHIRD () { return THIRD.winning(); }
	
	public double winNUM (int index) { return number[index].winning(); }
	
}// end class