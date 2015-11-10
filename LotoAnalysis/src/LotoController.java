import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * LotoController Class
 * @author Shal Xu
 * August 2014
 *
 */
public class LotoController 
{
	protected Loto currentLoto;
	public LotoController()
	{
		currentLoto=null;
	}
	public void initialize()
	{
		Loto tempLoto=null;
		Loto tempPreviousLoto=null;
		Scanner scanner=null;
		try {
			scanner= new Scanner(new File("src/data.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scanner.hasNext())
		{
			String temp=scanner.nextLine().substring(0,11);
			int year=new Integer(temp.substring(0,4));
			int day= new Integer(temp.substring(4,7));
			String number=temp.substring(8,11);
			tempLoto=new Loto(year,day,number);
			tempLoto.setPreviousLoto(tempPreviousLoto);
			tempPreviousLoto=tempLoto;
			tempLoto=tempLoto.getNextLoto();
		}
		currentLoto=tempPreviousLoto;
		scanner.close();
	}
	
	/**
	 * Calculate LotoResult, Intervals
	 */
	public int currentInterval(String number)
	{
		Loto tempLoto=currentLoto;
		while(tempLoto!=null&&!tempLoto.getNumber().equalsIgnoreCase(number))
		{
			tempLoto=tempLoto.getPreviousLoto();
		}
		if(tempLoto!=null)
		return currentLoto.calculateInterval(tempLoto);
		else
		return 9999;
	}
	public int currentCombInterval(String comb)
	{
		Loto tempLoto=currentLoto;
		while(tempLoto!=null&&!tempLoto.getCombination().equalsIgnoreCase(comb))
		{
			tempLoto=tempLoto.getPreviousLoto();
		}
		if(tempLoto!=null)
		return currentLoto.calculateInterval(tempLoto);
		else
		return 9999;
	}
	public int previousInterval(String number)
	{
		int equalCount=0;
		Loto firstLoto=null;
		Loto secondLoto=currentLoto;
		while(secondLoto!=null&&equalCount!=2)
		{	
			if(secondLoto.getNumber().equalsIgnoreCase(number))
			{
				equalCount++;
				if(equalCount==1)
				{
					firstLoto=secondLoto;
				}
				else if(equalCount==2)
				{
					break;
				}
			}
			secondLoto=secondLoto.getPreviousLoto();
		}
		if(secondLoto!=null)
		{
			return secondLoto.calculateInterval(firstLoto);
		}
		else
			return 9999;
	}
	public int previousCombInterval(String comb)
	{
		int equalCount=0;
		Loto firstLoto=null;
		Loto secondLoto=currentLoto;
		while(secondLoto!=null&&equalCount!=2)
		{	
			if(secondLoto.getCombination().equalsIgnoreCase(comb))
			{
				equalCount++;
				if(equalCount==1)
				{
					firstLoto=secondLoto;
				}
				else if(equalCount==2)
				{
					break;
				}
			}
			secondLoto=secondLoto.getPreviousLoto();
		}
		if(secondLoto!=null)
		{
			return secondLoto.calculateInterval(firstLoto);
		}
		else
			return 9999;
	}
	public int averageInterval(String number)
	{
		ArrayList<Integer> counts=new ArrayList<Integer>();
		Loto tempLoto=currentLoto;
		Loto previousLoto=currentLoto;
		while(tempLoto!=null)
		{
			if(tempLoto.getNumber().equalsIgnoreCase(number))
			{
				counts.add(new Integer(tempLoto.calculateInterval(previousLoto)));
				previousLoto=tempLoto;
			}
			tempLoto=tempLoto.getPreviousLoto();
		}	
		if(counts.size()!=0)
		{
			int sum=0;
			Iterator<Integer> ite=counts.iterator();
			while(ite.hasNext())
			{
				sum+=ite.next();
			}
			return (sum/counts.size());
		}
		else
			return 9999;
	}
	public int averageCombInterval(String comb)
	{
		ArrayList<Integer> counts=new ArrayList<Integer>();
		Loto tempLoto=currentLoto;
		Loto previousLoto=currentLoto;
		while(tempLoto!=null)
		{
			if(tempLoto.getCombination().equalsIgnoreCase(comb))
			{
				counts.add(new Integer(tempLoto.calculateInterval(previousLoto)));
				previousLoto=tempLoto;
			}
			tempLoto=tempLoto.getPreviousLoto();
		}	
		if(counts.size()!=0)
		{
			int sum=0;
			Iterator<Integer> ite=counts.iterator();
			while(ite.hasNext())
			{
				sum+=ite.next();
			}
			return (sum/counts.size());
		}
		else
			return 9999;
	}
	
	/**
	 * Generate combination & number from existing data
	 */
	public String generateCombination(String number)
	{
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
		return result;
	}
	public ArrayList<String> generateNumber(String combination)
	{
		ArrayList<String> result=new ArrayList<String>();
		for(int digit1=0;digit1<3;digit1++)
		{
			for (int digit2=0;digit2<3;digit2++)
			{
				if(digit2!=digit1)
				{
					for(int digit3=0;digit3<3;digit3++)
					{
						if(digit3!=digit2&&digit3!=digit1)
						{
							String temp="";
							temp+=combination.charAt(digit1);
							temp+=combination.charAt(digit2);
							temp+=combination.charAt(digit3);
							if(!result.contains(temp))
							{
								result.add(temp);
							}
						}
					}
				}
			}
		}
		return result;
	}
	/**
	 * Filter Results
	 */
	public ArrayList<LotoResult> filterNumber(int range)
	{
		ArrayList<String> allNumbers=new ArrayList<String>();
		for(int firstDigit=0;firstDigit<10;firstDigit++)
		{
			for(int secondDigit=0;secondDigit<10;secondDigit++)
			{
				for(int thirdDigit=0;thirdDigit<10;thirdDigit++)
				{
					String temp="";
					temp+=firstDigit;
					temp+=secondDigit;
					temp+=thirdDigit;
					allNumbers.add(temp);
				}
			}
		}
		Loto tempLoto=currentLoto;
		for(int i=0;i<range;i++)
		{
			if(allNumbers.contains(tempLoto.getNumber()))
			{
				allNumbers.remove(tempLoto.getNumber());
			}
			tempLoto=tempLoto.getPreviousLoto();
		}
		Iterator ite=allNumbers.iterator();
		ArrayList<LotoResult> results=new ArrayList<LotoResult>();
		while(ite.hasNext())
		{
			String tempNumber=(String)ite.next();
			results.add(new LotoResult(tempNumber,currentInterval(tempNumber),previousInterval(tempNumber),averageInterval(tempNumber)));
		}
		return results;
	}
	public ArrayList<LotoResult> filterCombination(int range)
	{
		ArrayList<String> allNumbers=new ArrayList<String>();
		for(int firstDigit=0;firstDigit<10;firstDigit++)
		{
			for(int secondDigit=0;secondDigit<10;secondDigit++)
			{
				for(int thirdDigit=0;thirdDigit<10;thirdDigit++)
				{
					if(firstDigit<=secondDigit&&secondDigit<=thirdDigit)
					{
						String temp="";
						temp+=firstDigit;
						temp+=secondDigit;
						temp+=thirdDigit;
						allNumbers.add(temp);
					}
				}
			}
		}
		Loto tempLoto=currentLoto;
		for(int i=0;i<range;i++)
		{
			if(allNumbers.contains(tempLoto.getCombination()))
			{
				allNumbers.remove(tempLoto.getCombination());
			}
			tempLoto=tempLoto.getPreviousLoto();
		}
		Iterator ite=allNumbers.iterator();
		ArrayList<LotoResult> results=new ArrayList<LotoResult>();
		while(ite.hasNext())
		{
			String tempNumber=(String)ite.next();
			results.add(new LotoResult(tempNumber,currentCombInterval(tempNumber),previousCombInterval(tempNumber),averageCombInterval(tempNumber)));
		}
		return results;
	}
	public ArrayList<LotoResult> filterReCombination(int range)
	{
		ArrayList<String> firstList=new ArrayList<String>();
		ArrayList<String> secondList=new ArrayList<String>();
		ArrayList<String> allNumbers=new ArrayList<String>();
		for(int firstDigit=0;firstDigit<10;firstDigit++)
		{
			for(int secondDigit=0;secondDigit<10;secondDigit++)
			{
				String temp="";
				temp+=firstDigit;
				temp+=secondDigit;
				firstList.add(temp);
				secondList.add(temp);
			}
		}
		Loto tempLoto=currentLoto;
		for(int i=0;i<range;i++)
		{
			if(firstList.contains(tempLoto.getNumber().substring(0, 2)))
			{
				firstList.remove(tempLoto.getNumber().substring(0, 2));
			}
			if(secondList.contains(tempLoto.getNumber().substring(1, 3)))
			{
				secondList.remove(tempLoto.getNumber().substring(1, 3));
			}
			tempLoto=tempLoto.getPreviousLoto();
		}
		for(String a:firstList)
		{
			for(String b:secondList)
			{
				if(a.charAt(1)==b.charAt(0))
				{
					allNumbers.add(a+b.charAt(1));
				}
			}
		}
		Iterator ite=allNumbers.iterator();
		ArrayList<LotoResult> results=new ArrayList<LotoResult>();
		while(ite.hasNext())
		{
			String tempNumber=(String)ite.next();
			results.add(new LotoResult(tempNumber,currentInterval(tempNumber),previousInterval(tempNumber),averageInterval(tempNumber)));
		}
		return results;
	}
	public ArrayList<LotoResult> filterConstants()
	{
		ArrayList<String> allNumbers=new ArrayList<String>();
		for(int firstDigit=0;firstDigit<10;firstDigit++)
		{
			
					String temp="";
					temp+=firstDigit;
					temp+=firstDigit;
					temp+=firstDigit;
					allNumbers.add(temp);
				
		}
		Iterator ite=allNumbers.iterator();
		ArrayList<LotoResult> results=new ArrayList<LotoResult>();
		while(ite.hasNext())
		{
			String tempNumber=(String)ite.next();
			results.add(new LotoResult(tempNumber,currentInterval(tempNumber),previousInterval(tempNumber),averageInterval(tempNumber)));
		}
		return results;
	}

	public static void main(String[] args)
	{
		LotoController a=new LotoController();
		a.initialize();
		String result=a.generateCombination("969");
		System.out.println(result);
	}
	public void addNumber(int newYear, int newDay, String number) throws IOException 
	{
		Loto newLoto=new Loto(newYear,newDay, number);
		currentLoto.setNextLoto(newLoto);
		newLoto.setPreviousLoto(currentLoto);
		currentLoto=newLoto;
		FileWriter writer=new FileWriter("src/data.txt",true);
		writer.write(new Integer(newYear).toString());
		writer.write(new Integer(newDay).toString());
		writer.write(" "+number+"\n");
		writer.close();
	}
}
