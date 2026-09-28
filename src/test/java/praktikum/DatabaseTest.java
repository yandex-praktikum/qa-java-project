package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {
    private Database database;

    @BeforeEach
    void SetUp() {
        database = new Database();
    }

    @Test
    void availableBuns_shouldReturnThreeBuns() {
        List<Bun> buns = database.availableBuns();
        assertEquals(3, buns.size());
    }

    @Test
    void availableIngredients_shouldReturnSixIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals(6, ingredients.size());
    }
}
