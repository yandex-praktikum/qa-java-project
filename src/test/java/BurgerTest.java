import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    private Burger burger;
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        burger = new Burger();
        bun = new Bun("булочка", 100.0f);
        burger.setBuns(bun);
    }

    @ParameterizedTest
    @CsvSource({
            "FILLING, cutlet, 100.0",
            "SAUCE, hot sauce, 100.0",
            "FILLING, dinosaur, 200.0",
            "SAUCE, sour cream, 15.0",
            "FILLING, sausage, 300.0",
            "SAUCE, chili sauce, 300.0"
    })
    void testAddIngredient(String typeStr, String name, float price) {

        IngredientType type = IngredientType.valueOf(typeStr);

        Mockito.when(ingredient.getType()).thenReturn(type);
        Mockito.when(ingredient.getName()).thenReturn(name);
        Mockito.when(ingredient.getPrice()).thenReturn(price);

        burger.addIngredient(ingredient);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice());
    }

    @Test
    void testRemoveIngredient() {
        Ingredient cutlet = new Ingredient(IngredientType.FILLING, "cutlet", 100.0f);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "hot sauce", 100.0f);

        burger.addIngredient(cutlet);
        burger.addIngredient(sauce);
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals("hot sauce", burger.ingredients.get(0).getName());
    }

    @Test
    void testMoveIngredient() {
        Ingredient cutlet = new Ingredient(IngredientType.FILLING, "cutlet", 100.0f);
        Ingredient dinosaur = new Ingredient(IngredientType.FILLING, "dinosaur", 200.0f);

        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);

        burger.moveIngredient(0, 1);

        assertEquals("dinosaur", burger.ingredients.get(0).getName());
        assertEquals("cutlet", burger.ingredients.get(1).getName());
    }

    @Test
    void testGetPrice() {
        burger.addIngredient(ingredient);
        float totalPrice = burger.getPrice();
        assertEquals(200.0f, totalPrice);
    }

    @Test
    void testGetReceipt() {
        Mockito.when(ingredient.getName()).thenReturn("cutlet");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(ingredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== булочка ====)"));
        assertTrue(receipt.contains("= filling cutlet ="));
        assertTrue(receipt.contains("Price:"));
    }
}