package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database db;

    @Before
    public void setUp() {
        db = new Database();
    }

    @Test
    public void availableBuns() {
        assertFalse(db.availableBuns().isEmpty());
    }

    @Test
    public void availableIngredients() {
        assertFalse(db.availableIngredients().isEmpty());
    }

}