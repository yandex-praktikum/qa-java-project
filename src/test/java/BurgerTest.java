import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Test
    public void setBunTest() {
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100);
        burger.setBuns(bun);
        assertEquals("Другая булочка", bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "sour cream", 200);
        burger.addIngredient(ingredient);
        assertTrue("Ингредиент не добавился", burger.ingredients.contains(ingredient));
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "chili sauce", 300);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertTrue("Ингредиент не удалился", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        Ingredient firstIngredient = new Ingredient(IngredientType.SAUCE, "chili sauce", 300);
        Ingredient secondIngredient = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        Ingredient thirdIngredient = new Ingredient(IngredientType.FILLING, "sausage", 300);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        burger.moveIngredient(0, 2);
        assertEquals("Перенос ингредиентов неверный", firstIngredient, burger.ingredients.get(2));
    }

    @Spy
    Bun bunReceipt = new Bun("red bun", 300);
    @Spy
    Ingredient firstIngredientReceipt = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
    @Spy
    Ingredient secondIngredientReceipt = new Ingredient(IngredientType.FILLING, "sausage", 300);

    @Test
    public void getReceiptPriceTest() {
        Burger burger = new Burger();
        burger.setBuns(bunReceipt);
        burger.addIngredient(firstIngredientReceipt);
        burger.addIngredient(secondIngredientReceipt);
        String expectedReceipt = String.format(
                        "(==== red bun ====)%n" +
                        "= sauce hot sauce =%n" +
                        "= filling sausage =%n" +
                        "(==== red bun ====)%n" +
                        "%nPrice: 1000,000000%n"
        );
        assertEquals("Формат чека неверный", expectedReceipt, burger.getReceipt());
    }
}

