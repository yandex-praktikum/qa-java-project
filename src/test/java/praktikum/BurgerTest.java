package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(firstIngredient);
        assertEquals(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(firstIngredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        burger.ingredients.add(firstIngredient);
        burger.ingredients.add(secondIngredient);
        burger.moveIngredient(0, 1);
        assertEquals(burger.ingredients.get(0), secondIngredient);
    }

    @Test
    public void getPriceTest() {
        float expectedPrice = 56;
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(firstIngredient);

        when(bun.getPrice()).thenReturn(9f);
        when(firstIngredient.getPrice()).thenReturn(19f);

        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {
        Bun bun = new Bun("Шавуха космос", 111);
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "метеоритный соус", 117);
        Ingredient salad = new Ingredient(IngredientType.FILLING, "кольца Сатурна", 23);

        burger.setBuns(bun);
        burger.ingredients.add(ingredient);
        burger.ingredients.add(salad);

        String actual = burger.getReceipt();
        String expected = String.format("(==== Шавуха космос ====)\r\n= sauce метеоритный соус " +
                "=\r\n= filling кольца Сатурна =\r\n" +
                "(==== Шавуха космос ====)\r\n\r\nPrice: 362,000000\r\n");

        assertEquals(actual, expected);
    }
}

