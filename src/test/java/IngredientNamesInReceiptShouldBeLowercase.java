import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class IngredientNamesInReceiptShouldBeLowercase {

    private AutoCloseable closeable;

    private final IngredientType type;
    private final String ingredient ;
    private final String expectedVisual;

    public IngredientNamesInReceiptShouldBeLowercase(IngredientType type, String ingredient , String expectedVisual) {

        this.type = type;
        this.ingredient  = ingredient ;
        this.expectedVisual = expectedVisual;
    }


    @Parameterized.Parameters(name = "тип={0}, название={1}, итог={2}")
    public static Object[][] getIngredientData() {
        return new Object[][]{
                {IngredientType.SAUCE, "my sauce", "= sauce my sauce ="},
                {IngredientType.FILLING, "my cutlet", "= filling my cutlet ="}
        };
    }

    @Before
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredientSauce;


    @Test
    public void correctReceipt(){

        Burger burger = new Burger();

        Mockito.when(ingredientSauce.getType()).thenReturn(type);
        Mockito.when(ingredientSauce.getName()).thenReturn(ingredient);

        burger.setBuns(bun);
        burger.addIngredient(ingredientSauce);

        String actualVisual = burger.getReceipt();
        assertTrue(expectedVisual, actualVisual.contains(expectedVisual));
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }
}
