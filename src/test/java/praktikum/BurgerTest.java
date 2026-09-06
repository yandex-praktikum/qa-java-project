package praktikum;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

public class BurgerTest {

    @DisplayName("Добавление булочки в бургер")
    @Description("Проверяет, что метод setBuns() добавляет булочку в бургер")
    @Test
    void setBunsSetsBurgerBun(){
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @DisplayName("Добавление ингредиента в бургер")
    @Description("Проверяет, что метод addIngredient() добавляет переданный ингредиент в список ингредиентов бургера")
    @Test
    void addIngredientAddsIngredientToBurger(){
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient, burger.ingredients.get(0));
    }
}
