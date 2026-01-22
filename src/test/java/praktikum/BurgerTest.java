package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsSetsTheBun() {
        burger.setBuns(bun);
        assertEquals("Булка не установилась корректно", bun, burger.bun);
    }

    @Test
    public void addIngredientIncreasesListSize() {
        burger.addIngredient(sauce);
        assertEquals("Размер списка ингредиентов должен увеличиться", 1, burger.ingredients.size());
    }

    @Test
    public void addIngredientAddsCorrectIngredient() {
        burger.addIngredient(sauce);
        assertEquals("Добавленный ингредиент не совпадает", sauce, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientDecreasesListSize() {
        burger.addIngredient(sauce);
        burger.removeIngredient(0);
        assertTrue("Список ингредиентов должен быть пуст после удаления", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientChangesFirstItem() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(1, 0);

        assertEquals("Первым элементом должен стать filling", filling, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientChangesSecondItem() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(1, 0);

        assertEquals("Вторым элементом должен стать sauce", sauce, burger.ingredients.get(1));
    }

    @Test
    public void getReceiptContainsBunName() {
        when(bun.getName()).thenReturn("Space Bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        assertTrue("Чек должен содержать имя булки", burger.getReceipt().contains("Space Bun"));
    }

    @Test
    public void getReceiptContainsIngredientType() {
        setupMockForReceipt();
        assertTrue("Чек должен содержать тип ингредиента", burger.getReceipt().contains("sauce"));
    }

    @Test
    public void getReceiptContainsIngredientName() {
        setupMockForReceipt();
        assertTrue("Чек должен содержать название ингредиента", burger.getReceipt().contains("Space Sauce"));
    }

    @Test
    public void getReceiptContainsPrice() {
        setupMockForReceipt();
        assertTrue("Чек должен содержать итоговую цену", burger.getReceipt().contains("250,000000"));
    }

    private void setupMockForReceipt() {
        when(bun.getName()).thenReturn("Space Bun");
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("Space Sauce");
        when(sauce.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
    }
}