package praktikum;

import helper.GenerateRandom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static helper.Constants.DELTA_FLOAT_BOUNED;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Ingredient stuffingIngredient;
    @Mock
    private Ingredient sauceIngredient;
    @Mock
    private Bun bigBun;

    @Test
    public void setBunsTest() {
        Burger burger = new Burger();
        burger.setBuns(bigBun);
        Assert.assertEquals(bigBun.name, burger.bun.getName());
    }

    @Test
    public void addIngredientInvokedTest() {
        Burger burger = new Burger();
        burger.addIngredient(stuffingIngredient);
        Assert.assertTrue(burger.ingredients.contains(stuffingIngredient));
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(stuffingIngredient);
        int index = burger.ingredients.indexOf(stuffingIngredient);

        burger.removeIngredient(index);

        Assert.assertFalse(burger.ingredients.contains(stuffingIngredient));
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(stuffingIngredient);
        burger.addIngredient(sauceIngredient);

        int indexFirstIngredient = burger.ingredients.indexOf(stuffingIngredient);
        int indexSecondIngredient = burger.ingredients.indexOf(sauceIngredient);

        burger.moveIngredient(indexFirstIngredient, indexSecondIngredient);

        int newIndexFirstIngredient = burger.ingredients.indexOf(stuffingIngredient);
        int newIndexSecondIngredient = burger.ingredients.indexOf(sauceIngredient);

        Assert.assertEquals(indexFirstIngredient, newIndexSecondIngredient);
        Assert.assertEquals(indexSecondIngredient, newIndexFirstIngredient);
    }

    @Test
    public void getPriceReturnCorrectPrice() {
        Burger burger = new Burger();
        Mockito.when(stuffingIngredient.getPrice())
                .thenReturn(GenerateRandom.generateRandomFloatBouned(10f, 100f));
        Mockito.when(sauceIngredient.getPrice())
                .thenReturn(GenerateRandom.generateRandomFloatBouned(10f, 100f));

        burger.setBuns(bigBun);
        burger.addIngredient(stuffingIngredient);
        burger.addIngredient(sauceIngredient);

        float expectedPrice = (burger.bun.getPrice() * 2) + stuffingIngredient.getPrice() + sauceIngredient.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA_FLOAT_BOUNED);
    }

    @Test
    public void getReceiptReturnCorrectReceipt() {
        Burger burger = new Burger();
        Mockito.when(stuffingIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(stuffingIngredient.getName()).thenReturn(GenerateRandom.generateRandomFood());
        Mockito.when(stuffingIngredient.getPrice())
                .thenReturn(GenerateRandom.generateRandomFloatBouned(10f, 100f));

        Mockito.when(sauceIngredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(sauceIngredient.getName()).thenReturn(GenerateRandom.generateRandomFood());
        Mockito.when(sauceIngredient.getPrice())
                .thenReturn(GenerateRandom.generateRandomFloatBouned(10f, 100f));

        burger.setBuns(bigBun);
        burger.addIngredient(stuffingIngredient);
        burger.addIngredient(sauceIngredient);

        String expectedReceipt = "(==== " + burger.bun.getName() + " ====)" + "\r\n"
                + "= " + stuffingIngredient.getType().toString().toLowerCase() + " " + stuffingIngredient.getName() + " =" + "\r\n"
                + "= " + sauceIngredient.getType().toString().toLowerCase() + " " + sauceIngredient.getName() + " =" + "\r\n"
                + "(==== " + burger.bun.getName() + " ====)" + "\r\n\r\n"
                + "Price: " + String.format("%f", burger.getPrice()) + "\r\n";

        Assert.assertEquals(expectedReceipt, burger.getReceipt());
    }

}
