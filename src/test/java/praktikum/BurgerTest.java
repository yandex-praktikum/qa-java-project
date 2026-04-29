package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {
    @Test
    void burgerSetBunCorrect (){
BunInterface bun = mock(BunInterface.class);
Burger burger = new Burger();
burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }
    @Test
    void burgerAddIngredientArrayLengthCorrect(){
        IngredientInterface ingredient = mock(IngredientInterface.class);
        Burger burger = new Burger();
        int expectedLength = burger.ingredients.size()+1;
        burger.addIngredient(ingredient);
        int actualLength = burger.ingredients.size();
        assertEquals(expectedLength, actualLength);
    }
    @Test
    void burgerAddIngredientCorrect(){
        IngredientInterface ingredient = mock(IngredientInterface.class);
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        IngredientInterface actualIngredient = burger.ingredients.get(burger.ingredients.size()-1);
        assertEquals(ingredient, actualIngredient);
    }
    @Test
    void burgerRemoveIngredientArrayLengthCorrect(){
        IngredientInterface ingredient = mock(IngredientInterface.class);
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        int expectedLength = burger.ingredients.size()-1;
        burger.removeIngredient(0);
        int actualLength = burger.ingredients.size();
        assertEquals(expectedLength, actualLength);
    }

    @Test
    void burgerMoveIngredientCorrect(){
        IngredientInterface ingredientOne = mock(IngredientInterface.class);
        IngredientInterface ingredientTwo = mock(IngredientInterface.class);
        Burger burger = new Burger();
        burger.addIngredient(ingredientOne);
        int indexOne = burger.ingredients.indexOf(ingredientOne);
        burger.addIngredient(ingredientTwo);
        int indexTwo = burger.ingredients.indexOf(ingredientTwo);
        burger.moveIngredient(indexOne,indexTwo);
        IngredientInterface movedIngredient = burger.ingredients.get(indexTwo);
        assertEquals(ingredientOne, movedIngredient);
    }
@Test
    void burgerGetPriceBurgerCorrect(){
    Burger burger = new Burger();
    Database data = new Database();
    BunInterface bun = mock(BunInterface.class);
    float bunPrice = data.availableBuns().get(0).price;
    when(bun.getPrice()).thenReturn(bunPrice);
   burger.setBuns(bun);
    float expectedPrice = bunPrice*2;
   for (int i = 0;i<data.availableIngredients().size();i++){
       IngredientInterface ingredient = mock(IngredientInterface.class);
       float ingredientPrice = data.availableIngredients().get(i).price;
       when(ingredient.getPrice()).thenReturn(ingredientPrice);
       burger.addIngredient(ingredient);
       expectedPrice = expectedPrice+ingredientPrice;
   }
   assertEquals(expectedPrice,burger.getPrice());
}
    @Test
    void burgerGetPriceBurgerException(){
        Burger burger = new Burger();
        Database data = new Database();
        BunInterface bun = mock(BunInterface.class);
        float oneBunPrice = data.availableBuns().get(0).price;
        when(bun.getPrice()).thenReturn(oneBunPrice);
        Executable executable = () -> burger.getPrice();
        assertThrows(NullPointerException.class, executable);
    }
    @Test
    void burgerGetReceiptCorrect(){
        Burger burger = new Burger();
        Database data = new Database();
        BunInterface bun = mock(BunInterface.class);
        float bunPrice = data.availableBuns().get(0).price;
        when(bun.getPrice()).thenReturn(bunPrice);
        String bunSelected = data.availableBuns().get(0).name;
        when(bun.getName()).thenReturn(bunSelected);
        burger.setBuns(bun);
        for (int i = 0;i<data.availableIngredients().size();i++){
            IngredientInterface ingredient = mock(IngredientInterface.class);
            float ingredientPrice = data.availableIngredients().get(i).price;
            when(ingredient.getPrice()).thenReturn(ingredientPrice);
            String ingredientSelected = data.availableIngredients().get(i).name;
            when(ingredient.getName()).thenReturn(ingredientSelected);
            IngredientType ingredientType = data.availableIngredients().get(i).type;
            when(ingredient.getType()).thenReturn(ingredientType);
            burger.addIngredient(ingredient);
        }
        burger.getPrice();
        String actual = burger.getReceipt();
        String expected = "(==== black bun ====)\n" +
                "= sauce hot sauce =\n" +
                "= sauce sour cream =\n" +
                "= sauce chili sauce =\n" +
                "= filling cutlet =\n" +
                "= filling dinosaur =\n" +
                "= filling sausage =\n" +
                "(==== black bun ====)\n" +
                "\nPrice: 1400,000000\n";
        actual = actual.replaceAll("\r\n", "\n");
        expected = expected.replaceAll("\r\n", "\n");
        assertEquals(expected,actual);

    }

}
