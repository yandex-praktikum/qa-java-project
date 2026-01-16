import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;
    private IngredientType type;
    private String name;
    private float price;

    @Parameterized.Parameters
    public static Object[] data(){
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100},
                {IngredientType.SAUCE, "sour cream", 200},
                {IngredientType.SAUCE, "chili sauce", 300},
                {IngredientType.FILLING, "cutlet", 100},
                {IngredientType.FILLING, "dinosaur", 200},
                {IngredientType.FILLING, "sausage", 300},
                {IngredientType.SAUCE,"",0},
                {IngredientType.SAUCE,"",-100},
        };
    }

    @Test
    public void setBunsTest(){
        Bun bun = new Bun("red bun", 300);
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest(){
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(type, name, price));
        assertFalse("Упс. Что-то пошло не так.", burger.ingredients.isEmpty());
    }

    @Test
    public void removeIngredientTest(){
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(type, name, price));
        burger.removeIngredient(0);
        assertTrue("Упс. Что-то пошло не так.", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "dinosaur", 100));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "sour cream", 200));
        burger.moveIngredient(0, 1);
        String expectedResult = "sour cream";
        String actualResult = burger.ingredients.get(0).name;
        assertEquals("Упс. Что-то пошло не так.", expectedResult, actualResult);
    }

    @Test
    public void getPriceTest(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(new Ingredient(type, name, price));
        burger.addIngredient(new Ingredient(type, name, price));
        assertEquals(0.0, burger.getPrice(), 0.001);
    }

    @Mock
    Ingredient ingredientFirst;
    @Test
    public void getReceiptTest(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        String result = String.format("(==== Краторная булка N-200i ====)%n" + "= filling Флюоресцентная булка R2-D3 =%n" + "(==== Краторная булка N-200i ====)%n" +"%n" +"Price: 450,000000%n");

        burger.addIngredient(ingredientFirst);

        Mockito.when(bun.getPrice()).thenReturn(150F);
        Mockito.when(ingredientFirst.getPrice()).thenReturn(150F);
        Mockito.when(bun.getName()).thenReturn("Краторная булка N-200i");
        Mockito.when(ingredientFirst.getName()).thenReturn("Флюоресцентная булка R2-D3");
        Mockito.when(ingredientFirst.getType()).thenReturn(IngredientType.FILLING);

        Assert.assertEquals(result, burger.getReceipt());
    }
}
