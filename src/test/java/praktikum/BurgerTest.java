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
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

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
    public void testAddIngredientAddsToElementsList() {
        burger.addIngredient(mockIngredient1);
        Assert.assertEquals("Размер списка ингредиентов должен быть равен 1", 1, burger.ingredients.size());
        Assert.assertEquals("В списке должен находиться именно добавленный ингредиент", mockIngredient1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientRemovesCorrectElement() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0); // Удаляем mockIngredient1

        Assert.assertEquals("После удаления одного элемента размер списка должен быть равен 1", 1, burger.ingredients.size());
        Assert.assertEquals("Оставшимся элементом должен быть mockIngredient2", mockIngredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientChangesOrderInList() {
        burger.addIngredient(mockIngredient1); // Индекс 0
        burger.addIngredient(mockIngredient2); // Индекс 1

        burger.moveIngredient(0, 1); // Перемещаем с 0 на 1

        Assert.assertEquals("Размер списка не должен измениться при перемещении", 2, burger.ingredients.size());
        Assert.assertEquals("На индексе 0 теперь должен быть mockIngredient2", mockIngredient2, burger.ingredients.get(0));
        Assert.assertEquals("На индексе 1 теперь должен быть mockIngredient1", mockIngredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPriceCalculatesCorrectTotalPrice() {
        // Настраиваем стабы для возврата цен
        Mockito.when(mockBun.getPrice()).thenReturn(120.0f);
        Mockito.when(mockIngredient1.getPrice()).thenReturn(50.0f);
        Mockito.when(mockIngredient2.getPrice()).thenReturn(30.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Расчет: (120.0 * 2) + 50.0 + 30.0 = 320.0
        float expectedPrice = 320.0f;
        Assert.assertEquals("Итоговая цена бургера рассчитана неверно", expectedPrice, burger.getPrice(), 0.0f);
    }
}
