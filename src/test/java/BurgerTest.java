import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BurgerTest {
    private Burger burger;
    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockIngredient1;
    @Mock
    private Ingredient mockIngredient2;
    @Mock
    private Ingredient mockIngredient3;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }
    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }
    @Test
    public void testSetBunsWithNull() {
        burger.setBuns(null);
        assertNull(burger.bun);
    }
    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredient1, burger.ingredients.get(0));
    }
    @Test
    public void testAddMultipleIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        assertEquals(3, burger.ingredients.size());
        assertEquals(mockIngredient1, burger.ingredients.get(0));
        assertEquals(mockIngredient2, burger.ingredients.get(1));
        assertEquals(mockIngredient3, burger.ingredients.get(2));
    }
    @Test
    public void testAddNullIngredient() {
        burger.addIngredient(null);
        assertEquals(1, burger.ingredients.size());
        assertNull(burger.ingredients.get(0));
    }
    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        burger.removeIngredient(1);
        assertEquals(2, burger.ingredients.size());
        assertEquals(mockIngredient1, burger.ingredients.get(0));
        assertEquals(mockIngredient3, burger.ingredients.get(1));
    }
    @Test
    public void testRemoveFirstIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        burger.removeIngredient(0);
        assertEquals(2, burger.ingredients.size());
        assertEquals(mockIngredient2, burger.ingredients.get(0));
        assertEquals(mockIngredient3, burger.ingredients.get(1));
    }
    @Test
    public void testRemoveLastIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        burger.removeIngredient(2);
        assertEquals(2, burger.ingredients.size());
        assertEquals(mockIngredient1, burger.ingredients.get(0));
        assertEquals(mockIngredient2, burger.ingredients.get(1));
    }
    @Test
    public void testRemoveIngredientFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(0));
    }
    @ParameterizedTest
    @CsvSource({
            "0",
            "1",
            "2"
    })
    public void testRemoveIngredientWithValidIndexes(int index) {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        assertDoesNotThrow(() -> burger.removeIngredient(index));
        assertEquals(2, burger.ingredients.size());
    }
    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        burger.moveIngredient(0, 2);
        assertEquals(mockIngredient2, burger.ingredients.get(0));
        assertEquals(mockIngredient3, burger.ingredients.get(1));
        assertEquals(mockIngredient1, burger.ingredients.get(2));
    }
    @Test
    public void testMoveIngredientToSamePosition() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        burger.moveIngredient(1, 1);
        assertEquals(mockIngredient1, burger.ingredients.get(0));
        assertEquals(mockIngredient2, burger.ingredients.get(1));
        assertEquals(mockIngredient3, burger.ingredients.get(2));
    }
    @Test
    public void testMoveIngredientToBeginning() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        burger.moveIngredient(2, 0);
        assertEquals(mockIngredient3, burger.ingredients.get(0));
        assertEquals(mockIngredient1, burger.ingredients.get(1));
        assertEquals(mockIngredient2, burger.ingredients.get(2));
    }
    @Test
    public void testMoveIngredientToEnd() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        burger.moveIngredient(0, 2);
        assertEquals(mockIngredient2, burger.ingredients.get(0));
        assertEquals(mockIngredient3, burger.ingredients.get(1));
        assertEquals(mockIngredient1, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientWithInvalidIndexes() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(5, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(0, 10));
    }
    @Test
    public void testMoveIngredientFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(0, 1));
    }
    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 0",
            "0, 2",
            "2, 0"
    })
    public void testMoveIngredientWithDifferentPositions(int fromIndex, int toIndex) {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        Ingredient movedIngredient = burger.ingredients.get(fromIndex);
        burger.moveIngredient(fromIndex, toIndex);
        assertEquals(movedIngredient, burger.ingredients.get(toIndex));
    }
    @Test
    public void testGetPriceWithIngredients() {
        burger.setBuns(mockBun);
        when(mockBun.getPrice()).thenReturn(100.0f);
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        when(ingredient1.getPrice()).thenReturn(50.0f);
        when(ingredient2.getPrice()).thenReturn(75.0f);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        float expectedPrice = 100.0f * 2 + 50.0f + 75.0f;
        assertEquals(expectedPrice, burger.getPrice());
    }
    @Test
    public void testGetPriceWithNoIngredients() {
        burger.setBuns(mockBun);
        when(mockBun.getPrice()).thenReturn(100.0f);
        float expectedPrice = 200.0f;
        assertEquals(expectedPrice, burger.getPrice());
    }
    @Test
    public void testGetPriceWithNoBun() {
        assertThrows(NullPointerException.class, () -> burger.getPrice());
    }
    @Test
    public void testGetPriceWithZeroPriceIngredients() {
        burger.setBuns(mockBun);
        when(mockBun.getPrice()).thenReturn(0.0f);
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(0.0f);
        burger.addIngredient(ingredient);
        assertEquals(0.0f, burger.getPrice());
    }
    @Test
    public void testGetPriceWithNegativePrices() {
        burger.setBuns(mockBun);
        when(mockBun.getPrice()).thenReturn(-100.0f);
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(-50.0f);
        burger.addIngredient(ingredient);
        float expectedPrice = -100.0f * 2 + (-50.0f);
        assertEquals(expectedPrice, burger.getPrice());
    }
    @Test
    public void testGetPriceWithNullIngredients() {
        burger.setBuns(mockBun);
        when(mockBun.getPrice()).thenReturn(100.0f);
        burger.addIngredient(null);
        burger.addIngredient(null);
        assertThrows(NullPointerException.class, () -> burger.getPrice());
    }
    @Test
    public void testGetReceipt() {
        Bun realBun = new Bun("black bun", 100.0f);
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100.0f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 200.0f);
        burger.setBuns(realBun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("black bun"));
        assertTrue(receipt.contains("sauce"));
        assertTrue(receipt.contains("hot sauce"));
        assertTrue(receipt.contains("filling"));
        assertTrue(receipt.contains("cutlet"));
        assertTrue(receipt.contains("Price:"));
    }

    @Test
    public void testGetReceiptWithNoIngredients() {
        Bun realBun = new Bun("white bun", 200.0f);
        burger.setBuns(realBun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("white bun"));
        assertTrue(receipt.contains("Price:"));
        long countEqualsWithSpace = receipt.lines()
                .filter(line -> line.contains("= ") && !line.contains("(===="))
                .count();
        assertEquals(0, countEqualsWithSpace);
    }
    @Test
    public void testGetReceiptWithAllTypesOfIngredients() {
        Bun realBun = new Bun("red bun", 300.0f);
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "chili sauce", 100.0f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "dinosaur", 200.0f);
        Ingredient ingredient3 = new Ingredient(IngredientType.SAUCE, "sour cream", 150.0f);
        burger.setBuns(realBun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("red bun"));
        assertTrue(receipt.contains("sauce"));
        assertTrue(receipt.contains("chili sauce"));
        assertTrue(receipt.contains("filling"));
        assertTrue(receipt.contains("dinosaur"));
        assertTrue(receipt.contains("sour cream"));
        assertTrue(receipt.contains("Price:"));
    }
    @Test
    public void testGetReceiptWithNullBun() {
        burger.setBuns(null);
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "test sauce", 100.0f));
        assertThrows(NullPointerException.class, () -> burger.getReceipt());
    }
    @Test
    public void testGetReceiptWithNullIngredients() {
        Bun realBun = new Bun("black bun", 100.0f);
        burger.setBuns(realBun);
        burger.addIngredient(null);
        burger.addIngredient(null);
        assertThrows(NullPointerException.class, () -> burger.getReceipt());
    }
    @Test
    public void testCompleteBurgerWorkflow() {
        Bun bun = new Bun("black bun", 100.0f);
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100.0f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 200.0f);
        Ingredient ingredient3 = new Ingredient(IngredientType.SAUCE, "chili sauce", 50.0f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        assertEquals(3, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
        burger.moveIngredient(0, 2);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient3, burger.ingredients.get(1));
        assertEquals(ingredient1, burger.ingredients.get(2));
        burger.removeIngredient(1);
        assertEquals(2, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
        float expectedPrice = 100.0f * 2 + 200.0f + 100.0f;
        assertEquals(expectedPrice, burger.getPrice());
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("black bun"));
        assertTrue(receipt.contains("hot sauce"));
        assertTrue(receipt.contains("cutlet"));
        assertFalse(receipt.contains("chili sauce"));
        assertTrue(receipt.contains("Price:"));
    }

}