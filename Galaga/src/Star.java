//Galaga--Star Class
//Shal Xu April 30 2014
import java.awt.Color;
public class Star extends Shape
{
	private Color color=new Color((int)(Math.random()*100+150),(int)(Math.random()*100+150),(int)(Math.random()*100+150));
	public Star()
	{
		this.setX((int)(Math.random()*512));
		this.setY((int)(Math.random()*512));
		this.setVeloX(0);
		this.setVeloY(8);
	}
	public void move()
	{
		this.setY(this.getY()+this.getVeloY());
		if(this.getY()>512)
			this.setY(0);
	}
	public void draw()
	{
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(this.getX(), this.getY(), 1);
	}
}
