import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    private Database database;

    private List<Bun> expectedBuns;

    private List<Ingredient> expectedIngredients;

    @BeforeEach
    void setUp() {
    database = new Database();

    expectedBuns = new ArrayList<>();

    expectedIngredients = new ArrayList<>();
    }

    @Test
    void availableBunsShouldReturnAvailableBuns() {
        expectedBuns.add(new Bun("black bun", 100));
        expectedBuns.add(new Bun("white bun", 200));
        expectedBuns.add(new Bun("red bun", 300));

        assertEquals(expectedBuns.size(), database.availableBuns().size());

        for (int i = 0; i < expectedBuns.size(); i++) {

            int index = i;

            assertAll(
                 () -> assertEquals(expectedBuns.get(index).getPrice(), database.availableBuns().get(index).getPrice()),
                 () -> assertEquals(expectedBuns.get(index).getName(), database.availableBuns().get(index).getName())
            );
        }
    }

    @Test
    void availableIngredientsShouldReturnAvailableIngredients() {
        expectedIngredients.add(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        expectedIngredients.add(new Ingredient(IngredientType.SAUCE, "sour cream", 200));
        expectedIngredients.add(new Ingredient(IngredientType.SAUCE, "chili sauce", 300));

        expectedIngredients.add(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        expectedIngredients.add(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        expectedIngredients.add(new Ingredient(IngredientType.FILLING, "sausage", 300));

        assertEquals(expectedIngredients.size(), database.availableIngredients().size());

        for (int i = 0; i < expectedIngredients.size(); i++) {

            int index = i;

            assertAll(
                    () -> assertEquals(expectedIngredients.get(index).getName(), database.availableIngredients().get(index).getName()),
                    () -> assertEquals(expectedIngredients.get(index).getPrice(), database.availableIngredients().get(index).getPrice()),
                    () -> assertEquals(expectedIngredients.get(index).getType(), database.availableIngredients().get(index).getType())
            );
        }
    }
}
