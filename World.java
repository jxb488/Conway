import java.util.Arrays;

public class World
{

  private char[][] world;
  private char[][] mirrorWorld;
  private int rows;
  private int rowsPlus;
  private int cols;
  private int colsPlus;
  private int ticks;
  private int randomFactor;

  //Patterns pat = new Patterns();

  public World(int inRows, int inCols, int inTicks, int inRandom)
  {
    rowsPlus = inRows + 2; //Create invisible rows on either side
    colsPlus = inCols + 2; //Create invisible columns on either side
    rows = inRows;
    cols = inCols;
    ticks = inTicks;
    randomFactor = inRandom;
    world = new char[rowsPlus][colsPlus];
    for (char[] a: world) 
    {
      Arrays.fill(a, ' ');
    }
     //fillRandom();
    mirrorWorld = world;

	/* Glider - testing logic and valid ticks
    world[1][2] = '*';
    world[2][3] = '*';
    world[3][1] = '*';
    world[3][2] = '*';
    world[3][3] = '*'; */

    tick(ticks);

  }

  private void tick(int loops)
  {
    mirrorWorld = new char[rowsPlus][colsPlus];
    for(int h = 0; h < loops; h++)
    {
	  drawWorld();
      for(int i = 1; i <= rows; i++)
      {
        for(int j = 1; j <= cols; j++)
        {
          newWorld(i,j);
        }
      }  
      for (int r = 1; r <= rows; r++)
      {
        for(int c = 1; c <= cols; c++)
        {
          if (mirrorWorld[r][c] == '*')
			world[r][c] = '*';
		  else world[r][c] = ' ';
        }
      }
	  
      try
      {
        Thread.sleep(1000);
      } catch (InterruptedException e) 
      {
        e.printStackTrace();
      }	  
   }
 }

   private void newWorld(int r, int c)
   {

    int count = countNeighbors(r,c);
    char present = world[r][c];

    if ((present == '*') && (count < 2))
    { //less than 2 neighbors = death
      mirrorWorld[r][c] = ' ';
    }
    else if ((present == '*') && ((count == 2) || (count == 3)))
    { //2 or 3 neighbors = still alive
      mirrorWorld [r][c] = '*';
    }
    else if ((present == '*') && (count > 3))
    { //more than 2 neighbors dies
      mirrorWorld [r][c] = ' ';
    }
    else if ((present == ' ') && (count == 3))
    { //3 neighbors dead cell comes alive
      mirrorWorld [r][c] = '*';
    }
   }
  
  private void fillRandom()
  {
    for(int i = 1; i <= rows; i++) 
    {
      for(int j = 1; j <= cols; j++) 
      {
        if((int)(Math.random() * randomFactor) == 0)
        {
          world[i][j] = '*';
          // System.out.println("Filling point " + i + ", " + j);
        }
      }
    }
  }

  private void drawWorld()
  {
    for(int i = 1; i <= rows; i++) 
    {
      for(int j = 1; j <= cols; j++) 
      {
        System.out.print(world[i][j]);
      }
      System.out.println();
    }
  }

  private int countNeighbors(int x, int y)
  {
    int n=0;
    for(int i = x - 1; i <= x + 1; i++) 
    {
      for(int j = y - 1; j <= y + 1; j++)
      {
        if(world[i][j] == '*')
          n++;
          //System.out.println(i + ", " + j + " -> " + world[i][j]);
      }
      // System.out.println();
    }
    //if(world[x][y] == '*')
    //System.out.println("Point " + x + ", " + y + " has " + n + " neighbors.");
    if(world[x][y] == '*') return n - 1;
    else return n;
  }
}
