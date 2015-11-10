//Galaga--Class NPCShip
//Shal Xu April 25 2014
public class NPCship extends Ship
{
	private int color;
	private int animation;
	private int destinyX;
	private int destinyY;
	private double timer;
	public NPCship(int destinyX, int destinyY,int anime,int color)
	{
		this.destinyX=destinyX;
		this.destinyY=destinyY;
		this.animation=anime;
		this.color=color;
		timer=314;
		switch(animation)
		{
			case 0:
				this.setX(destinyX+400);
				this.setY(destinyY-100);
				this.setVeloX(-6);
				this.setVeloY(6);
				break;
			case 1:
				this.setX(this.destinyX+512);
				this.setY(this.destinyY);
				this.setVeloX(-8);
				this.setVeloY(0);
				break;
			case 2:
				this.setX((int)(100*Math.cos(timer/50)+30*(timer/50)+200));
				this.setY((int)(-100*Math.sin(timer/50)+200));
				break;
		}
	}
	public void move()
	{
		timer-=2;
		switch(animation)
		{
		case 0:
			if(timer%50==16)
			{
				this.setVeloY(this.getVeloY()*-1);
			}
			super.move();
			break;
		case 1:
			if(this.getX()>destinyX)
			{
				
			 this.setVeloY((int)(10*Math.cos(((double)(this.getX()-destinyX))/512*5*Math.PI)));
			}
			else
			{
				this.setVeloX(0);
				this.setVeloY(0);	
			}
			super.move();
			break;
		case 2:
			this.setX((int)(100*Math.cos(timer/50)+30*(timer/50)+200));
			this.setY((int)(-100*Math.sin(timer/50)+200));
			break;
		}
	}
	public void draw()
	{
		switch (color)
		{
		case 0:
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledEllipse(this.getX(),this.getY(),15,15);
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			StdDraw.filledEllipse(this.getX(),this.getY()+10,30,10);
			break;
		case 1:
			double[] xTemp=new double[3];
			double[] yTemp=new double[3];
			xTemp[0]=this.getX();
			xTemp[1]=this.getX();
			xTemp[2]=this.getX()+30;
			yTemp[0]=this.getY()-10;
			yTemp[1]=this.getY()+10;
			yTemp[2]=this.getY()-10;
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledPolygon(xTemp,yTemp);
			xTemp[2]-=60;
			StdDraw.filledPolygon(xTemp,yTemp);
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledEllipse(this.getX(),this.getY(),10,20);
			break;
		case 2:
			StdDraw.setPenColor(StdDraw.MAGENTA);
			StdDraw.filledCircle(this.getX(),this.getY(),25);
			StdDraw.setPenColor(StdDraw.BLACK);
			for(double a=Math.PI/2;a<Math.PI*5/2;a+=Math.PI/3)
			{
				StdDraw.filledCircle(this.getX()+Math.cos(a)*15,this.getY()+Math.sin(a)*15,5);
			}
			break;
		}
	}
	public Missle attack()
	{
		return new Missle(this.getX(),this.getY(),10);
	}
	public boolean hit(Missle missle)
	{
		if(missle.getVeloY()<0&&missle.getX()>this.getX()-30&&missle.getX()<this.getX()+30&&missle.getY()>this.getY()-15&&missle.getY()<this.getY()+15)
			{
			return true;
			}
		else
			return false;
	}
}
