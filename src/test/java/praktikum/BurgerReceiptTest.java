package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BurgerReceiptTest {

    @Test
    public void getReceipt_withNoIngredients_buildsExpectedReceipt() {
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100);
        burger.setBuns(bun);

        String expected = ""
                + String.format("(==== %s ====)%n", "black bun")
                + String.format("(==== %s ====)%n", "black bun")
                + String.format("%nPrice: %f%n", 200.0f);

        assertEquals(expected, burger.getReceipt());
    }
}

