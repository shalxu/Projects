public class LotoResult implements Comparable 
{
	String number;
	int currentInterval;
	int previousInterval;
	int averageInterval;
	String comment;
	public LotoResult(String number, int current, int previous, int average)
	{
		this.number=number;
		this.currentInterval=current;
		this.previousInterval=previous;
		this.averageInterval=average;
		if(currentInterval>=3*averageInterval)
		{
			comment="¿‰∫≈";
		}
		else
		{
			comment="";
		}	
	}
	public String toString()
	{
		String result="";
		int currentLength=18-new Integer(currentInterval).toString().length();
		int previousLength=18-new Integer(previousInterval).toString().length();
		result+="   ";
		result+=number;
		result+="        ";
		result+=currentInterval;
		for(int i=0;i<currentLength;i++)
		{
			result+=" ";
		}
		result+=previousInterval;
		for(int i=0;i<previousLength;i++)
		{
			result+=" ";
		}
		result+=averageInterval;
		result+="       ";
		result+=comment;
		return result;
	}
	@Override
	public int compareTo(Object otherResult) 
	{
		return this.currentInterval-((LotoResult)otherResult).currentInterval;
	}
}
