import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
        burger.setBuns(bun);
    }

    @Test
    public void setBunsSetsBunField() {
        Bun newBun = new Bun("white bun", 200f);
        burger.setBuns(newBun);
        assertSame(newBun, burger.bun);
    }

    @Test
    public void addIngredientAddsIngredientToList() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ketchup", 10f);
        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientRemovesIngredientFromList() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "mustard", 5f);
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientChangesOrderOfIngredients() {
        Ingredient first = new Ingredient(IngredientType.SAUCE, "ketchup", 10f);
        Ingredient second = new Ingredient(IngredientType.FILLING, "cutlet", 50f);
        Ingredient third = new Ingredient(IngredientType.SAUCE, "mustard", 5f);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);
        burger.moveIngredient(0, 2);

        assertSame(second, burger.ingredients.get(0));
        assertSame(third, burger.ingredients.get(1));
        assertSame(first, burger.ingredients.get(2));
    }

    @Test
    public void getPriceReturnsSumOfBunsAndIngredients() {
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient1.getPrice()).thenReturn(10f);
        when(ingredient2.getPrice()).thenReturn(20f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float price = burger.getPrice();

        assertEquals(100f * 2 + 10f + 20f, price, 0.0001f);
    }

    @Test
    public void getReceiptReturnsFormattedString() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(10f);

        burger.addIngredient(sauce);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен начинаться с булочки",
                receipt.startsWith("(==== black bun ====)\n"));
        assertTrue("Чек должен содержать ингредиент",
                receipt.contains("\n= sauce hot sauce =\n"));
        assertTrue("Чек должен заканчиваться булочкой и ценой",
                receipt.contains("\n(==== black bun ====)\n"));
        assertTrue("Чек должен содержать цену",
                receipt.contains("\nPrice: "));

        int firstBunIndex = receipt.indexOf("(==== black bun ====)");
        int ingredientIndex = receipt.indexOf("= sauce hot sauce =");
        int secondBunIndex = receipt.lastIndexOf("(==== black bun ====)");

        assertTrue("Ингредиент должен быть между булочками",
                firstBunIndex < ingredientIndex && ingredientIndex < secondBunIndex);
    }
}