//Galaga--Class Shape
//Shal Xu April 25
public abstract class Shape 
{
	private int x;
	private int y;
	private int veloX;
	private int veloY;
	public Shape()
	{	
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getVeloX()
	{
		return veloX;
	}
	public int getVeloY()
	{
		return veloY;
	}
	public void setX(int newX)
	{
		this.x=newX;
	}
	public void setY(int newY)
	{
		this.y=newY;
	}
	public void setVeloX(int newVelo)
	{
		veloX=newVelo;
	}
	public void setVeloY(int newVelo)
	{
		veloY=newVelo;
	}
	public void move()
	{
		x+=veloX;
		y+=veloY;
	}
	abstract void draw();
}
