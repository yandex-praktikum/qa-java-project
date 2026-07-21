package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void availableBunsReturnsCorrectList() {
        assertNotNull(database.availableBuns());
        assertFalse(database.availableBuns().isEmpty());
        assertEquals(3, database.availableBuns().size());
    }

    @Test
    public void availableIngredientsReturnsCorrectList() {
        assertNotNull(database.availableIngredients());
        assertFalse(database.availableIngredients().isEmpty());
        assertEquals(6, database.availableIngredients().size());
    }
}
