package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptParameterizedTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    private Burger burger;

    private final IngredientType type;
    private final String typeName;
    private final String ingredientName;

    public BurgerReceiptParameterizedTest(IngredientType type, String typeName,String ingredientName) {
        this.type = type;
        this.typeName = typeName;
        this.ingredientName = ingredientName;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2}")
    public static Object[][] data() {
        return new Object[][] {
                {IngredientType.SAUCE, "sauce", "hot sauce"},
                {IngredientType.FILLING, "filling", "cutlet"}
        };
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Test
    public void testGetReceiptWithIngredientType() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(50f);
        when(ingredient.getType()).thenReturn(type);
        when(ingredient.getName()).thenReturn(ingredientName);
        when(ingredient.getPrice()).thenReturn(200f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt).contains("black bun");
        softly.assertThat(receipt).contains(typeName);
        softly.assertThat(receipt).contains(ingredientName);
        softly.assertThat(receipt).contains("300");
        softly.assertAll();
    }
}
