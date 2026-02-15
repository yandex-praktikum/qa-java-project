package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatabaseTests {

    @Test
    public void shouldReturnBunsList() {
        Database db = new Database();
        assertNotNull(db.availableBuns());
        assertEquals(3, db.availableBuns().size());
    }

    @Test
    public void shouldReturnIngredientsList() {
        Database db = new Database();
        assertNotNull(db.availableIngredients());
        assertEquals(6, db.availableIngredients().size());
    }
}
