package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        when(bun.getPrice()).thenReturn(100.0f);
        when(ingredient1.getPrice()).thenReturn(50.0f);
        when(ingredient2.getPrice()).thenReturn(75.0f);

        float expectedPrice = 100.0f * 2 + 50.0f + 75.0f;
        assertEquals(expectedPrice, burger.getPrice());
    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();
        Bun bun = new Bun("Test Bun", 100.0f);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Test Sauce", 50.0f);
        Ingredient filling = new Ingredient(IngredientType.FILLING, "Test Filling", 0.0f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();
        String[] lines = receipt.split("\r?\n");

        assertEquals("(==== Test Bun ====)", lines[0]);
        assertEquals("(==== Test Bun ====)", lines[lines.length - 3]);
        assertEquals("", lines[lines.length - 2]);
        assertTrue(lines[lines.length - 1].startsWith("Price: "));

        List<String> middleLines = Arrays.asList(lines).subList(1, lines.length - 3);
        assertTrue(middleLines.contains("= sauce Test Sauce ="));
        assertTrue(middleLines.contains("= filling Test Filling ="));
        assertEquals(2, middleLines.size());

        float expectedPrice = 250.0f;
        assertTrue(lines[lines.length - 1].contains(String.format("%.6f", expectedPrice)));

    }
}