//Galaga--Game Class
//Shal Xu--April 25 2014
import java.util.ArrayList;
import java.util.Iterator;
public class Game 
{
	public boolean dead;
	private int lives;
	private int score;
	private Star[] stars=new Star[30];
	private ArrayList<NPCship> ships =new ArrayList<NPCship>();
	private ArrayList<Missle> missles=new ArrayList<Missle>();
	private myShip myShip=new myShip();
	private boolean addShip;
	public Game()
	{
		dead=false;
		lives=3;
		score=0;
		addShip=false;
		for(int i=0;i<30;i++)
		{
			stars[i]=new Star();
		}
	}
	private void background()
	{
		StdDraw.clear(StdDraw.BLACK);
		for(int i=0;i<30;i++)
		{
			stars[i].draw();
			stars[i].move();
		}
	}
	
	public void operate()
	{
		int shipX =0;
		int shipY=0;
		int shipAnime=-1;
		int delay=0;
		StdDraw.setCanvasSize();
		StdDraw.setXscale(0,512);
		StdDraw.setYscale(512,0);
		while(true)
		{
			StdDraw.show(25);
			delay++;
			if(delay==40)
				delay=0;
			this.background();
			myShip.draw();
			if (myShip.launchMissel() && delay % 2 == 0)
			{
				missles.add(new Missle((int)(StdDraw.mouseX()),470,-15));
			}
			for(int i=0;i<missles.size();i++)
			{
				missles.get(i).draw();
				if(missles.get(i).getY()>530||missles.get(i).getY()<-15)
					missles.remove(i);
			}
			Iterator<Missle> ite=missles.iterator();
			while (ite.hasNext())
			{
				if(myShip.hit(ite.next()))
				{
					ite.remove();
					lives--;
					break;
				}
			}
			if(ships.size()==0)
			{
				addShip=true;
				shipX=100;
				shipY=100;
				shipAnime=(int)(Math.random()*3);
			}
			else if(delay%13==0)
				{
					int bomb=(int)(Math.random()*ships.size());
					missles.add(ships.get(bomb).attack());
				}
			if(shipY>200)
			{
				addShip=false;
			}
			if(addShip)
			{
				if(delay%10==0&&delay>0)
				{
					ships.add(new NPCship(shipX,shipY,shipAnime,(int)(Math.random()*3)));
					shipX+=100;
					delay=0;
					if(shipX>400)
					{
						delay-=30;
						shipX=100;
						shipY+=100;
					}
				}
			}
			for(int i=ships.size()-1;i>=0;i--)
			{
			    ite=missles.iterator();
				ships.get(i).draw();
				ships.get(i).move();
				if(ships.get(i).getX()<-200)
					ships.remove(i);
				else
				{
					while (ite.hasNext())
					{
						if(ships.get(i).hit(ite.next()))
						{
							ite.remove();
							ships.remove(i);
							score+=50;
							break;
						}
					}
				}
			}
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.text(25, 500, "Lives: "+lives);
			StdDraw.text(25, 520, "Score: "+score);
			if(lives<=0)
			{
				StdDraw.clear(StdDraw.BLACK);
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.text(250,250,"YOU ARE DEAD");
				dead=true;
				if(StdDraw.mousePressed())
				break;
			}
		}
	}
}