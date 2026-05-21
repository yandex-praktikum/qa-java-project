import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BurgerTest extends BaseTest {

    @Test
    public void receiptContainsBunNameTest() {
        burger.setBuns(bunMock);
        burger.addIngredient(fillingMock);
        String receipt = burger.getReceipt();
        assertTrue("В чеке должно быть название булки", receipt.contains("Булочка"));
    }

    @Test
    public void receiptContainsIngredientNameTest() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        String receipt = burger.getReceipt();
        assertTrue("В чеке должно быть название ингридиента", receipt.contains("Соус"));
    }

    @Test
    public void receiptContainsPrice() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        String receipt = burger.getReceipt();
        assertTrue("В чеке должна быть цена", receipt.contains("Price:"));
    }

    @Test
    public void receiptContainsPriceCorrectValueWithOneIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(fillingMock);
        String receipt = burger.getReceipt();
        assertTrue("В чеке должна быть цена 275", receipt.contains("275"));
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        burger.moveIngredient(0, 1);
        assertEquals("Ингридиент ingredient должен переместиться с позиции 0 на 1", ingredientMock, burger.ingredients.get(1));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertEquals("Список ингредиентов не должен содержать ингредиентов", 0, burger.ingredients.size());
    }

}
