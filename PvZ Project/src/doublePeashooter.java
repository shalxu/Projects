import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;

/**
 * Repeater class that shoots two peas at a time at the first zombie in the same lane.
 * @author user Shal Xu & Riley Manifold
 */
public class doublePeashooter extends Plant
{
	private int attackDelay;
	/**
	 * Constructor
	 * @param locationX x location of plant
	 * @param locationY y location of plant
	 */
	public doublePeashooter(int locationX, int locationY)
	{
	super(locationX, locationY);
	image=new ImageIcon("graphics/doublePeashooter.jpg");
	damage=25;
	health=60;
	attackDelay=0;
	}
	/**
	 * The plant prepares and launches the pea after attackDelay time.
	 */
	protected void launchPeas()
	{
		attackDelay++;
		if(attackDelay==25)
		{
			peas.add(new Pea(this.x+40,this.y+10));
		}
		if(attackDelay==30)
		{
			peas.add(new Pea(this.x+40,this.y+10));
			attackDelay=0;
		}
	}
	/**
	 * Moves each of the peas
	 */
	@Override
	protected void movesPeas() 
	{	
		Iterator peaIte=peas.iterator();
		while (peaIte.hasNext())
		{
			Pea p=(Pea)peaIte.next();
			p.x+=15;
			if(p.x>1000)
				peaIte.remove();
		}
	}
}
