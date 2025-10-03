import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class BurgerTest {
    @Mock
    Ingredient ingredient;
    @Mock
    Ingredient ingredient2;
    @Mock
    Bun bun;

    @Test
    public void shouldSetBun(){
        Burger burger = new Burger();
        Bun bun = new Bun("bulka", 21F);
        burger.setBuns(bun);
        assertEquals(bun, burger.bun,"Сеттер неправильно работает");

    }
    @Test
    public void shouldAddIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertTrue(burger.ingredients.contains(ingredient),"Неправильно добавляется ингредиент");
    }
    @Test
    public void shouldRemoveIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(ingredient));
    }
    @Test
    public void shouldMoveIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0,1);
        assertEquals(ingredient, burger.ingredients.get(1),"Неправильно перемещает ингредиенты");
    }
    @ParameterizedTest
    @CsvSource({
            "48.2F, 512F, 322F",
            "36.5F, 145F, 444F"
    })
    public void shouldGetPrice(float bunPrice, float ingPrice1, float ingPrice2){
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(ingPrice1);
        Mockito.when(ingredient2.getPrice()).thenReturn(ingPrice2);
        float expectedTotalPrice = bunPrice*2+ingPrice1+ingPrice2;
        assertEquals(expectedTotalPrice,burger.getPrice(),"Неправильно считает цену");
    }
    @ParameterizedTest
    @CsvSource({
            "татар, грязный носок, булка ,SAUCE, FILLING, 600F",
            "karama, shaa, багет ,FILLING, SAUCE, 400.41F"
    })
    public void shouldGetReceipt(String ingName1, String ingName2, String bunName, IngredientType ingType1, IngredientType ingType2, float price){
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(ingredient.getType()).thenReturn(ingType1);
        Mockito.when(ingredient.getName()).thenReturn(ingName1);
        Mockito.when(ingredient2.getType()).thenReturn(ingType2);
        Mockito.when(ingredient2.getName()).thenReturn(ingName2);
        Mockito.when(burger.getPrice()).thenReturn(price);

        String expectedReceipt =
                String.format("(==== %s ====)%n", bunName) +
                String.format("= %s %s =%n", ingType1.toString().toLowerCase(), ingName1) +
                String.format("= %s %s =%n", ingType2.toString().toLowerCase(), ingName2) +
                String.format("(==== %s ====)%n", bunName) +
                String.format("%nPrice: %f%n", price);

        assertEquals(expectedReceipt, burger.getReceipt(),"Неправильно печатает рецепт");

    }
}
