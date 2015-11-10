import javax.swing.ImageIcon;

/**
 * FootballZombies that has high health, high speed and high damage.
 * @author user Shal Xu & Riley Manifold
 */
public class footballZombie extends Zombie
{
	/**
	 * Constructor.
	 * @param lane lane The lane in which the zombie is entering.
	 */
	public footballZombie(int lane)
	{
		super(lane);
		this.image=new ImageIcon("graphics/footballZombie.jpg");
		speed=4;
		health=600;
		damage=2;
	}
	/**
	 * Move the x location of zombie and set the speed to positive. 
	 */
	protected void move()
	{
		super.move();
		speed=2;
	}
}
