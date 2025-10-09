package praktikum.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Burger;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    // Параметры для параметризованных тестов
    private final float bunPrice;
    private final List<Float> ingredientPrices;
    private final float expectedPrice;
    private final String bunName;
    private final List<String> ingredientNames;
    private final List<IngredientType> ingredientTypes;
    private final String expectedReceipt;

    public BurgerTest(float bunPrice, List<Float> ingredientPrices, float expectedPrice,
                      String bunName, List<String> ingredientNames, List<IngredientType> ingredientTypes,
                      String expectedReceipt) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
        this.bunName = bunName;
        this.ingredientNames = ingredientNames;
        this.ingredientTypes = ingredientTypes;
        this.expectedReceipt = expectedReceipt;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // Сценарий: Бургер с одной булочкой, без ингредиентов
                {100.0f, Collections.emptyList(), 200.0f, // цена булочки * 2
                        "black bun", Collections.emptyList(), Collections.emptyList(),
                        "(==== black bun ====)\n(==== black bun ====)\n\nPrice: 200.000000\n"},
                // Сценарий: Бургер с одной булочкой и одним ингредиентом
                {100.0f, Arrays.asList(50.0f), 250.0f, // цена булочки * 2 + цена ингредиента
                        "white bun", Arrays.asList("hot sauce"), Arrays.asList(IngredientType.SAUCE),
                        "(==== white bun ====)\n= sauce hot sauce =\n(==== white bun ====)\n\nPrice: 250.000000\n"},
                // Сценарий: Бургер с одной булочкой и двумя ингредиентами
                {200.0f, Arrays.asList(100.0f, 150.0f), 650.0f, // цена булочки * 2 + цена ингредиента1 + цена ингредиента2
                        "red bun", Arrays.asList("cutlet", "sour cream"), Arrays.asList(IngredientType.FILLING, IngredientType.SAUCE),
                        "(==== red bun ====)\n= filling cutlet =\n= sauce sour cream =\n(==== red bun ====)\n\nPrice: 650.000000\n"},
                // Сценарий: Бургер с булочкой и ингредиентами с нулевой ценой
                {0.0f, Arrays.asList(0.0f, 0.0f), 0.0f,
                        "free bun", Arrays.asList("free sauce", "free filling"), Arrays.asList(IngredientType.SAUCE, IngredientType.FILLING),
                        "(==== free bun ====)\n= sauce free sauce =\n= filling free filling =\n(==== free bun ====)\n\nPrice: 0.000000\n"}
        });
    }

    @Test
    public void testSetBuns() {
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);

        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        when(ingredient1.getName()).thenReturn(ingredientNames.isEmpty() ? "default" : ingredientNames.get(0));
        when(ingredient1.getPrice()).thenReturn(ingredientPrices.isEmpty() ? 0.0f : ingredientPrices.get(0));
        when(ingredient1.getType()).thenReturn(ingredientTypes.isEmpty() ? IngredientType.SAUCE : ingredientTypes.get(0));

        burger.addIngredient(ingredient1);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.ingredients.add(ingredient1);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientInvalidIndex() {
        burger.removeIngredient(0); // Должен выбросить IndexOutOfBoundsException
    }

    @Test
    public void testMoveIngredient() {
        when(ingredient1.getName()).thenReturn("sauce");
        when(ingredient2.getName()).thenReturn("filling");

        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2);

        burger.moveIngredient(0, 1);

        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientInvalidIndex() {
        burger.moveIngredient(0, 0); // Должен выбросить IndexOutOfBoundsException
    }

    @Test
    public void testGetPrice() {
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);

        for (int i = 0; i < ingredientPrices.size(); i++) {
            Ingredient mockIngredient = i == 0 ? ingredient1 : ingredient2;
            when(mockIngredient.getPrice()).thenReturn(ingredientPrices.get(i));
            burger.addIngredient(mockIngredient);
        }

        assertEquals(expectedPrice, burger.getPrice(), 0.0001f);
    }

    @Test
    public void testGetReceipt() {
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);

        for (int i = 0; i < ingredientPrices.size(); i++) {
            Ingredient mockIngredient = i == 0 ? ingredient1 : ingredient2;
            when(mockIngredient.getPrice()).thenReturn(ingredientPrices.get(i));
            when(mockIngredient.getName()).thenReturn(ingredientNames.get(i));
            when(mockIngredient.getType()).thenReturn(ingredientTypes.get(i));
            burger.addIngredient(mockIngredient);
        }

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}