import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class BurgerGetReceiptTests {

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient1 = mock(Ingredient.class);
    @Mock
    Ingredient ingredient2 = mock(Ingredient.class);;

    private static final String BUN_NAME = "Краторная булка";
    private static final float BUN_PRICE = 0;
    private static final String INGREDIENT_1_NAME = "Spicy-X";
    private static final IngredientType INGREDIENT_1_TYPE = IngredientType.SAUCE;
    private static final float INGREDIENT_1_PRICE = 0;
    private static final String INGREDIENT_2_NAME = "Говяжий метеорит";
    private static final IngredientType INGREDIENT_2_TYPE = IngredientType.FILLING;
    private static final float INGREDIENT_2_PRICE = 0;
    private static final String EXPECTED_RECEIPT = "(==== Краторная булка ====)" + System.lineSeparator()
            + "= sauce Spicy-X =" + System.lineSeparator()
            + "= filling Говяжий метеорит =" + System.lineSeparator()
            + "(==== Краторная булка ====)" + System.lineSeparator()
            + System.lineSeparator() + "Price: 0,000000"  + System.lineSeparator();

    private Burger burger;

    @BeforeEach
    public void setUp() {
        Mockito.when(bun.getName()).thenReturn(BUN_NAME);
        Mockito.when(bun.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(ingredient1.getName()).thenReturn(INGREDIENT_1_NAME);
        Mockito.when(ingredient2.getName()).thenReturn(INGREDIENT_2_NAME);
        Mockito.when(ingredient1.getType()).thenReturn(INGREDIENT_1_TYPE);
        Mockito.when(ingredient2.getType()).thenReturn(INGREDIENT_2_TYPE);
        Mockito.when(ingredient1.getPrice()).thenReturn(INGREDIENT_1_PRICE);
        Mockito.when(ingredient2.getPrice()).thenReturn(INGREDIENT_2_PRICE);

        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
    }

    @Test
    public void getReceiptPositiveTest(){
        String actualReceipt = burger.getReceipt();
        assertEquals(EXPECTED_RECEIPT, actualReceipt,
                String.format("Burger receipt was expected to be:\n%s\nBut actually was:\n%s", EXPECTED_RECEIPT, actualReceipt));
    }
}
