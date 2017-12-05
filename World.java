import java.util.Arrays;

public class World{

  private char[][] world;
  private char[][] mirrorWorld;
  private int rows;
  private int cols;
  private int ticks;

  public World(int inRows, int inCols, int inTicks){
    int rowsPlus = inRows + 2; //Create invisible rows on either side
    int colsPlus = inCols + 2; //Create invisible columns on either side
    rows = inRows;
    cols = inCols;
    ticks = inTicks;
    world = new char[rowsPlus][colsPlus];
    for (char[] a: world) {
      Arrays.fill(a, ' ');
    }
    fillRandom();
    tick(ticks);
    // drawWorld();
    // glider(2,2);
  }

  private void tick(int loops){
    mirrorWorld = world;
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
          // System.out.println(x);
        }
      }
      drawWorld();
      try{
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void fillRandom(){
    for(int i = 1; i <= rows; i++) {
      for(int j = 1; j <= cols; j++) {
        if((int)(Math.random() * 20) == 0){
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
        // System.out.print(world[i][j]);
        if(world[i][j] == '*')
          n++;
      }
      // System.out.println();
    }
    if(world[x][y] == '*')
      n--;
    // System.out.println("Point " + x + ", " + y + " has " + n + " neighbors.");
    return n;
  }
}
