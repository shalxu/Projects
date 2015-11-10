import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

/**
 * A sunflower class that is able to generate sun, but will not attack any zombies.
 * @author Shal Xu & Riley Manifold
 *
 */
public class sunflower extends Plant{
	private int sunDelay;
	/**
	 * The current sun this sunflower is generating. Null if no such sun exists.
	 */
	protected Sun sun;
	/**
	 * Constructor.
	 * @param locationX x location of plant.
	 * @param locationY y location of plant.
	 */
	public sunflower(int locationX, int locationY)
	{
	super(locationX, locationY);
	image=new ImageIcon("graphics/sunFlower.gif");
	damage=0;
	health=60;
	sunDelay=0;
	sun=null;
	}
	/**
	 * The sunflower prepares to generate a sun, or moves the current the sun.
	 * @return 1 if the sun has reached the buyboard and is ready to adds to the total sun count. 0 otherwise.
	 */
	protected int moveSun()
	{
		sunDelay++;
		if(sunDelay==150)
			sunDelay=0;
		if(sunDelay==1)
		{
			sun=new Sun(this.x,this.y-10);
		}
		if(sunDelay>0&&sunDelay<10)
		{
			sun.x=sun.x/(10-sunDelay)*(10-sunDelay-1);
			sun.y=50+(sun.y-50)/(10-sunDelay)*(10-sunDelay-1);
		}
		if(sunDelay==10)
		{
			sun=null;
			return 1;
		}
		else
			return 0;
	}
	/**
	 * A sun class that keeps the sun this sunflower generates.
	 */
	protected class Sun extends Subject
	{
		protected Sun(int x, int y)
		{
			this.x=x;
			this.y=y;
			image=new ImageIcon("graphics/pea.png");
		}
	}
	protected void launchPeas()
	{
		return;
	}
	@Override
	protected void movesPeas() 
	{	
		return;
	}
}
