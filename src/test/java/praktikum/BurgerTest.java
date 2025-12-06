package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Ingredient ingredient1;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    // ... твои остальные тесты ...

    @Test
    public void getReceiptContainsBunIngredientAndTotalPrice() {
        Bun bun = new Bun("white bun", 100f);
        burger.setBuns(bun);

        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient1.getPrice()).thenReturn(50f);

        burger.addIngredient(ingredient1);

        String receipt = burger.getReceipt();

        // проверяем, что в чеке есть булка, ингредиент и итоговая цена
        assertTrue(receipt.contains("white bun"));
        assertTrue(receipt.contains("hot sauce"));
        assertTrue(receipt.contains("250.0")); // 100 * 2 + 50
    }
}