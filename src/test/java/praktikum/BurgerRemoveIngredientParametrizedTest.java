package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BurgerRemoveIngredientParametrizedTest {
    private Burger burger;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "Tomato", 14.0f));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "cutlet", 99.0f));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "mustard", 10.0f));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void removeIngredientRemovesIt(int index) {
        //сохраняю список ингредиентов
        List<Ingredient> beforeList = burger.getIngredients();
        //сохраняю размер списка
        int beforeSize = beforeList.size();
        //сохраняю ингредиент который буду удалять
        Ingredient ingredientToRemove = beforeList.get(index);
        //удалю ингредиент
        burger.removeIngredient(index);
        //сохраню список ингредиентов после удаления
        List<Ingredient> afterList = burger.getIngredients();

        //проверки
         assertEquals(beforeSize-1, afterList.size());
         assertFalse(afterList.contains(ingredientToRemove));
    }
}
