import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

/**
 * Peashooter class that shoots one pea at a time at the first zombie in the same lane.
 * @author user Shal Xu & Riley Manifold
 */
public class Peashooter extends Plant
{
	private int attackDelay;
	/**
	 * Constructor
	 * @param locationX x location of plant
	 * @param locationY y location of plant
	 */
	public Peashooter(int locationX, int locationY)
	{
	super(locationX, locationY);
	image=new ImageIcon("graphics/peashooter.jpg");
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
			attackDelay=0;
			peas.add(new Pea(this.x+40,this.y+10));
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
