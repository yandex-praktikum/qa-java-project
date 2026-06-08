package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockSauce;

    @Mock
    private Ingredient mockFilling;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBunsSetsFieldCorrectly() {
        burger.setBuns(mockBun);
        Assert.assertEquals("Булочка должна успешно установиться в бургер", mockBun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesListSize() {
        burger.addIngredient(mockSauce);
        Assert.assertEquals("Размер списка ингредиентов должен быть равен 1", 1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectElementToList() {
        burger.addIngredient(mockSauce);
        Assert.assertEquals("В списке должен находиться именно добавленный ингредиент", mockSauce, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientDecreasesListSize() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        burger.removeIngredient(0);

        Assert.assertEquals("После удаления одного элемента размер списка должен быть равен 1", 1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientLeavesCorrectElement() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        burger.removeIngredient(0);

        Assert.assertEquals("Оставшимся элементом должен быть mockFilling", mockFilling, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientDoesNotChangeListSize() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        burger.moveIngredient(0, 1);

        Assert.assertEquals("Размер списка не должен измениться при перемещении", 2, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientPutsCorrectElementToFirstPosition() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        burger.moveIngredient(0, 1);

        Assert.assertEquals("На индексе 0 теперь должен быть mockFilling", mockFilling, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientPutsCorrectElementToSecondPosition() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        burger.moveIngredient(0, 1);

        Assert.assertEquals("На индексе 1 теперь должен быть mockSauce", mockSauce, burger.ingredients.get(1));
    }

    @Test
    public void testGetPriceCalculatesCorrectTotalPrice() {
        Mockito.when(mockBun.getPrice()).thenReturn(120.0f);
        Mockito.when(mockSauce.getPrice()).thenReturn(50.0f);
        Mockito.when(mockFilling.getPrice()).thenReturn(30.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        float expectedPrice = 320.0f;
        Assert.assertEquals("Итоговая цена бургера рассчитана неверно", expectedPrice, burger.getPrice(), 0.0f);
    }
}
