import javax.swing.ImageIcon;

/**
 * DoorZombies that has low health, normal speed and normal damage.
 * @author user Shal Xu & Riley Manifold
 */
public class doorZombie extends Zombie
{
	int moveDelay;
	/**
	 * Constructor.
	 * @param lane The lane in which the zombie is entering.
	 */
	public doorZombie(int lane)
	{
		super(lane);
		this.image=new ImageIcon("graphics/doorZombie.jpg");
		moveDelay=0;
		speed=2;
		health=200;
		damage=1;
	}
	/**
	 * Move the x location of zombie and set the speed to positive. 
	 */
	protected void move()
	{
		super.move();
		moveDelay++;
		if(moveDelay>20)
		{
			moveDelay=0;
			if(speed==2)
				speed=1;
			else
				speed=2;
		}
	}
}
