import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest {

    Database database = new Database();

    @ParameterizedTest
    @CsvSource({
            "black bun, 100",
            "white bun, 200",
            "red bun, 300"
    })
    public void availableBunsTest(String nameBun, int priceBun){

        List<Bun> buns = database.availableBuns();

        boolean found = false;

        for (Bun bun : buns) {
            if (bun.getName().equals(nameBun)) {
                assertEquals(priceBun, bun.getPrice(), "Цена булки '" + nameBun + "' должна быть " + priceBun);
                found = true;
                break;
            }
        }
        assertTrue(found,"Булка с именем " + nameBun + " не найдена в списке");
    }
    @ParameterizedTest
    @CsvSource({
            "SAUCE, hot sauce, 100",
            "SAUCE, sour cream, 200",
            "SAUCE, chili sauce, 300",
            "FILLING, cutlet, 100",
            "FILLING, dinosaur, 200",
            "FILLING, sausage, 300"
    })

    void availableIngredientsTest(String typeIngredient, String nameIngredient, int priceIngredient) {

        IngredientType type = IngredientType.valueOf(typeIngredient);

        List<Ingredient> ingredients = database.availableIngredients();

        boolean found = false;

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getType() == type && ingredient.getName().equals(nameIngredient)) {
                assertEquals(priceIngredient, ingredient.getPrice(), "Цена ингредиента " + nameIngredient + " должна быть " + priceIngredient);
                found = true;
                break;
            }
        }

        assertTrue(found, "Ингредиент типа " + type + " имя " + nameIngredient + " не найден в списке");
    }
}
