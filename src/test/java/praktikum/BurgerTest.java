package praktikum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest extends BaseBurgerTest {

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals("Булочка не установлена в бургер", mockBun, burger.bun);
    }

    @Test
    public void testAddIngredientContainsElement() {
        burger.addIngredient(mockIngredientSauce);
        assertTrue("Список ингредиентов не содержит добавленный элемент", burger.ingredients.contains(mockIngredientSauce));

    }

    @Test
    public void testAddIngredientIncreasesSize() {
        burger.addIngredient(mockIngredientSauce);
        assertEquals("Размер списка ингридиентов не увеличился до 1", 1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredientSauce);
        burger.removeIngredient(0);
        assertTrue("Список ингредиентов не стал пустым после удаления элемента", burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredientFirstToSecondPosition() {
        burger.addIngredient(mockIngredientSauce);
        burger.addIngredient(mockIngredientFilling);

        burger.moveIngredient(0, 1);

        assertEquals("На индексе 0 теперь должен находиться filling", mockIngredientFilling, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientSecondToFirstPosition() {
        burger.addIngredient(mockIngredientSauce);
        burger.addIngredient(mockIngredientFilling);

        burger.moveIngredient(0, 1);

        assertEquals("На индексе 1 теперь должен находиться sauce", mockIngredientSauce, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);
        Mockito.when(mockIngredientSauce.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientSauce);

        float expectedPrice = (100.0f * 2) + 50.0f;
        assertEquals("Расчет итоговой стоимости бургера выполнен неверно", expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceiptStrict() {

        Mockito.when(mockBun.getName()).thenReturn("Краторная булка");
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);

        Mockito.when(mockIngredientSauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredientSauce.getName()).thenReturn("Чили");
        Mockito.when(mockIngredientSauce.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientSauce);

        String expectedReceipt = String.format(
            "(==== %s ====)%n" +
                    "= %s %s =%n" +
                    "(==== %s ====)%n" +
                    "%nPrice: %f%n",
            "Краторная булка",
            "sauce", "Чили",
            "Краторная булка",
            250.0f
        );

        assertEquals("Структура чека или итоговая стоимость не совпадают", expectedReceipt, burger.getReceipt());
    }
}
