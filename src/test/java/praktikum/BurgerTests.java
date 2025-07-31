package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {
    private Burger burger;

    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient ingredientMock;
    @Mock
    private Ingredient newIngredientMock;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    //Проверка булочку в бургере
    @Test
    public void setBunsTest() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    //Проверка ингридиента в бургере
    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredientMock);
        assertTrue("Ingredient add", burger.ingredients.contains(ingredientMock));
    }

    //Проверка на удаление ингридиента
    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertEquals("List must not contain a remote ingredient", 0, burger.ingredients.size());
    }

    //Проверка перемещения слоя ингридиента
    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.addIngredient(newIngredientMock);
        burger.moveIngredient(0, 1);
        assertEquals(List.of(newIngredientMock, ingredientMock), burger.ingredients);
    }

    //Проверка расчета цены
    @Test
    public void checkGetPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(newIngredientMock);

        when(mockBun.getPrice()).thenReturn(30f);
        when(ingredientMock.getPrice()).thenReturn(10f);
        when(newIngredientMock.getPrice()).thenReturn(2f);

        float expectedPrice = (30f * 2) + 10f + 2f;
        assertEquals("Incorrect price", expectedPrice, burger.getPrice(), 0);
    }

    //Проверка печати чека
    @Test
    public void checkGetReceipt() {
        burger.setBuns(mockBun);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(newIngredientMock);

        when(mockBun.getName()).thenReturn("bun");
        when(mockBun.getPrice()).thenReturn(30f);

        when(ingredientMock.getName()).thenReturn("Spicy-X");
        when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock.getPrice()).thenReturn(10f);

        when(newIngredientMock.getName()).thenReturn("Beef meteorite");
        when(newIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(newIngredientMock.getPrice()).thenReturn(2f);

        String expected= String.format("(==== bun ====)%n"
                + "= sauce Spicy-X =%n"
                + "= filling Beef meteorite =%n"
                + "(==== bun ====)%n"
                + "%n"
                + "Price: 72.00%n");
        Assert.assertEquals("Incorrect check", expected, burger.getReceipt());
    }
}
