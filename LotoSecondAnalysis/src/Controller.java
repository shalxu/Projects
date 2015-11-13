import java.lang.Character;
public class Controller 
{
	private Window window;
	public Controller()
	{
	}
	protected void setWindow(Window window)
	{
		this.window=window;
	}
	protected boolean isLegid(String number)
	{
		boolean result=true;
		if(number.length()!=4&&number.length()!=3)
		{
			result=false;
		}
		else
		{
			for(int i=0;i<3;i++)
			{
				if(!Character.isDigit(number.charAt(i)))
				result=false;
			}
		}
		return result;
	}
	protected boolean isCombination(String number)
	{
		if(number.charAt(0)==number.charAt(1)||number.charAt(0)==number.charAt(2)||number.charAt(1)==number.charAt(2))
		{
			return true;
		}
		else
			return false;
	}

}
