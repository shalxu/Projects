public class Loto {
	private Loto previousLoto;
	private Loto nextLoto;
	private int year;
	private int day;
	private String number;
	private String combination;
	public Loto(int year, int day, String number)
	{
		this.year=year;
		this.day=day;
		this.number=number;
		previousLoto=null;
		nextLoto=null;
		String result="";
		int firstBit=new Integer(number.substring(0,1));
		int secondBit=new Integer(number.substring(1,2));
		int thirdBit=new Integer(number.substring(2,3));
		if(firstBit<=secondBit&&firstBit<=thirdBit)
		{
			if(secondBit<=thirdBit)
			{
				result+=firstBit;
				result+=secondBit;
				result+=thirdBit;
			}
			else
			{
				result+=firstBit;
				result+=thirdBit;
				result+=secondBit;
			}
		}
		else if(secondBit<=firstBit&&secondBit<=thirdBit)
		{
			if(firstBit<=thirdBit)
			{
				result+=secondBit;
				result+=firstBit;
				result+=thirdBit;
			}
			else
			{
				result+=secondBit;
				result+=thirdBit;
				result+=firstBit;
			}
		}
		else
		{
			if(firstBit<=secondBit)
			{	
				result+=thirdBit;
				result+=firstBit;
				result+=secondBit;
			}
			else
			{
				result+=thirdBit;
				result+=secondBit;
				result+=firstBit;
			}
		}
		combination=result;
	}
	public Loto(String number)
	{
		this.year=-1;
		this.day=-1;
		this.number=number;
		previousLoto=null;
		nextLoto=null;
		String result="";
		int firstBit=number.charAt(0);
		int secondBit=number.charAt(1);
		int thirdBit=number.charAt(2);
		if(firstBit<=secondBit&&firstBit<=thirdBit)
		{
			if(secondBit<=thirdBit)
			{
				result+=firstBit;
				result+=secondBit;
				result+=thirdBit;
			}
			else
			{
				result+=firstBit;
				result+=thirdBit;
				result+=secondBit;
			}
		}
		else if(secondBit<=firstBit&&secondBit<=thirdBit)
		{
			if(firstBit<=thirdBit)
			{
				result+=secondBit;
				result+=firstBit;
				result+=thirdBit;
			}
			else
			{
				result+=secondBit;
				result+=thirdBit;
				result+=firstBit;
			}
		}
		else
		{
			if(firstBit<=secondBit)
			{	
				result+=thirdBit;
				result+=firstBit;
				result+=secondBit;
			}
			else
			{
				result+=thirdBit;
				result+=secondBit;
				result+=firstBit;
			}
		}
		combination=result;
	}
	public Loto getPreviousLoto() 
	{
		return previousLoto;
	}
	public Loto getNextLoto() 
	{
		return nextLoto;
	}
	public void setPreviousLoto(Loto previousLoto)
	{
		this.previousLoto=previousLoto;
	}
	public void setNextLoto(Loto nextLoto)
	{
		this.nextLoto=nextLoto;
	}
	public String getNumber()
	{
		return number;
	}
	public int getYear()
	{
		return year;
	}
	public int getDay()
	{
		return day;
	}
	public int calculateInterval(Loto otherLoto)
	{
		if(otherLoto.getYear()>this.year||(otherLoto.getYear()==this.year&&otherLoto.getDay()>this.day))
		{
			return(359*(otherLoto.getYear()-this.year)+otherLoto.getDay()-this.getDay());
		}
		else
		{
			return-(359*(otherLoto.getYear()-this.year)+otherLoto.getDay()-this.getDay());
		}
	}
	public String getCombination() {
		return combination;
	}
	public String toString()
	{
		return year+" "+day+" "+number;
	}
}
