
package praktikum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BurgerMoveIngredientParameterizedTest {
    private Burger burger;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "Tomato", 14.0f));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "cutlet", 99.0f));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "mustard", 10.0f));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "Sausage", 11.0f));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "Chicken", 15.0f));

    }

    @ParameterizedTest
    @CsvSource({
            "0, 3",
            "4, 0",
            "3, 2"
    })
    void moveIngredientFromStartIndexMovesItToNewIndex(int index, int newIndex) {
        //сохраняю список ингредиентов
        List<Ingredient> beforeList = burger.getIngredients();

        //сохраняю ингредиент который буду move
        Ingredient ingredientToMove = beforeList.get(index);
        //удалю ингредиент
        burger.moveIngredient(index, newIndex);
        //сохраню список ингредиентов после удаления
        List<Ingredient> afterList = burger.getIngredients();

        //проверки
        assertEquals(ingredientToMove, afterList.get(newIndex));
    }
}
