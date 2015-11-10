import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Analysis {

	public static void main(String[] args)
	{
		Scanner scanner=null;
		try {
			scanner= new Scanner(new File("src/Records.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintWriter writer=null;
		try {
			writer=new PrintWriter("src/Simplifieddata.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int delay=0;
		while(scanner.hasNext())
		{
			String temp=scanner.nextLine().substring(0,12);
			writer.println(temp);
			System.out.println("0");
			if(delay>300)
			{
				delay=0;
			}
			delay++;
		}
		scanner.close();
		writer.close();
	}
}
