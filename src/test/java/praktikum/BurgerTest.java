package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger;

    @Mock
    Bun mockBun;

    @Mock
    Ingredient mockIngredientsOne;

    @Mock
    Ingredient mockIngredientsTwo;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest(){
        burger.setBuns(mockBun);

        assertEquals("Булка не создана",mockBun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(mockIngredientsOne);

        assertEquals("Ингредиент не добавлен",mockIngredientsOne, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(mockIngredientsOne);
        burger.addIngredient(mockIngredientsTwo);

        burger.removeIngredient(0);

        assertEquals("Не удален ингредиент",mockIngredientsTwo, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(mockIngredientsOne);
        burger.addIngredient(mockIngredientsTwo);

        burger.moveIngredient(0, 1);

        assertEquals("Позиция ингредиента не изменена",mockIngredientsTwo, burger.ingredients.get(0));
    }
}