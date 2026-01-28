package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Mock
    private Ingredient ingredient3;

    @Test
    public void addRemoveMoveIngredientShouldUpdateListOrder() {
        Burger burger = new Burger();

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        // перемещаем ingredient1 на позицию 2
        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
        assertSame(ingredient2, burger.ingredients.get(0));
        assertSame(ingredient3, burger.ingredients.get(1));
        assertSame(ingredient1, burger.ingredients.get(2));

        // удаляем ingredient3
        burger.removeIngredient(1);

        assertEquals(2, burger.ingredients.size());
        assertSame(ingredient2, burger.ingredients.get(0));
        assertSame(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getReceiptShouldReturnExpectedString() {
        Burger burger = new Burger();

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(2.5f);
        burger.setBuns(bun);

        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("spicy sauce");
        when(ingredient1.getPrice()).thenReturn(0.75f);

        burger.addIngredient(ingredient1);

        String ls = System.lineSeparator();

        String expected =
                "(==== black bun ====)" + ls +
                        "= sauce spicy sauce =" + ls +
                        "(==== black bun ====)" + ls +
                        ls +
                        "Price: 5.750000" + ls;

        assertEquals(expected, burger.getReceipt());
    }
}
