import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import org.junit.After;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    // Добавьте это поле
    private AutoCloseable closeable;

    @Before
    public void setUp() {
        // Замените initMocks на openMocks
        closeable = MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    // Добавьте метод tearDown для закрытия ресурсов
    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

    // Остальные тесты остаются без изменений
    @Test
    public void constructorShouldCreateEmptyIngredientsList() {
        assertNotNull("Ingredients list should not be null", burger.ingredients);
        assertTrue("Ingredients list should be empty", burger.ingredients.isEmpty());
    }

    @Test
    public void setBunsShouldSetBunCorrectly() {
        burger.setBuns(bun);
        assertEquals("Bun should be set correctly", bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(ingredient);
        assertTrue("Ingredient should be added to list", burger.ingredients.contains(ingredient));
        assertEquals("Ingredients list size should be 1", 1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldRemoveIngredientFromList() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertTrue("Ingredient should be removed from list", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientShouldMoveIngredientToNewIndex() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        assertEquals("First ingredient should be moved to index 1", ingredient1, burger.ingredients.get(1));
        assertEquals("Second ingredient should be moved to index 0", ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        float expectedPrice = 100f * 2 + 50f;
        assertEquals("Price should be calculated correctly", expectedPrice, burger.getPrice(), 0.01);
    }

    @Test
    public void getReceiptShouldReturnCorrectReceipt() {
        when(bun.getName()).thenReturn("black bun");
        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();
        assertTrue("Receipt should contain bun name", receipt.contains("(==== black bun ====)"));
        assertTrue("Receipt should contain ingredient", receipt.contains("= filling cutlet ="));
        assertTrue("Receipt should contain price", receipt.contains("Price: "));
    }
}
