import org.junit.Test;
import static org.junit.Assert.*;

public class LifeTest {
    
    @Test
    public void createNewCell() {
        
        // Arrange: drei lebende Zellen
        Life l = new Life();
        l.setAlive(0, 0);
        l.setAlive(0, 1);
        l.setAlive(1, 0);

        // Act: Berechnung der Folgegeneration
        ILife nextGen = l.nextGeneration();

        // Assert: Rasterpunkt mit drei Nachbarn sollte jetzt leben
        assertTrue(nextGen.isAlive(1, 1));
    }


    @Test
    public void destroyLonelyCell() {
      // Arrange: zwei lebende Zellen
        Life l = new Life();
        l.setAlive(0, 0);
        l.setAlive(0, 1);

        // Act: Berechnung der Folgegeneration
        ILife nextGen = l.nextGeneration();

        // Assert: Rasterpunkt mit weniger als zwei Nachbarn stirbt an Einsamkeit
        assertFalse(nextGen.isAlive(0, 0));
    }


    @Test
    public void keepAliveCell() {
      // Arrange: drei lebende Zellen
        Life l = new Life();
        l.setAlive(1, 0);
        l.setAlive(1, 1);
        l.setAlive(1, 2);

        // Act: Berechnung der Folgegeneration
        ILife nextGen = l.nextGeneration();

        // Assert: lebender Rasterpunkt mit zwei oder drei Nachbarn bleibt am leben
        assertTrue(nextGen.isAlive(1, 1));
    }


    @Test
    public void destroyCrowdedCell() {
      // Arrange: vier lebende Zellen
        Life l = new Life();
        l.setAlive(1, 0);
        l.setAlive(1, 1);
        l.setAlive(1, 2);
        l.setAlive(0, 1);
        l.setAlive(2, 2);

        // Act: Berechnung der Folgegeneration
        ILife nextGen = l.nextGeneration();

        // Assert: lebender Rasterpunkt mit zwei oder drei Nachbarn bleibt am leben
        assertFalse(nextGen.isAlive(1, 1));
    }
}