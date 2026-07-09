import org.junit.jupiter.api.Test;
import praktikum.Database;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTests {

    @Test
    public void shouldContainThreeBuns() {
        Database db = new Database();
        assertEquals(3, db.availableBuns().size());
    }

    @Test
    public void shouldContainSixIngredients() {
        Database db = new Database();
        assertEquals(6, db.availableIngredients().size());
    }
}
