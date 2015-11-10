//Galaga--Missle Class
//Shal Xu April 25 2014
public class Missle extends Shape
{
	public Missle(int x, int y, int veloY)
	{
		this.setX(x);
		this.setY(y);
		this.setVeloY(veloY);
	}
	public void draw()
	{
		super.move();
		if(this.getVeloY()<0)
			StdDraw.setPenColor(StdDraw.RED);
		else
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledEllipse(this.getX(),this.getY(),3,6);
	}

}
