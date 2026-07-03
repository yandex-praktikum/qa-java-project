package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    @Test
    public void shouldReturnThreeBuns() {

        Database database = new Database();

        assertEquals(3,
                database.availableBuns().size());
    }

    @Test
    public void shouldReturnSixIngredients() {

        Database database = new Database();

        assertEquals(6,
                database.availableIngredients().size());
    }
}