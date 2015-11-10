class figure
{
  int x;
  int hp;
  int magicx;
  figure()
  {
    hp=1000;
    x=300;
    magicx=900;
  }
  void display()
  {
      image(figure, x, 380);
  }
  void move()
  {
    if (x>0 &&keyCode==37)
      x-=6;
    if (x<width-50&&keyCode==39)
      x+=6;
  }
  
  void magic()
  {
    if(keyCode==32)
    {
     magicx=0;
     keyCode=0;
    }
     fill(255,255,0);
      ellipse(x-magicx,410,10,10);
      magicx+=15;
    if(magicx>100)
    magicx=900;
  }
  
  void attacked()
  {
    fill(255,0,0);
    textSize(40);
    text("-50",x,350);
    hp-=50;
  }
  
  int hp()
  {
    return hp;
  }
  int magicx()
  {
    return magicx;
  }
  int getx()
  {
    return x;
  }
}

