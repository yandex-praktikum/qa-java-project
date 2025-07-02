package praktikum.test;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient sauce;

    @Mock
    Ingredient firstFilling;

    @Mock
    Ingredient secondFilling;

    @Before
    public void initBurger() {
        burger = new Burger();

        // Стабы для булочки
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(200f);

        // Стабы для соуса
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("sour cream");
        when(sauce.getPrice()).thenReturn(200f);

        // Стабы для начинок
        when(firstFilling.getType()).thenReturn(IngredientType.FILLING);
        when(firstFilling.getName()).thenReturn("dinosaur");
        when(firstFilling.getPrice()).thenReturn(200f);

        when(secondFilling.getType()).thenReturn(IngredientType.FILLING);
        when(secondFilling.getName()).thenReturn("cutlet");
        when(secondFilling.getPrice()).thenReturn(100f);
    }

    // Тестирование сеттера setBun в классе Burger
    // Булочка должна устанавливаться корректно
    @Test
    public void theBunMustBeInstalledCorrectly() {
        burger.setBuns(bun);
        assertEquals("Булочка должна установиться корректно", bun, burger.bun);
    }

    // Тестирование сеттера setBun в классе Burger
    // Цена бургера равна цене двух булочек
    @Test
    public void thePriceOfABurgerIsEqualToThePriceOfTwoBuns() {
        burger.setBuns(bun);
        float expectedPrice = 400;
        float actualPrice = burger.getPrice();

        assertEquals("Цена бургера должна быть равна цене двух булочек", expectedPrice, actualPrice,
                0.01f);
    }

    // Тестирование сеттера setBun в классе Burger
    // Название булочки отображается в чеке с информацией о бургере
    @Test
    public void theBunNameIsDisplayedOnTheReceiptWithTheBurgerInformation() {
        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        assertTrue("Название булочки содержится в чеке с информацией о бургере",
                receipt.contains("==== white bun ===="));
    }

    // Тестирование метода addIngredient в классе Burger
    // Размер списка ингредиентов увеличивается на одну позицию
    @Test
    public void theSizeOfTheIngredientListShouldChange() {
        int initialSizeIngredientsList = burger.ingredients.size();

        burger.addIngredient(sauce);
        int newSizeIngredientList = burger.ingredients.size();
        assertEquals("Размер списка ингредиентов должен увеличиться на 1", initialSizeIngredientsList
                + 1, newSizeIngredientList);
    }

     // Тестирование метода addIngredient в классе Burger
     // Список ингредиентов содержит добавленную позицию
     @Test
     public void theListContainsAnAddedIngredient() {
         burger.addIngredient(sauce);
         int newSizeIngredientList = burger.ingredients.size();
         assertEquals("Список содержит добавленный ингредиент", sauce, burger.ingredients
                .get(newSizeIngredientList - 1));
    }

    // Тестирование метода removeIngredient в классе Burger
    // Размер списка ингредиентов уменьшается на одну позицию
    @Test
    public void theSizeOfTheIngredientListIsReduced() {
        burger.addIngredient(sauce);

        int sizeIngredientList = burger.ingredients.size();
        burger.removeIngredient(0);
        int newSizeIngredientList = burger.ingredients.size();
        assertEquals("Размер списка должен уменьшиться на 1", sizeIngredientList - 1,
                newSizeIngredientList);
    }

    // Тестирование метода getPrice в классе Burger
    // Цена бургера равна сумме цен двух булочек и ингредиентов
    @Test
    public void priceOfABurgerWithBunAndIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(firstFilling);
        burger.addIngredient(secondFilling);

        float expectedPrice = 900;
        float actualPrice = burger.getPrice();

        assertEquals("Цена бургера равна сумме цен двух булочек и ингредиентов", expectedPrice, actualPrice,
                0.01f);
    }

    // Тестирование метода getReceipt в классе Burger
    // В чеке должны отобразиться все добавленные ингредиенты
    @Test
    public void allAddedIngredientsShouldBeDisplayed() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(firstFilling);
        burger.addIngredient(secondFilling);

        String receipt = burger.getReceipt();

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(receipt)
                .as("Проверка наличия соуса в чеке")
                .contains("= sauce sour cream =");

        softly.assertThat(receipt)
                .as("Проверка наличия первой начинки в чеке")
                .contains("= filling dinosaur =");

        softly.assertThat(receipt)
                .as("Проверка наличия второй начинки в чеке")
                .contains("= filling cutlet =");

        softly.assertAll();
    }

    // Тестирование метода getReceipt в классе Burger
    // В чеке отображается стоимость бургера, равная сумме цен всех ингредиентов
    @Test
    public void theCorrectPriceIsDisplayed() {

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(firstFilling);
        burger.addIngredient(secondFilling);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Price: 900"));
    }
}