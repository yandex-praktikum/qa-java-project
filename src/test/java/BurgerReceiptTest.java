import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private final String bunName;
    private final IngredientType ingredientType;
    private final String ingredientName;
    private final String expectedReceipt;


    public BurgerReceiptTest(String bunName, IngredientType ingredientType, String ingredientName,
                             String expectedReceipt) {
        this.bunName = bunName;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.expectedReceipt = expectedReceipt;
    }
    @Parameterized.Parameters(name = "Тест {index}: чек для бургера с булочкой ''{0}'' и ингредиентом {1} ''{2}''")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        "черная булочка",
                        IngredientType.SAUCE,
                        "острый соус",
                        "(==== черная булочка ====)\n= sauce острый соус =\n(==== черная булочка ====)" +
                                "\n\nPrice: 250,000000\n"
                },
                {
                        "белая булочка",
                        IngredientType.FILLING,
                        "котлета",
                        "(==== белая булочка ====)\n= filling котлета =\n(==== белая булочка ====)" +
                                "\n\nPrice: 250,000000\n"
                },
                {
                        "красная булочка",
                        IngredientType.SAUCE,
                        "чили соус",
                        "(==== красная булочка ====)\n= sauce чили соус =\n(==== красная булочка ====)" +
                                "\n\nPrice: 250,000000\n"
                },
                {
                        "особая булочка",
                        IngredientType.FILLING,
                        "сыр",
                        "(==== особая булочка ====)\n= filling сыр =\n(==== особая булочка ====)" +
                                "\n\nPrice: 250,000000\n"
                }
        });
    }

    @Test
    public void testGetReceiptReturnsCorrectFormat() {
        // Arrange
        Burger burger = new Burger();

        Bun bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn(bunName);
        when(bunMock.getPrice()).thenReturn(100f);

        Ingredient ingredientMock = mock(Ingredient.class);
        when(ingredientMock.getType()).thenReturn(ingredientType);
        when(ingredientMock.getName()).thenReturn(ingredientName);
        when(ingredientMock.getPrice()).thenReturn(50f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        // Act
        String actualReceipt = burger.getReceipt();

        // Assert - проверка формата чека целиком
        assertEquals("Чек должен полностью соответствовать ожидаемому формату",
                expectedReceipt, actualReceipt);
    }

}
