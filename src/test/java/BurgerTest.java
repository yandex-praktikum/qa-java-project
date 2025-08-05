import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.FILLING;

@ExtendWith(MockitoExtension.class)

public class BurgerTest {

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @Test
    public void setBunsTest(){
        Burger burger = new Burger();

        String expectedName = "Круглая булка";
        Mockito.when(bun.getName()).thenReturn(expectedName);
        burger.setBuns(bun);

        String actualReceipt = burger.getReceipt();

        assertEquals(actualReceipt.contains(expectedName), true, "Булка не соотвествует заданной!");
    }

    @Test
    public void addIngredientTest(){
        Burger burger = new Burger();

        String expectedName = "Котлета из оленины";
        String expectedType = "filling";
        Mockito.when(ingredient.getName()).thenReturn(expectedName);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.valueOf("FILLING"));
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String actualReceipt = burger.getReceipt();

        assertEquals(actualReceipt.contains(expectedName), true, "Имя ингредиента не соотвествует заданному!");
        assertEquals(actualReceipt.contains(expectedType), true, "Тип ингредиента не соотвествует заданному!");
    }

    @Test
    public void removeIngredientTest(){
        Burger burger = new Burger();

        String expectedName = "Котлета из оленины";
        String expectedType = "filling";
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        String actualReceipt = burger.getReceipt();

        assertEquals(actualReceipt.contains(expectedName), false, "Имя ингредиента не соотвествует заданному!");
        assertEquals(actualReceipt.contains(expectedType), false, "Тип ингредиента не соотвествует заданному!");
    }

    @Test
    public void getPriceTest(){
        Burger burger = new Burger();

        float expectedBunPrice = 100;
        float expectedIngredientPrice = 200;
        float expectedBurgerPrice = expectedBunPrice * 2 + expectedIngredientPrice;

        Mockito.when(bun.getPrice()).thenReturn(expectedBunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(expectedIngredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);



        float actualBurgerPrice = burger.getPrice();

        assertEquals(actualBurgerPrice, expectedBurgerPrice, "Цена не соотвествует заданной!");
    }

}
