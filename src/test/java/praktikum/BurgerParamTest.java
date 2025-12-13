package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParamTest {
    float price;
    ArrayList<Ingredient> ingredients;
    Bun bun;
    Burger burger;

    @Parameterized.Parameters(name = "Тестовые данные: булочка - {0}, ингредиенты - {1}, стоимость - {2}")
    public static Object[][] getParams() {
        Ingredient ingredientMock = mock(Ingredient.class);
        when(ingredientMock.getPrice()).thenReturn(250f);
        when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        return new Object[][] {
                {new Bun("Розовая", 100f), new ArrayList<>(), 200f},
                {new Bun("Синяя", 100f), new ArrayList<>(List.of(ingredientMock, ingredientMock)), 700f}
        };
    }

    public BurgerParamTest(Bun bun, ArrayList<Ingredient> ingredients, float price) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.price = price;
    }

    @Before
    public void prepareBurger() {
        burger = new Burger();
        burger.setBuns(this.bun);
        for (Ingredient ingredient : this.ingredients) {
            burger.addIngredient(ingredient);
        }
    }

    @Test
    public void testGetPrice() {
        assertEquals(this.price, burger.getPrice(), 0);
    }

    @Test
    public void testGetReceipt() {
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));

        for (Ingredient ingredient : ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));

        assertEquals(receipt.toString(), burger.getReceipt());
    }
}
