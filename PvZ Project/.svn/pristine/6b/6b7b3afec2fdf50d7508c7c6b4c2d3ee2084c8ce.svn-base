/**
 * A Runnable that keeps track of the pace of game in PvZView. PvZView is an observer of this class.
 * @author Shal Xu & Riley Manifold.
 *
 */
public class Timer implements Runnable{
	/**
	 * The PvZView that observes this timer.
	 */
	private PvZView view;
	/**
	 * count of seconds that has elapsed since start of game
	 */
	private int seconds;
	/**
	 * Constructor.
	 * @param view PvZView.
	 */
	protected Timer(PvZView view)
	{
		this.view=view;
		seconds=0;
	}
	/**
	 * returns count of seconds of this game.
	 * @return count of seconds.
	 */
	protected int getSeconds()
	{
		return seconds;
	}
	/**
	 * Run. Adds zombies into PvZView based on pace of game.
	 */
	@Override
	public void run() {
		while (true)
		{
			seconds++;
			System.out.println(seconds);
			if(seconds<=50)
			{
				if(seconds%10==9)
				{
					int temp=(int)(Math.random()*5);
					view.zombies[temp].add(new doorZombie(temp));
				}
			}
			else if(seconds<=120)
			{
				if(seconds%5==4)
				{
				int temp=(int)(Math.random()*5);
				int zombie=(int)(Math.random()*3);
				switch(zombie)
				{
				case 0:
					view.zombies[temp].add(new coneZombie(temp));
					break;
				case 1:
					view.zombies[temp].add(new footballZombie(temp));
					break;
				case 2:
					view.zombies[temp].add(new doorZombie(temp));
					break;
				}
			}	
			}
			else if(seconds<=150)
			{
				if(seconds%2==0)
				{
					for (int i=0;i<2;i++)
					{
						int temp=(int)(Math.random()*5);
						int zombie=(int)(Math.random()*3);
						switch(zombie)
						{
						case 0:
							view.zombies[temp].add(new coneZombie(temp));
							break;
						case 1:
							view.zombies[temp].add(new footballZombie(temp));
							break;
						case 2:
							view.zombies[temp].add(new doorZombie(temp));
							break;
						}
				}
			}
			}
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
			e.printStackTrace();
			}
			view.panel.repaint();
		}
	}
}
