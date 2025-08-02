import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BurgerTests {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(ingredient1);
        assertEquals("Колличество ингредиентов не совпадает", 1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveIngredientFromList() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertEquals("Колличество ингредиентов не уменшьилось", 1, burger.ingredients.size());
        assertEquals("После удаления остался не тот ингредиент", ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldChangeIngredientPosition() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals("Ингридиент не поменял свое место", ingredient2, burger.ingredients.get(0));
        assertEquals("Ингридиент не поменял свое место", ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        when(bun.getPrice()).thenReturn(5.0f);
        when(ingredient1.getPrice()).thenReturn(3.0f);
        when(ingredient2.getPrice()).thenReturn(2.0f);

        float expectedPrice = 5.0f * 2 + 3.0f + 2.0f;

        assertEquals("Сумма отличается от ожидаемой", expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void getReceiptShouldReturnCorrectReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        when(bun.getName()).thenReturn("White bun");
        when(bun.getPrice()).thenReturn(5.0f);

        when(ingredient1.getName()).thenReturn("Lettuce");
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient1.getPrice()).thenReturn(1.5f);

        String expectedReceipt = String.format(
                "(==== White bun ====)%n" +
                        "= filling Lettuce =%n" +
                        "(==== White bun ====)%n" +
                        "%nPrice: %f%n",
                5.0f * 2 + 1.5f
        );

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
