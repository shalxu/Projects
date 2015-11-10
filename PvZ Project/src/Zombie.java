import javax.swing.ImageIcon;

/**
 * A general zombie class that will move left in the lane and attack any plants in its way!
 * @author Shal Xu & Riley Manifold
 *
 */
public abstract class Zombie extends Subject {
	/**
	 * speed that the zombie is moving in.
	 */
	protected int speed;
	/**
	 * Attack damage of the zombie.
	 */
	protected int damage;
	/**
	 * Constructor.
	 * @param lane lane in which the zombie is entering.
	 */
	protected Zombie(int lane)
	{
		y=PvZView.gridY+(lane+1)*PvZView.gridHeight-144;
		x=1000;
	}
	/**
	 * The zombie simply moves by subtracting speed from x location.
	 */
	protected void move()
	{
		this.x-=this.speed;
	}
	/**
	 * Sets speed to 0 temporarily when the zombie is eating a plant. 
	 */
	protected void suspend()
	{
		speed=0;
	}
	/**
	 * Takes a bite on the plant.
	 * @param p plant attacked.
	 */
	protected void eat(Plant p)
	{
		p.eaten(damage);
	}
	/**
	 * Gets attacked by a plant.
	 * @param damage damage taken.
	 */
	protected void attacked(int damage)
	{
		health-=damage;
	}
}
