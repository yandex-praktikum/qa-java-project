import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Mock
    private Ingredient secondIngredient;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(bun);

        assertEquals("Булочка должна быть установлена", bun, burger.bun);
    }

    @Test
    public void addIngredientShouldIncreaseIngredientsSize() {
        burger.addIngredient(ingredient);

        assertEquals("Размер списка ингредиентов должен увеличиться", 1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddCorrectIngredient() {
        burger.addIngredient(ingredient);

        assertEquals("Добавлен неверный ингредиент", ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldDecreaseIngredientsSize() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        assertEquals("Размер списка ингредиентов должен уменьшиться", 0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientShouldChangeIngredientPosition() {
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);

        assertEquals("Ингредиент должен переместиться на новую позицию", ingredient, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientShouldKeepOtherIngredients() {
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);

        assertEquals("Второй ингредиент должен остаться в списке", secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void getReceiptShouldContainBunName() {
        Mockito.when(bun.getName()).thenReturn("original");
        Mockito.when(bun.getPrice()).thenReturn(200.0f);

        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать название булочки", receipt.contains("original"));
    }

    @Test
    public void getReceiptShouldContainIngredientName() {
        Mockito.when(bun.getName()).thenReturn("original");
        Mockito.when(bun.getPrice()).thenReturn(200.0f);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("chili");
        Mockito.when(ingredient.getPrice()).thenReturn(20.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать название ингредиента", receipt.contains("chili"));
    }

    @Test
    public void getReceiptShouldNotBeEmpty() {
        Mockito.when(bun.getName()).thenReturn("original");
        Mockito.when(bun.getPrice()).thenReturn(200.0f);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("chili");
        Mockito.when(ingredient.getPrice()).thenReturn(20.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String receipt = burger.getReceipt();

        assertTrue("Чек не должен быть пустым", receipt != null && !receipt.isEmpty());
    }
}