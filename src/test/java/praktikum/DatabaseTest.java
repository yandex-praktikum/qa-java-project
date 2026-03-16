package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DatabaseTest {

    @Test
    public void availableBunsListIsNotEmpty() {
        Database db = new Database();
        assertNotNull(db.availableBuns());
        assertFalse(db.availableBuns().isEmpty());
    }

    @Test
    public void availableIngredientsListIsNotEmpty() {
        Database db = new Database();
        assertNotNull(db.availableIngredients());
        assertFalse(db.availableIngredients().isEmpty());
    }
}