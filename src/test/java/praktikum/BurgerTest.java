package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockSauceIngredient;
    @Mock
    private Ingredient mockFillingIngredient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100.0f);

        burger.setBuns(mockBun);
        assertSame(mockBun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        when(mockSauceIngredient.getName()).thenReturn("cutlet");
        when(mockSauceIngredient.getPrice()).thenReturn(150.0f);
        when(mockSauceIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(mockSauceIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientSizeIsCorrectTest() {
        when(mockSauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockFillingIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(mockSauceIngredient);
        burger.addIngredient(mockFillingIngredient);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientElementIsShiftedTest() {
        when(mockSauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockFillingIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(mockSauceIngredient);
        burger.addIngredient(mockFillingIngredient);

        burger.removeIngredient(0);

        assertSame(mockFillingIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientFirstElementMovedTest() {
        when(mockSauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockFillingIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(mockSauceIngredient);
        burger.addIngredient(mockFillingIngredient);

        burger.moveIngredient(0, 1); // move index 0 to 1

        assertEquals(mockFillingIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientSecondElementsMovedTest() {
        when(mockSauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockFillingIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(mockSauceIngredient);
        burger.addIngredient(mockFillingIngredient);

        burger.moveIngredient(0, 1); // move index 0 to 1

        assertEquals(mockSauceIngredient, burger.ingredients.get(1));
    }
    @Test
    public void generateCorrectReceiptWithIngredientsTest() {
        when(mockBun.getName()).thenReturn("black bun");

        when(mockSauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauceIngredient.getName()).thenReturn("hot sauce");

        when(mockFillingIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(mockFillingIngredient.getName()).thenReturn("dinosaur");

        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockSauceIngredient.getPrice()).thenReturn(100.0f);
        when(mockFillingIngredient.getPrice()).thenReturn(200.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauceIngredient);
        burger.addIngredient(mockFillingIngredient);

        String expected = String.format("(==== black bun ====)%n" +
                "= sauce hot sauce =%n" +
                "= filling dinosaur =%n" +
                "(==== black bun ====)%n" +
                "%n" +
                "Price: 500,000000%n");

        String actual = burger.getReceipt();

        assertEquals(expected, actual);
    }

    @Test
    public void generateReceiptWithNoIngredientsTest() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(200.0f);

        burger.setBuns(mockBun);

        String expected = String.format("(==== white bun ====)%n" +
                "(==== white bun ====)%n" +
                "%n" +
                "Price: 400,000000%n");

        String actual = burger.getReceipt();

        assertEquals(expected, actual);
    }

}