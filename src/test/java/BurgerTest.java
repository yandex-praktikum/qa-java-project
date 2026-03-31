import praktikum.*;

import static org.junit.jupiter.api.Assertions.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class BurgerTest {
     Burger burger;
     Bun bun;
     Ingredient sauce;
     Ingredient filling;

    @BeforeEach
    public void setUpAll() {
        burger = new Burger();
        bun = new Bun("Bulochka", 100.0f);
        sauce = new Ingredient(SAUCE, "ketchup", 20.5f);
        filling = new Ingredient(FILLING, "chicken", 50.0f);
    }

    @Test
    //проверяем, что в бургере есть булка
    void setBunsShouldSetBun() {
        burger.setBuns(bun);
        assertNotNull(burger.bun);
        assertEquals("Bulochka", burger.bun.getName());
        assertEquals(100.0f, burger.bun.getPrice());
    }
    @Test
    //проверяем, что через метод addIngredient в список ingredients добавляется sauce и filling
    void addIngredientShouldAddToList(){
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        assertEquals(2, burger.ingredients.size());
        assertSame(sauce, burger.ingredients.get(0));
        assertSame(filling, burger.ingredients.get(1));
    }
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void removeIngredientShouldWorkWithAnyIndex(int index) {
        // заполняем 6 ингредиентов
        for (int i = 0; i < 6; i++) {
            burger.addIngredient(new Ingredient(SAUCE, "test" + i, 10f));
        }

        burger.removeIngredient(index);

        assertEquals(5, burger.ingredients.size());  // всегда 5 остается
    }
    @Test
    void removeFirstShouldLeaveFilling() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(0);  // удаляем sauce

        assertSame(filling, burger.ingredients.get(0));  // ✓
    }

    @Test
    void removeSecondShouldLeaveSauce() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(1);  // удаляем filling

        assertSame(sauce, burger.ingredients.get(0));  // ✓
    }

    @Test
    void moveIngredientShouldChangeOrder() {
        Ingredient anotherSauce = new Ingredient(SAUCE, "ketchup", 15.0f);

        burger.addIngredient(sauce);         // index 0
        burger.addIngredient(filling);       // index 1
        burger.addIngredient(anotherSauce);  // index 2

        // перенос элемент с index 0 в позицию 2
        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
        assertSame(filling, burger.ingredients.get(0));
        assertSame(anotherSauce, burger.ingredients.get(1));
        assertSame(sauce, burger.ingredients.get(2));
    }
    @Test
    void getPriceShouldReturnSumOfBunAndIngredients() {
        burger.setBuns(bun); // 100 * 2 = 200
        burger.addIngredient(sauce);   // + 20.5
        burger.addIngredient(filling); // + 50

        float expected = 100.0f * 2 + 20.5f + 50.0f;

        assertEquals(expected, burger.getPrice());
    }
    @Test
    void getReceiptShouldReturnCorrectFormattedString() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== Bulochka ====)"));
        assertTrue(receipt.contains("= sauce ketchup ="));
        assertTrue(receipt.contains("= filling chicken ="));

        float expectedPrice = 100.0f * 2 + 20.5f + 50.0f;
        String expectedPricePart = String.format("Price: %f", expectedPrice);

        assertTrue(receipt.contains(expectedPricePart));
    }
}