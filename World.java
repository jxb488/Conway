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
    tick();
  }

  private void tick(){
    mirrorWorld = world;
    glider(99,100);
  }

  private void fillRandom(){
    for(char[] i:world) {
      for(char j:i) {
        System.out.println(j);
      }
    }

  }

  private int glider(int x, int y){
    int n=0;

    for(int i = x - 1; i <= x + 1; i++) {
      for(int j = y - 1; j <= y + 1; j++){
        System.out.print(world[i][j]);
        n++;
      }
      System.out.println();
    }
    System.out.println(n + " neighbors.");
    return n;
  }

  private boolean evaluatePoint(int neighbors){
    boolean life = true;
    /*    Any live cell with fewer than two live neighbours dies (referred to as underpopulation or exposure[1]).
        Any live cell with more than three live neighbours dies (referred to as overpopulation or overcrowding).
        Any live cell with two or three live neighbours lives, unchanged, to the next generation.
        Any dead cell with exactly three live neighbours will come to life.*/



    return life;
  }

}
