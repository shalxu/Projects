PImage backGround;
PImage figure;
PImage mushroom;
PImage coin;
int coinCount;
mushroom mush0;
mushroom mush1;
figure me;
void setup()
{
  size(800, 550);
  backGround=loadImage("background.png");
  figure=loadImage("figure.png");
  mushroom=loadImage("mushroom.gif");
  coin=loadImage("coin.png");
  mush0=new mushroom();
  mush1=new mushroom();
  me=new figure();
  coinCount=0;
}

void draw()
{
  fill(0);
  rect(0, 500, 800, 100);
  fill(255);
  textSize(30);
  text("HP                              "+me.hp(), 50, 530);
  fill(255);
  text(coinCount, 530,530);
  rect(100, 510, 260, 30);
  fill(255, 0, 0);
  rect(100+3, 510+3, 260*me.hp()/1000-6, 30-6);
  image(coin, 500, 510);
  
  if (me.hp()>0)
  {
    image(backGround, 0, 0, width, height-50);
    mush0.move();
    mush1.move();
    me.display();
    me.magic();
    if (me.magicx()<900 && me.getx()-me.magicx-50 < mush0.x())
      mush0.attacked();
    if (me.magicx()<900 && me.getx()-me.magicx-50 < mush1.x())
      mush1.attacked();
    if ((me.getx()-mush0.x()<10 && me.getx()-mush0.x()>-10) && mush0.hp()>0||(me.getx()-mush1.x()<10 && me.getx()-mush1.x()>-10) && mush1.hp()>0)
      me.attacked();
    
  }
  else
  {
    background(0);
    textSize(100);
    fill(255, 0, 0);
    text("GAME OVER", 100, 300);
  }
}

void keyPressed()
{
  me.move();
}


