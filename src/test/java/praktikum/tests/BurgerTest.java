package praktikum.tests;

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

    public Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient cheese;

    @Mock
    private Ingredient tomato;

    @Mock
    private Ingredient cucumber;

    @Before
    public void makeBurger() {
        burger = new Burger();
    }


    @Test
    public void setBunTest() {
        burger.setBuns(bun);

        when(bun.getName()).thenReturn("Название булочки");
        assertEquals("Взяли не ту булочку", "Название булочки", burger.bun.getName());
    }


    @Test
    public void addIngredientTest() {

        burger.addIngredient(cheese);
        assertTrue("Ингредиент не добавлен", burger.ingredients.contains(cheese));
    }


    @Test
    public void removeIngredientTest() {

        burger.ingredients.add(cheese);
        burger.ingredients.add(tomato);
        burger.ingredients.add(cucumber);

        burger.removeIngredient(1);

        assertTrue("Удалённый ингредиент всё ещё присутствует", !burger.ingredients.contains(tomato));
    }


    @Test
    public void moveIngredientTest() {

        burger.ingredients.add(cheese);
        burger.ingredients.add(tomato);
        burger.ingredients.add(cucumber);
        burger.moveIngredient(1, 0);

        assertEquals("Ингредиент перемещен не правильно", burger.ingredients.get(0), tomato);
    }


    @Test
    public void priceWithIngredientsTest() {

        burger.bun = bun;
        burger.ingredients.add(cheese);
        burger.ingredients.add(tomato);
        burger.ingredients.add(cucumber);

        when(bun.getPrice()).thenReturn(200.0F);
        when(cheese.getPrice()).thenReturn(80.0F);
        when(tomato.getPrice()).thenReturn(45.0F);
        when(cucumber.getPrice()).thenReturn(45.0F);


        float expectedPrice = 200.0f * 2 + 80.0f + 45.0f + 45.0f;

        assertEquals("Неправильная общая стоимость бургера",
                expectedPrice, burger.getPrice(), 0.0F);

    }


    @Test
    public void getReceiptTest() {

        burger.bun = bun;
        burger.ingredients.add(cheese);
        burger.ingredients.add(tomato);
        burger.ingredients.add(cucumber);

        when(bun.getName()).thenReturn("bun");
        when(bun.getPrice()).thenReturn(200.0F);

        when(cheese.getType()).thenReturn(IngredientType.FILLING);
        when(cheese.getName()).thenReturn("ingredient0");
        when(cheese.getPrice()).thenReturn(80.0F);

        when(tomato.getType()).thenReturn(IngredientType.FILLING);
        when(tomato.getName()).thenReturn("ingredient1");
        when(tomato.getPrice()).thenReturn(45.0F);

        when(cucumber.getType()).thenReturn(IngredientType.SAUCE);
        when(cucumber.getName()).thenReturn("ingredient2");
        when(cucumber.getPrice()).thenReturn(45.0F);


        String expectedReceipt = "(==== bun ====)\r\n" +
                "= filling ingredient0 =\r\n" +
                "= filling ingredient1 =\r\n"    +
                "= sauce ingredient2 =\r\n" +
                "(==== bun ====)\r\n" +
                "\r\n" +
                "Price: 570,000000\r\n";

        String actualReceipt = burger.getReceipt();

        assertEquals(
                "Чеки не совпадают!",
                expectedReceipt.trim(),
                actualReceipt.trim()
        );
    }
}
