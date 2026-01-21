package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class DatabaseTest {

    @Mock
    private Database databaseMock;

    private final String expectedBunName;
    private final float expectedBunPrice;
    private final IngredientType expectedIngredientType;
    private final String expectedIngredientName;
    private final float expectedIngredientPrice;

    public DatabaseTest(String expectedBunName, float expectedBunPrice,
                        IngredientType expectedIngredientType, String expectedIngredientName,
                        float expectedIngredientPrice) {
        this.expectedBunName = expectedBunName;
        this.expectedBunPrice = expectedBunPrice;
        this.expectedIngredientType = expectedIngredientType;
        this.expectedIngredientName = expectedIngredientName;
        this.expectedIngredientPrice = expectedIngredientPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"black bun", 100.0f, IngredientType.SAUCE, "hot sauce", 100.0f},
                {"white bun", 200.0f, IngredientType.SAUCE, "sour cream", 200.0f},
                {"red bun", 300.0f, IngredientType.FILLING, "cutlet", 100.0f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void availableBunsReturnsNonEmptyList() {
        List<Bun> mockBuns = Arrays.asList(mock(Bun.class), mock(Bun.class), mock(Bun.class));
        when(databaseMock.availableBuns()).thenReturn(mockBuns);

        List<Bun> buns = databaseMock.availableBuns();
        assertFalse(buns.isEmpty());
    }

    @Test
    public void availableBunsReturnsListWithThreeElements() {
        List<Bun> mockBuns = Arrays.asList(mock(Bun.class), mock(Bun.class), mock(Bun.class));
        when(databaseMock.availableBuns()).thenReturn(mockBuns);

        List<Bun> buns = databaseMock.availableBuns();
        assertEquals(3, buns.size());
    }

    @Test
    public void availableIngredientsReturnsNonEmptyList() {
        List<Ingredient> mockIngredients = Arrays.asList(
                mock(Ingredient.class), mock(Ingredient.class), mock(Ingredient.class),
                mock(Ingredient.class), mock(Ingredient.class), mock(Ingredient.class)
        );
        when(databaseMock.availableIngredients()).thenReturn(mockIngredients);

        List<Ingredient> ingredients = databaseMock.availableIngredients();
        assertFalse(ingredients.isEmpty());
    }

    @Test
    public void availableIngredientsReturnsListWithSixElements() {
        List<Ingredient> mockIngredients = Arrays.asList(
                mock(Ingredient.class), mock(Ingredient.class), mock(Ingredient.class),
                mock(Ingredient.class), mock(Ingredient.class), mock(Ingredient.class)
        );
        when(databaseMock.availableIngredients()).thenReturn(mockIngredients);

        List<Ingredient> ingredients = databaseMock.availableIngredients();
        assertEquals(6, ingredients.size());
    }

    @Test
    public void databaseContainsExpectedBunName() {
        Bun mockBun = mock(Bun.class);
        when(mockBun.getName()).thenReturn(expectedBunName);
        when(mockBun.getPrice()).thenReturn(expectedBunPrice);

        List<Bun> mockBuns = Arrays.asList(mockBun, mock(Bun.class), mock(Bun.class));
        when(databaseMock.availableBuns()).thenReturn(mockBuns);

        List<Bun> buns = databaseMock.availableBuns();
        boolean found = buns.stream().anyMatch(bun -> bun.getName().equals(expectedBunName));

        assertTrue("Database should contain bun with name: " + expectedBunName, found);
    }

    @Test
    public void databaseContainsExpectedBunPrice() {
        Bun mockBun = mock(Bun.class);
        when(mockBun.getName()).thenReturn("some name");
        when(mockBun.getPrice()).thenReturn(expectedBunPrice);

        List<Bun> mockBuns = Arrays.asList(mockBun, mock(Bun.class), mock(Bun.class));
        when(databaseMock.availableBuns()).thenReturn(mockBuns);

        List<Bun> buns = databaseMock.availableBuns();
        boolean found = buns.stream().anyMatch(bun -> Math.abs(bun.getPrice() - expectedBunPrice) < 0.001);

        assertTrue("Database should contain bun with price: " + expectedBunPrice, found);
    }

    @Test
    public void databaseContainsExpectedIngredientName() {
        Ingredient mockIngredient = mock(Ingredient.class);
        when(mockIngredient.getName()).thenReturn(expectedIngredientName);
        when(mockIngredient.getType()).thenReturn(expectedIngredientType);
        when(mockIngredient.getPrice()).thenReturn(expectedIngredientPrice);

        List<Ingredient> mockIngredients = Arrays.asList(
                mockIngredient, mock(Ingredient.class), mock(Ingredient.class),
                mock(Ingredient.class), mock(Ingredient.class), mock(Ingredient.class)
        );
        when(databaseMock.availableIngredients()).thenReturn(mockIngredients);

        List<Ingredient> ingredients = databaseMock.availableIngredients();
        boolean found = ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals(expectedIngredientName));

        assertTrue("Database should contain ingredient with name: " + expectedIngredientName, found);
    }

    @Test
    public void databaseContainsExpectedIngredientType() {
        Ingredient mockIngredient = mock(Ingredient.class);
        when(mockIngredient.getName()).thenReturn("some name");
        when(mockIngredient.getType()).thenReturn(expectedIngredientType);
        when(mockIngredient.getPrice()).thenReturn(expectedIngredientPrice);

        List<Ingredient> mockIngredients = Arrays.asList(
                mockIngredient, mock(Ingredient.class), mock(Ingredient.class),
                mock(Ingredient.class), mock(Ingredient.class), mock(Ingredient.class)
        );
        when(databaseMock.availableIngredients()).thenReturn(mockIngredients);

        List<Ingredient> ingredients = databaseMock.availableIngredients();
        boolean found = ingredients.stream().anyMatch(ingredient -> ingredient.getType() == expectedIngredientType);

        assertTrue("Database should contain ingredient with type: " + expectedIngredientType, found);
    }

    @Test
    public void databaseContainsExpectedIngredientPrice() {
        Ingredient mockIngredient = mock(Ingredient.class);
        when(mockIngredient.getName()).thenReturn("some name");
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getPrice()).thenReturn(expectedIngredientPrice);

        List<Ingredient> mockIngredients = Arrays.asList(
                mockIngredient, mock(Ingredient.class), mock(Ingredient.class),
                mock(Ingredient.class), mock(Ingredient.class), mock(Ingredient.class)
        );
        when(databaseMock.availableIngredients()).thenReturn(mockIngredients);

        List<Ingredient> ingredients = databaseMock.availableIngredients();
        boolean found = ingredients.stream().anyMatch(ingredient ->
                Math.abs(ingredient.getPrice() - expectedIngredientPrice) < 0.001);

        assertTrue("Database should contain ingredient with price: " + expectedIngredientPrice, found);
    }
}