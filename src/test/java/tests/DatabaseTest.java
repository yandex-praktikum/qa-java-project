package tests;

import org.junit.Test;
import praktikum.Database;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    @Test
    public void availableBunsShouldReturnThreeBuns() {
        Database database = new Database();

        int bunsSize = database.availableBuns().size();

        assertEquals(3, bunsSize);
    }

    @Test
    public void availableIngredientsShouldReturnSixIngredients() {
        Database database = new Database();

        int ingredientsSize = database.availableIngredients().size();

        assertEquals(6, ingredientsSize);
    }
}