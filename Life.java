import java.util.Arrays;

public class Life implements ILife {

  private boolean[][] cells;
  private final int WORLD_HEIGHT = 5 ;
  private final int WORLD_WIDTH = 5;

  public Life() {
    nukeAll();
  }

  public Life(String[] setup) {
    this();
    cells = new boolean[setup.length][setup[0].length()];

    for (int y = 0; y < setup.length; y++)
      for (int x = 0; x < setup[y].length(); x++)
        if (setup[y].charAt(x) != ' ')
          setAlive(x, y);
  }

  public void nukeAll() {
    cells = new boolean[WORLD_HEIGHT][WORLD_WIDTH];  
  }

  public void setAlive(int x, int y) {
    cells[y][x] = true;
  }

  public void setDead(int x, int y) {
    cells[y][x] = false;
  }

  public boolean isAlive(int x, int y) {
    return cells[y][x];
  }

  public ILife nextGeneration()  {
    int[][] neighbours = getNeighbours();

    for(int y = 0; y < cells.length; y++) {
      for(int x = 0; x < cells[y].length; x++) {
        if(neighbours[y][x] < 2 || neighbours[y][x] > 3) {
          setDead(x, y);
        } else if(neighbours[y][x] == 3) {
          setAlive(x, y);
        }
      }
    }
    printArray(neighbours);

    return this;  
  }

  private int[][] getNeighbours() {
    int neighbours[][] = new int[cells.length][cells[0].length];
    for(int y = 0; y < cells.length; y++) {
      for(int x = 0; x < cells[y].length; x++) {
        if(cells[y][x]) {
          neighbours = raiseNeighbours(neighbours, x, y);
        }
      }
    }
    return neighbours;
  }

  private int[][] raiseNeighbours(int[][] neighbours, int centerX, int centerY) {
    for (int shiftY = -1; shiftY <= 1; shiftY++) {
      for (int shiftX = -1; shiftX <= 1; shiftX++) {
        int posY = centerY + shiftY;
        int posX = centerX + shiftX;

        if (!(posX < 0 ||  posY < 0 
        || posY > neighbours.length || posX > neighbours[posY].length
        || (shiftX == 0 &&  shiftY == 0))) {
          neighbours[posY][posX]++;
        }
      }
    }
    return neighbours;
  }

  private void printArray(int[][] arr) {
    System.out.println("\n");

    for(int i = 0; i< arr.length; i++){
      System.out.println(Arrays.toString(arr[i]) + "\t" + Arrays.toString(cells[i]));
    }

    System.out.println("\n");
  }
}