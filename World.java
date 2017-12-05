import java.util.Arrays;

public class World{

  private char[][] world;
  private char[][] mirrorWorld;
  private int rows;
  private int cols;

  public World(int inRows, int inCols){
    int rowsPlus = inRows + 2; //Create invisible rows on either side
    int colsPlus = inCols + 2; //Create invisible columns on either side
    rows = inRows;
    cols = inCols;
    world = new char[rowsPlus][colsPlus];
    for (char[] a: world) {
      Arrays.fill(a, ' ');
    }
    fillRandom();
    tick(10);
    drawWorld();
    glider(2,2);
  }

  private void tick(int loops){
    mirrorWorld = world;
    for(int h = 0; h < loops; h++){
      for(int i = 1; i <= rows; i++) {
        for(int j = 1; j <= cols; j++) {
          // int x = glider(i,j);
          // System.out.println(x);
        }
      }
      // drawWorld();
    }
  }

  private void fillRandom(){
    for(int i = 1; i <= rows; i++) {
      for(int j = 1; j <= cols; j++) {
        if((int)(Math.random() * 2) == 0){
          world[i][j] = '*';
          System.out.println("Filling point " + i + ", " + j);
        }
      }
      System.out.println();
    }
  }

  private void drawWorld(){
    System.out.println(" 0123456789");
    for(int i = 1; i <= rows; i++) {
      System.out.print(i - 1);
      for(int j = 1; j <= cols; j++) {
        System.out.print(world[i][j]);
      }
      System.out.println();
    }
  }

  private int glider(int x, int y){
    int n=0;
    System.out.println("Grid:\n_________");
    for(int i = x - 1; i <= x + 1; i++) {
      for(int j = y - 1; j <= y + 1; j++){
        System.out.print(world[i][j]);
        if(world[i][j] == '*')
          n++;
      }
      System.out.println();
    }
    if(world[x][y] == '*')
      n--;
    System.out.println("Point " + x + ", " + y + " has " + n + " neighbors.");
    return n;
  }
}
