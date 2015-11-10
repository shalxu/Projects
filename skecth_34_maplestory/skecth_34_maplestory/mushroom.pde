class mushroom
{
  int x;
  int velo;
  int hp;
  int frame;
  boolean Coin;

  mushroom()
  {
    x=(int)random(0, width-150);
    velo=4;
    hp=500;
    Coin=false;
  }
  void move()
  {
    if (hp>0)
    {
      x+=velo;
      if (x<0||x>width-150)
        velo*=-1;
      image(mushroom, x, 350, 150, 160);
      frame++;
      if (frame==30)
      {
        velo=(int)random(-5, 5);
        frame=0;
      }
    }
    else
    {
      hp-=50;
      if (Coin)
      {
        image(coin, x, 430);
        if (me.getx()-x<30 && me.getx()-x>-30)
        {
          Coin=false;
          coinCount++;
        }
      }
      frame++;
      if (frame==300)
      {
        hp=500;
        frame=0;
      }
    }
  }
  void attacked()
  {
    if (hp>0)
    {
      x-=10;
      textSize(20);
      text("-100", x+100, 350); 
      hp-=100;
    }
    if(hp==0)
    Coin=true;
  }

  int x()
  {
    return x;
  }
  int hp()
  {
    return hp;
  }
}

