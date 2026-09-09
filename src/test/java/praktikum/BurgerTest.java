package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredientFiling, ingredientSauce;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void checkSetBuns() {
        burger.setBuns(bun);
        assertEquals("Установлено не верное название и цена булочки", bun, burger.bun);
    }

    @Test
    public void checkAddIngredientTest() {
        burger.addIngredient(ingredientFiling);
        assertEquals("Ингридиент не добавлен", 1, burger.ingredients.size());
    }

    @Test
    public void checkRemoveIngredientTest() {
        burger.addIngredient(ingredientSauce);
        burger.removeIngredient(0);
        assertEquals("Ингридиент не удалён", 0, burger.ingredients.size());
    }

    @Test
    public void checkMoveIngredientTest() {
        burger.addIngredient(ingredientFiling);
        burger.addIngredient(ingredientSauce);
        burger.moveIngredient(0, 1);
        assertEquals("Ингредиент не был перемещен на новое место", burger.ingredients.get(0), ingredientSauce);
    }

    @Test
    public void checkGetPrice() {
        when(bun.getPrice()).thenReturn(300f);
        when(ingredientFiling.getPrice()).thenReturn(100f);
        when(ingredientSauce.getPrice()).thenReturn(200f);
        float price = bun.getPrice() * 2;
        float actualResult = price + ingredientFiling.getPrice() + ingredientSauce.getPrice();
        float expectedResult = 900f;
        assertEquals("Неверно указана цена",expectedResult, actualResult, 0.01f);
    }

    @Test
    public void checkGetReceiptText() {
        burger.setBuns(bun);
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(60.0F);

        burger.addIngredient(ingredientFiling);
        burger.addIngredient(ingredientSauce);
        when(ingredientFiling.getPrice()).thenReturn(100f);
        when(ingredientFiling.getName()).thenReturn("cutlet");
        when(ingredientFiling.getType()).thenReturn(IngredientType.FILLING);

        when(ingredientSauce.getPrice()).thenReturn(200f);
        when(ingredientSauce.getName()).thenReturn("sour cream");
        when(ingredientSauce.getType()).thenReturn(IngredientType.SAUCE);

        String actualResult= burger.getReceipt();
        String expectedResult = ("(==== white bun ====)\r\n" +
                "= filling cutlet =\r\n" +
                "= sauce sour cream =\r\n" +
                "(==== white bun ====)\r\n") +
                "\r\n" + "Price: 420,000000\r\n";
        assertEquals("Неверно указан рецепт", expectedResult, actualResult);
    }
}
