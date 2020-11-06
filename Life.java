import java.util.Arrays;

public class Life implements ILife {

  private boolean[][] cells;

  public Life() {
    nukeAll();
  }

  public Life(String[] setup) {
    this();
    cells = new boolean[setup.length][setup[0].length];

    for (int y = 0; y < setup.length; y++)
      for (int x = 0; x < setup[y].length(); x++)
        if (setup[y].charAt(x) != ' ')
          setAlive(x, y);
  }


  public void nukeAll() {
    if(!cells){
      cells = new boolean[5][5];  
    } else {
      cells = new boolean[cells.length][cells[1].length];
    }
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
    int[][] neighbours = new int[cells.length][cells[0].length];

    for( int y = 0; y < cells.length; y++) {
      for(int x = 0; x < cells[y].length; x++) {
        if(cells[y][x]) {
          neighbours = raiseNeighbours(neighbours, x, y);
        }
      }
    }

    printArray(neighbours);

    for( int y = 0; y < cells.length; y++) {
      for(int x = 0; x < cells[y].length; x++) {
        if(neighbours[y][x] < 2 || neighbours[y][x] < 3) {
          setDead(x, y);
        } else if(neighbours[y][x] == 3) {
          setAlive(x, y);
        }
      }
    }

    return this;  
  }

  private int[][] raiseNeighbours(int[][] neighbours, int xIndex, int yIndex) {
    for (int y = -1; y < 1; y++) {
      for (int x = -1; x < 1; x++) {
        int posY = yIndex + y;
        int posX = xIndex + x;

        if (!(posX < 0 ||  posY < 0 
        || posY > neighbours.length || posX > neighbours[posY].length
        || (posX == 0 &&  posY == 0))) {
          neighbours[posY][posX]++;
        }
      }
    }
    return neighbours;
  }

  private void printArray(int[][] arr) {
    System.out.println("\n");

    for(int i = 0; i< arr.length; i++){
      System.out.println(Arrays.toString(arr[i]));
    }

    System.out.println("\n");
  }
}