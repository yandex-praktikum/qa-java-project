import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    Burger burger = new Burger();

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient newIngredient;

    @Mock
    Bun bun;

    @Before
    public void setUp() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(newIngredient);
    }

    // проверяем добавление булок

    @Test

    public void setBunsTest(){
        assertEquals(bun, burger.bun);
    }

    // проверяем, что ингредиент добавляется

   @Test

    public void addIngredientTest(){
       assertEquals(ingredient, burger.ingredients.get(0));
    }

    // проверяем, что ингредиент удаляется

   @Test

    public void removeIngredientTest(){
       burger.removeIngredient(0);
       assertEquals(1, burger.ingredients.size());
    }

    // проверяем, что ингредиент меняет индекс

    @Test

    public void moveIngredientTest(){

       burger.moveIngredient(0,1);
       assertEquals(ingredient, burger.ingredients.get(1));
    }


    // проверяем цену

    @Test

    public void getPrice(){
        Mockito.when(bun.getPrice()).thenReturn(988f);
        Mockito.when(ingredient.getPrice()).thenReturn(80f);
        Mockito.when(newIngredient.getPrice()).thenReturn(3000f);

        float expectedPrice = 988 * 2 + 80 + 3000;
        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }

    // проверка печати чека

    @Test

    public void getReceiptTest(){
        Mockito.when(bun.getName()).thenReturn("Флюоресцентная булка R2-D3");
        Mockito.when(ingredient.getName()).thenReturn("Соус фирменный Space Sauce");
        Mockito.when(newIngredient.getName()).thenReturn("Говяжий метеорит (отбивная)");

        Mockito.when(ingredient.getType()).thenReturn(SAUCE);
        Mockito.when(newIngredient.getType()).thenReturn(FILLING);

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                "Флюоресцентная булка R2-D3",
                SAUCE.toString().toLowerCase(), "Соус фирменный Space Sauce",
                FILLING.toString().toLowerCase(), "Говяжий метеорит (отбивная)",
                "Флюоресцентная булка R2-D3",
                0.0f);
        assertEquals(expectedReceipt, burger.getReceipt());

    }

    @After

    public void tearDown()
    {
        burger.ingredients.clear();
    }


}
