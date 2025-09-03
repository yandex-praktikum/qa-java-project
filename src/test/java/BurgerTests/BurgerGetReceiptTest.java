package BurgerTests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static dataTest.dataBurger.*;

public class BurgerGetReceiptTest extends BurgerBaseTest{
    @Test
    public void testGetReceipt() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String expectedReceipt = String.format("(==== %s ====)%n" +
                "= %s =%n" +
                "= %s =%n" +
                "(==== %s ====)%n" +
                "%nPrice: 140,000000%n",
                BUN_NAME, RECEIPT_INGREDIENT_1_NAME, RECEIPT_INGREDIENT_2_NAME, BUN_NAME);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }

    @Test
    public void testGetReceiptEmpty() {
        burger.setBuns(mockBun);

        String expectedReceipt = String.format("(==== %s ====)%n" +
                "(==== %s ====)%n" +
                "%nPrice: 100,000000%n",
                BUN_NAME, BUN_NAME);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }

    @Test
    public void testGetReceiptWithOneIngredient() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String expectedReceipt = String.format("(==== %s ====)%n" +
                "= %s =%n" +
                "(==== %s ====)%n" +
                "%nPrice: 130,000000%n",
                BUN_NAME, RECEIPT_INGREDIENT_1_NAME, BUN_NAME);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }

    @Test
    public void testGetReceiptWithMultipleIngredients() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient1);

        String expectedReceipt = String.format("(==== %s ====)%n" +
                "= %s =%n" +
                "= %s =%n" +
                "= %s =%n" +
                "(==== %s ====)%n" +
                "%nPrice: 170,000000%n",
                BUN_NAME, RECEIPT_INGREDIENT_1_NAME, RECEIPT_INGREDIENT_2_NAME, RECEIPT_INGREDIENT_1_NAME, BUN_NAME);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }
}
