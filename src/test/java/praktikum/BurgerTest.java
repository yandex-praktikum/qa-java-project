package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;

    @Before
    public void setUp() {
        burger = new Burger();
        bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Test Bun");
        when(bunMock.getPrice()).thenReturn(100f);
        burger.setBuns(bunMock);
    }

    @After
    public void tearDown() {
        burger = null;
        bunMock = null;
    }

    @Test
    public void setBuns() {
        assertSame("Поле bun должно ссылаться на установленную булочку", bunMock, burger.bun);
    }

    @Test
    public void addIngredient() {
        int before = burger.ingredients.size();

        Ingredient extra = mock(Ingredient.class);
        when(extra.getType()).thenReturn(IngredientType.SAUCE);
        when(extra.getName()).thenReturn("extra-sauce");
        when(extra.getPrice()).thenReturn(10f);

        burger.addIngredient(extra);

        assertEquals(before + 1, burger.ingredients.size());
    }

    @Test
    public void removeIngredient() {
        Ingredient toRemove = mock(Ingredient.class);
        when(toRemove.getType()).thenReturn(IngredientType.FILLING);
        when(toRemove.getName()).thenReturn("delete-me");
        when(toRemove.getPrice()).thenReturn(123f);
        burger.addIngredient(toRemove);

        int initial = burger.ingredients.size();
        int index = burger.ingredients.indexOf(toRemove);

        burger.removeIngredient(index);

        assertEquals("Размер должен уменьшиться на 1", initial - 1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientFromList() {
        Ingredient toRemove = mock(Ingredient.class);
        when(toRemove.getType()).thenReturn(IngredientType.FILLING);
        when(toRemove.getName()).thenReturn("delete-me");
        when(toRemove.getPrice()).thenReturn(123f);
        burger.addIngredient(toRemove);

        int index = burger.ingredients.indexOf(toRemove);

        burger.removeIngredient(index);

        assertFalse("Элемент должен быть удалён", burger.ingredients.contains(toRemove));
    }

    @Test
    public void moveIngredient() {
        Ingredient i0 = mock(Ingredient.class, "i0");
        when(i0.getType()).thenReturn(IngredientType.SAUCE);
        when(i0.getName()).thenReturn("i0");
        when(i0.getPrice()).thenReturn(1f);

        Ingredient i1 = mock(Ingredient.class, "i1");
        when(i1.getType()).thenReturn(IngredientType.FILLING);
        when(i1.getName()).thenReturn("i1");
        when(i1.getPrice()).thenReturn(2f);

        Ingredient i2 = mock(Ingredient.class, "i2");
        when(i2.getType()).thenReturn(IngredientType.SAUCE);
        when(i2.getName()).thenReturn("i2");
        when(i2.getPrice()).thenReturn(3f);

        burger.ingredients.clear();
        burger.addIngredient(i0);
        burger.addIngredient(i1);
        burger.addIngredient(i2);

        burger.moveIngredient(0, 2);
        assertThat(burger.ingredients.get(2), is(i0));
    }
}
