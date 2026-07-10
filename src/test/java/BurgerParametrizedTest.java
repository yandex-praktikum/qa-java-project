import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;


import static org.junit.jupiter.api.Assertions.*;

public class BurgerParametrizedTest {

    private Burger burger;

    private Ingredient ingredient;

    private Bun bun;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @ParameterizedTest
    @CsvSource({
            "black bun, 100, SAUCE, Кетчуп, 50.2",
            "white bun, 200, FILLING, Бекон, 200.5",
            "red bun, 300, SAUCE, Кетчуп, 50.2"
    })
    void createBurgerWithParamsShouldReturnBurger(String bunName, float bunPrice, IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        bun = new Bun(bunName, bunPrice);
        ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertAll(
                () -> assertEquals(bunName, burger.bun.getName()),
                () -> assertEquals(bunPrice, burger.bun.getPrice()),
                () -> assertEquals(ingredientName, burger.ingredients.get(0).getName()),
                () -> assertEquals(ingredientPrice, burger.ingredients.get(0).getPrice()),
                () -> assertEquals(ingredientType, burger.ingredients.get(0).getType())
        );
    }
}
