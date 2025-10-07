import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptFormatTest {

    @Mock
    private Bun bun;
    @Mock
    private Ingredient sauceIngredient;
    @Mock
    private Ingredient fillingIngredient;
    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();

    }

    @Test
    public void testBunFormat() {
        String expectedName = "Краторная булка";
        String expectedBunLine = "(==== " + expectedName + " ====)";
        when(bun.getName()).thenReturn(expectedName);
        burger.setBuns(bun);

        String receipt = burger.getReceipt();

        assertTrue("Булка должна быть в формате (==== name ====)",
                receipt.contains(expectedBunLine));
    }

    @Test
    public void testSauceIngredientFormat() {

        String expectedSauceName = "Соус";
        String expectedSauceLine = "= sauce " + expectedSauceName + " =";


        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getName()).thenReturn(expectedSauceName);


        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);


        String receipt = burger.getReceipt();


        assertTrue("Соус должен быть в формате = type name =",
                receipt.contains(expectedSauceLine));


    }

    @Test
    public void testSauceIngredientLowcase() {


        String forbiddenSauceType = "SAUCE";


        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);


        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);


        String receipt = burger.getReceipt();


        assertFalse("Типы должны быть в нижнем регистре, без SAUCE",
                receipt.contains(forbiddenSauceType));

    }


    @Test
    public void testFillingIngredientFormat() {


        String expectedFillingName = "Говяжий метеорит";
        String expectedFillingLine = "= filling " + expectedFillingName + " =";


        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredient.getName()).thenReturn(expectedFillingName);

        burger.setBuns(bun);
        burger.addIngredient(fillingIngredient);

        String receipt = burger.getReceipt();


        assertTrue("Начинка должна быть в формате = type name =",
                receipt.contains(expectedFillingLine));


    }


    @Test
    public void testFillingIngredientLowCase() {


        String forbiddenFillingType = "FILLING";


        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(bun);
        burger.addIngredient(fillingIngredient);

        String receipt = burger.getReceipt();


        assertFalse("Типы должны быть в нижнем регистре, без FILLING",
                receipt.contains(forbiddenFillingType));
    }


    @Test
    public void testPriceFormat() {
        String expectedName = "Краторная булка";
        String expectedPriceLine = "Price: 200,000000";
        when(bun.getName()).thenReturn(expectedName);
        when(bun.getPrice()).thenReturn(100.0f);

        burger.setBuns(bun);


        String receipt = burger.getReceipt();


        assertTrue("Цена должна быть в формате 'Price: 200,000000'",
                receipt.contains(expectedPriceLine));
    }

    @Test
    public void testCompleteReceiptFormat() {

        when(bun.getName()).thenReturn("Краторная булка");
        when(bun.getPrice()).thenReturn(100.0f);

        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getName()).thenReturn("Соус");
        when(sauceIngredient.getPrice()).thenReturn(50.0f);

        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredient.getName()).thenReturn("Говяжий метеорит");
        when(fillingIngredient.getPrice()).thenReturn(300.0f);

        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);


        String receipt = burger.getReceipt();


        String expectedReceipt = String.format(
                "(==== Краторная булка ====)%n" +
                        "= sauce Соус =%n" +
                        "= filling Говяжий метеорит =%n" +
                        "(==== Краторная булка ====)%n" +
                        "%n" +
                        "Price: 550,000000%n"
        );


        assertEquals("Полный рецепт должен соответствовать ожидаемому формату",
                expectedReceipt, receipt);
    }


}