package praktikum;


import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private Burger burger;
    private Bun bun;
    private List<Ingredient> ingredients;
    private String expectedReceipt;

    public BurgerReceiptTest(Bun bun, List<Ingredient> ingredients, String expectedReceipt){
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }
}
