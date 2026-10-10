package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void testAvailableBuns() {
        Database db = new Database();
        assertEquals(3, db.availableBuns().size());
    }

    @Test
    public void testAvailableIngredients() {
        Database db = new Database();
        assertEquals(6, db.availableIngredients().size());
    }
}