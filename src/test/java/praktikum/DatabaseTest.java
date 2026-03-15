package praktikum;

import constants.TestMessages;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    @Test
    void databaseHasThreeBuns() {
        Database db = new Database();
        assertEquals(3, db.availableBuns().size(), TestMessages.ERROR);
    }

    @Test
    void databaseHasSixIngredients() {
        Database db = new Database();
        assertEquals(6, db.availableIngredients().size(), TestMessages.ERROR);
    }

    @Test
    void databaseHasThreeSaucesAndThreeFillings() {
        Database db = new Database();
        long sauces = db.availableIngredients().stream()
                .filter(i -> i.getType() == IngredientType.SAUCE).count();
        long fillings = db.availableIngredients().stream()
                .filter(i -> i.getType() == IngredientType.FILLING).count();
        assertEquals(3, sauces, TestMessages.ERROR);
        assertEquals(3, fillings, TestMessages.ERROR);
    }
}