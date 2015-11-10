import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * General Plant Class which can attack incoming zombies but cannot move.
 * @author user Shal Xu & Riley Manifold
 */
public abstract class Plant extends Subject 
{
	/**
	 * Attack damage of plant, 0 is the plant is harmless.
	 */
	protected int damage;
	/**
	 * ArrayList of peas to shoot at zombies of the plant. Null if no such peas exists, or if the plant is harmless.
	 */
	protected ArrayList<Pea> peas;
	/**
	 * Constructor
	 * @param locationX x location of plant
	 * @param locationY y location of plant
	 */
	public Plant(int locationY, int locationX)
	{
		x=PvZView.gridX+(locationX*PvZView.gridWidth);
		y=PvZView.gridY+(locationY*PvZView.gridHeight);
		peas=new ArrayList<Pea>();
	}
	/**
	 * Pea class of the plant that keeps all peas the plant launches.
	 */
	protected class Pea extends Subject
	{
		protected Pea(int x, int y)
		{
			this.x=x;
			this.y=y;
			image=new ImageIcon("graphics/pea.png");
		}
	}
	/**
	 * Moves each of the peas, does nothing if the plant is harmless
	 */
	protected abstract void movesPeas();
	/**
	 * The plant prepares and launches the pea after attackDelay time, does nothing if the plant is harmless
	 */
	protected abstract void launchPeas();
	/**
	 * Attack a zombie to cause it damage.
	 * @param z The zombie attacked.
	 * @param damage damage applied.
	 */
	protected  void attack(Zombie z, int damage)
	{
		z.attacked(damage);
	}
	/**
	 * The plants get eaten by a zombie.
	 * @param damage damage applied.
	 */
	protected void eaten(int damage) 
	{
		this.health-=damage;
	}
	/**
	 * Returns the damage of a plant.
	 * @return damage of plant.
	 */
	protected int getDamage()
	{
		return damage;
	}
}
