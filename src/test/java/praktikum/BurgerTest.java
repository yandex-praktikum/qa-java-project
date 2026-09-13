package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(bun, burger.bun,"Установлено не верное название и цена булочки");
    }

    @Test
    public void checkAddIngredientTest() {
        burger.addIngredient(ingredientFiling);
        assertEquals( 1, burger.ingredients.size(), "Ингридиент не добавлен");
    }

    @Test
    public void checkRemoveIngredientTest() {
        burger.addIngredient(ingredientSauce);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size(),"Ингридиент не удалён");
    }

    @Test
    public void checkMoveIngredientTest() {
        burger.addIngredient(ingredientFiling);
        burger.addIngredient(ingredientSauce);
        burger.moveIngredient(0, 1);
        assertEquals(burger.ingredients.get(0), ingredientSauce,"Ингредиент не был перемещен на новое место");
    }

    @Test
    public void checkGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredientFiling);
        burger.addIngredient(ingredientSauce);

        when(ingredientFiling.getPrice()).thenReturn(100f);
        when(ingredientSauce.getPrice()).thenReturn(200f);
        when(bun.getPrice()).thenReturn(300f);

        float actualResult = burger.getPrice();
        float expectedResult = 900f;
        assertEquals(expectedResult, actualResult, 0.01f,"Неверно указана цена");

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
        assertEquals(expectedResult, actualResult,"Неверно указан рецепт");
    }
}
