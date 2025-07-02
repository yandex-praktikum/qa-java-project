import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;
import java.util.List;

import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredientOne;

    @Mock
    Ingredient ingredientTwo;

    @Test
    void addIngredientTest(){
        Burger burger = new Burger();
        List<Ingredient> ingredientsList = new ArrayList<>();
        ingredientsList.add(ingredientOne);
        burger.addIngredient(ingredientOne);
        Assertions.assertEquals(ingredientsList.size(), burger.ingredients.size());
    }

    @Test
    void removeIngredientTest() {
        int expectedResultSize = 1;
        Burger burger = new Burger();
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        burger.removeIngredient(0);
        Assertions.assertEquals(expectedResultSize,burger.ingredients.size());
    }

    @Test
    void moveIngredientTest(){
        Burger burger = new Burger();
        List<Ingredient> ingredientsList = new ArrayList<>();
        ingredientsList.add(ingredientOne);
        ingredientsList.add(ingredientTwo);
        burger.addIngredient(ingredientTwo);
        burger.addIngredient(ingredientOne);
        burger.moveIngredient(0,1);
        Assertions.assertEquals(ingredientsList, burger.ingredients);
    }

    @Test
    void getPriceTest() {
        //Цены для стабов
        float bunPrice = 10f;
        float ingredientOnePrice = 10f;
        float ingredientTwoPrice = 5f;
        //Формула суммы ОР
        float expectedSum = bunPrice * 2 + ingredientOnePrice + ingredientTwoPrice;
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        //Стабы для теста
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredientOne.getPrice()).thenReturn(ingredientOnePrice);
        Mockito.when(ingredientTwo.getPrice()).thenReturn(ingredientTwoPrice);

        Assertions.assertEquals(expectedSum, burger.getPrice());
    }

    @Test
    void getReceiptTest(){
        //Делаем бургер
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        //Переменные для стабов
        float burgerPrice = 35f;
        String ingredientOneName = "Барбекю";
        String ingredientTwoName = "Говяжья котлета";
        String bunName = "С кунжутом";
        //Стабы для теста
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(ingredientOne.getType()).thenReturn(SAUCE);
        Mockito.when(ingredientTwo.getType()).thenReturn(FILLING);
        Mockito.when(ingredientOne.getName()).thenReturn(ingredientOneName);
        Mockito.when(ingredientTwo.getName()).thenReturn(ingredientTwoName);
        Mockito.when(burger.getPrice()).thenReturn(burgerPrice);
        //Ожидаемый результат чека
        String expectedResult = String.format("(==== %s ====)%n= %s %s =%n= %s %s =%n" + "(==== %s ====)%n" + "%nPrice: %f%n",
                bunName,
                IngredientType.SAUCE.toString().toLowerCase(),
                ingredientOneName,
                IngredientType.FILLING.toString().toLowerCase(),
                ingredientTwoName,
                bunName,
                burgerPrice);
        Assertions.assertEquals(expectedResult, burger.getReceipt());
    }
}
