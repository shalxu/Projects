//Galaga --myShip Class
//April 25 2014
public class myShip extends Ship
{
	private double[] x=new double[3];
	private double[] y=new double[3];
	private double[] xTemp=new double[3];
	private double[] yTemp=new double[3];
	public myShip()
	{
		this.setX(250);
		this.setY(500);
	}
	public void draw()
	{
		this.setX((int)(StdDraw.mouseX()));
		x[0]=this.getX()+30;
		x[1]=this.getX()-30;
		x[2]=this.getX();
		y[0]=this.getY();
		y[1]=this.getY();
		y[2]=this.getY()-30;
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledPolygon(x, y);
		StdDraw.setPenColor(StdDraw.GRAY);
		xTemp[0]=x[0]-45;
		xTemp[1]=x[1];
		xTemp[2]=x[2]-15;
		yTemp[0]=y[0];
		yTemp[1]=y[1];
		yTemp[2]=y[2]+15;
		StdDraw.filledPolygon(xTemp,yTemp);
		xTemp[0]=x[0];
		xTemp[1]=x[1]+45;
		xTemp[2]=x[2]+15;
		yTemp[0]=y[0];
		yTemp[1]=y[1];
		yTemp[2]=y[2]+15;
		StdDraw.filledPolygon(xTemp,yTemp);
	}
	public boolean launchMissel()
	{
		return StdDraw.mousePressed();
	}
	public boolean hit(Missle missle)
	{
		if(missle.getVeloY()>0&&missle.getX()>this.getX()-30&&missle.getX()<this.getX()+30&&missle.getY()>this.getY()-15&&missle.getY()<this.getY()+15)
			{
			return true;
			}
		else
			return false;
	}
}
