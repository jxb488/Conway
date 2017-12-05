import java.util.Arrays;

public class World{

  private char[][] world;
  private char[][] mirrorWorld;
  private int rows;
  private int cols;

  public World(int inRows, int inCols){
    rows = inRows + 2; //Create invisible rows on either side
    cols = inCols + 2; //Create invisible columns on either side
    world = new char[rows][cols];
    for (char[] a: world) {
      Arrays.fill(a, '*');
    }
    fillRandom();
    drawWorld();
    tick();
  }

  private void tick(){
    mirrorWorld = world;
    glider(30,40);
  }

  private void fillRandom(){
    for(int i = 1; i < rows; i++) {
      for(int j = 1; j < rows; j++) {
        if((int)(Math.random() * 2) == 0){
          world[i][j] = ' ';
        }
      }
      System.out.println();
    }
  }

  private void drawWorld(){
    for(int i = 1; i < rows; i++) {
      for(int j = 1; j < rows; j++) {
        System.out.print(world[i][j]);
      }
      System.out.println();
    }
  }

  private int glider(int x, int y){
    int n=0;

    for(int i = x - 1; i <= x + 1; i++) {
      for(int j = y - 1; j <= y + 1; j++){
        System.out.print(world[i][j]);
        if(world[i][j] == '*')
          n++;
      }
      System.out.println();
    }
    System.out.println(n + " neighbors.");
    return n;
  }
}
