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
     fillRandom();
    // mirrorWorld = world;

    /*world[1][2] = '*';
    world[2][3] = '*';
    world[3][1] = '*';
    world[3][2] = '*';
    world[3][3] = '*';
*/
    tick(ticks);
    // drawWorld();
    // glider(2,2);
  }

  private void tick(int loops)
  {
    mirrorWorld = new char[rowsPlus][colsPlus];
    for(int h = 0; h < loops; h++)
    {

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
          world[r][c] = mirrorWorld[r][c];
        }
      }
	  drawWorld();
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
          //int x = glider(i,j);
          // Conway logic
          //if(x < 2)
            //mirrorWorld[i][j] = ' ';
          //if(x > 3)
            //mirrorWorld[i][j] = ' ';
          //if(x == 3)
            //mirrorWorld[i][j] = '*';
          //System.out.println("Spot at: " + i + ", " + j + ": " +
          //world[i][j] + " -> " + x + " -> " + mirrorWorld[i][j]);
      
      
    

      //drawWorld();
      //world = mirrorWorld;
      //try
      //{
        //Thread.sleep(1000);
      //} catch (InterruptedException e) 
      //{
        //e.printStackTrace();
      //}
    
  

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
      // System.out.println();
    }
  }

  private void drawWorld()
  {
    // System.out.println(" 0123456789");
    for(int i = 1; i <= rows; i++) 
    {
      // System.out.print(i - 1);
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
    // System.out.println("Grid:\n_________");
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
    // if(world[x][y] == '*')
    // System.out.println("Point " + x + ", " + y + " has " + n + " neighbors.");
    if(world[x][y] == '*') return n - 1;
    else return n;
  }
  //public void changeColor() {
    //String colors[] = {"\u001b[30m","\u001b[31m","\u001b[32m","\u001b[33m","\u001b[34m","\u001b[35m",
      //"\u001b[36m","\u001b[37m","\u001b[0m"};
    //int picker = (int)(Math.random() * 8);
    //System.out.print(colors[picker]); 
  //} 

}
