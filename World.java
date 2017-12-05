import java.util.Arrays;

public class World{

  private char[][] world;
  private char[][] mirrorWorld;
  private int rows;
  private int rowsPlus;
  private int cols;
  private int colsPlus;
  private int ticks;
  private int randomFactor;

  public World(int inRows, int inCols, int inTicks, int inRandom){
    rowsPlus = inRows + 2; //Create invisible rows on either side
    colsPlus = inCols + 2; //Create invisible columns on either side
    rows = inRows;
    cols = inCols;
    ticks = inTicks;
    randomFactor = inRandom;
    world = new char[rowsPlus][colsPlus];
    for (char[] a: world) {
      Arrays.fill(a, ' ');
    }
    // fillRandom();
    // mirrorWorld = world;

    world[1][2] = '*';
    world[2][3] = '*';
    world[3][1] = '*';
    world[3][2] = '*';
    world[3][3] = '*';

    tick(ticks);
    // drawWorld();
    // glider(2,2);
  }

  private void tick(int loops){
    mirrorWorld = new char[rowsPlus][colsPlus];
    for(int h = 0; h < loops; h++){

      for(int i = 1; i <= rows; i++) {
        for(int j = 1; j <= cols; j++) {
          int x = glider(i,j);
          // Conway logic
          if(x < 2)
            mirrorWorld[i][j] = ' ';
          if(x > 3)
            mirrorWorld[i][j] = ' ';
          if(x == 3)
            mirrorWorld[i][j] = '*';
          System.out.println("Spot at: " + i + ", " + j + ": " +
            world[i][j] + " -> " + x + " -> " + mirrorWorld[i][j]);
        }
      }


      drawWorld();
      world = mirrorWorld;
      try{
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void fillRandom(){
    for(int i = 1; i <= rows; i++) {
      for(int j = 1; j <= cols; j++) {
        if((int)(Math.random() * randomFactor) == 0){
          world[i][j] = '*';
          // System.out.println("Filling point " + i + ", " + j);
        }
      }
      // System.out.println();
    }
  }

  private void drawWorld(){
    // System.out.println(" 0123456789");
    for(int i = 1; i <= rows; i++) {
      // System.out.print(i - 1);
      for(int j = 1; j <= cols; j++) {
        System.out.print(world[i][j]);
      }
      System.out.println("\u001b[0m");
    }
  }

  private int glider(int x, int y){
    int n=0;
    // System.out.println("Grid:\n_________");
    for(int i = x - 1; i <= x + 1; i++) {
      for(int j = y - 1; j <= y + 1; j++){
        if(world[i][j] == '*')
          n++;
          System.out.println(i + ", " + j + " -> " + world[i][j]);
      }
      // System.out.println();
    }
    // if(world[x][y] == '*')
    // System.out.println("Point " + x + ", " + y + " has " + n + " neighbors.");
    if(world[x][y] == '*') return n - 1;
    else return n;
  }
}
