package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    @Parameterized.Parameters(name = "bun={0}, ingredients={1}, expectedPrice={2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, Collections.<Float>emptyList(), 200f},
                {100f, Arrays.asList(50f), 250f},
                {100f, Arrays.asList(50f, 25f), 275f},
                {199.99f, Arrays.asList(0.01f, 1000f), 199.99f * 2 + 0.01f + 1000f},
        });
    }

    private final float bunPrice;
    private final List<Float> ingredientPrices;
    private final float expectedPrice;

    public BurgerTest(float bunPrice, List<Float> ingredientPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    private Burger burger;
    private Bun bunMock;

    @Before
    public void setUp() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Test Bun");
        when(bunMock.getPrice()).thenReturn(bunPrice);

        burger.setBuns(bunMock);

        for (int i = 0; i < ingredientPrices.size(); i++) {
            Ingredient ing = mock(Ingredient.class, "ing-" + i);
            IngredientType type = (i % 2 == 0) ? IngredientType.SAUCE : IngredientType.FILLING;
            when(ing.getType()).thenReturn(type);
            when(ing.getName()).thenReturn((type == IngredientType.SAUCE ? "bbq" : "cutlet") + "-" + i);
            when(ing.getPrice()).thenReturn(ingredientPrices.get(i));
            burger.addIngredient(ing);
        }
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
        assertSame(extra, burger.ingredients.get(burger.ingredients.size() - 1));
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
        assertThat(burger.ingredients.get(0), is(i1));
        assertThat(burger.ingredients.get(1), is(i2));
        assertThat(burger.ingredients.get(2), is(i0));
    }

    @Test
    public void getPrice() {
        float price = burger.getPrice();
        assertEquals(expectedPrice, price, 0.0001f);
    }

    @Test
    public void getReceipt() {
        String receipt = burger.getReceipt();

        String header = String.format("(==== %s ====)%n", "Test Bun");
        String footer = String.format("(==== %s ====)%n", "Test Bun");
        assertTrue("В чеке должен быть заголовок с названием булочки", receipt.startsWith(header));
        assertThat("В чеке должен быть нижний разделитель", receipt, containsString(footer));

        for (int i = 0; i < burger.ingredients.size(); i++) {
            Ingredient ing = burger.ingredients.get(i);
            String expectedLine = String.format("= %s %s =%n",
                    ing.getType().toString().toLowerCase(), ing.getName());
            assertThat("Должна присутствовать строка ингредиента " + i, receipt, containsString(expectedLine));
        }

        String expectedPriceLine = String.format("%nPrice: %f%n", burger.getPrice());
        assertTrue("В конце должен быть блок с ценой в формате %f", receipt.endsWith(expectedPriceLine));
    }
}