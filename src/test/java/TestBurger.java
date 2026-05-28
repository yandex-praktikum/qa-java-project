import jdk.jfr.Description;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestBurger {

    @Test
    public void testBurgerSetBuns() {
        Bun bunMock = mock(Bun.class);
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        assertEquals(burger.bun, bunMock);
    }

    @Test
    public void testBurgerAddIngredient() {
        Ingredient ingredientMock = mock(Ingredient.class);
        Burger burger = new Burger();
        burger.addIngredient(ingredientMock);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testBurgerRemoveIngredients() {
        Ingredient ingredientMock = mock(Ingredient.class);
        Burger burger = new Burger();
        burger.ingredients.add(ingredientMock);
        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testBurgerMoveIngredients() {
        Ingredient ingredientOne = new Ingredient(IngredientType.FILLING, "cheese", 30);
        Ingredient ingredientTwo = new Ingredient(IngredientType.SAUCE, "ketchup", 5);
        Burger burger = new Burger();

        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);

        burger.moveIngredient(1, 0); // Перемещаем ingredientTwo на позицию ingredientOne
        assertEquals(burger.ingredients.get(0), ingredientTwo);
        assertEquals(burger.ingredients.get(1), ingredientOne);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testBurgerGetPrice() {
        Ingredient ingredientMock = mock(Ingredient.class);
        float priceIngredientMock = 20f;
        when(ingredientMock.getPrice()).thenReturn(priceIngredientMock);

        Bun bunMock = mock(Bun.class);
        float priceBunMock = 5f;
        when(bunMock.getPrice()).thenReturn(priceBunMock);

        Burger burger = new Burger();
        burger.bun = bunMock;
        burger.ingredients.add(ingredientMock);

        float expectedPriceBurger = priceBunMock * 2 + priceIngredientMock;

        assertEquals(expectedPriceBurger, burger.getPrice(), 0.01);
    }
}
