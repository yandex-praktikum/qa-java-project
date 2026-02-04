package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@Story("Ingredient Tests")
@RunWith(Parameterized.class)
public class IngredientTest {

    private Ingredient ingredient;
    private final String name;
    private final IngredientType type;
    private final float price;

    public IngredientTest(String name, IngredientType type, float price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Ingredient: {0}, Type: {1}, Price: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Биокотлета",             IngredientType.FILLING, 24.0f },
                { "Соус Spicy-X",           IngredientType.SAUCE,   90.0f },
                { "Сыр с астероидов",       IngredientType.FILLING, 4200.0f },
                { "Флюоресцентная булка",   IngredientType.FILLING, 26.0f },
                { "Минеральное масло",      IngredientType.SAUCE,   867.0f },
                { "Солод",                  IngredientType.SAUCE,   90.0f },
                { "Нутрий-рpolonium",       IngredientType.FILLING, 100.0f }
        });
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    @Description("Проверка: имя ингредиента сохраняется корректно")
    public void testIngredientNameIsSet() {
        assertThat(ingredient.getName(), equalTo(name));
    }

    @Test
    @Description("Проверка: тип ингредиента сохраняется корректно")
    public void testIngredientTypeIsSet() {
        assertThat(ingredient.getType(), equalTo(type));
    }

    @Test
    @Description("Проверка: цена ингредиента сохраняется корректно")
    public void testIngredientPriceIsSet() {
        assertThat(ingredient.getPrice(), equalTo(price));
    }

    @Test
    @Description("Проверка: ингредиент типа FILLING")
    public void testFillingIngredient() {
        Ingredient fillingIngredient = new Ingredient(IngredientType.FILLING, "Котлета", 50.0f);
        assertThat(fillingIngredient.getType(), equalTo(IngredientType.FILLING));
    }

    @Test
    @Description("Проверка: ингредиент типа SAUCE")
    public void testSauceIngredient() {
        Ingredient sauceIngredient = new Ingredient(IngredientType.SAUCE, "Соус", 80.0f);
        assertThat(sauceIngredient.getType(), equalTo(IngredientType.SAUCE));
    }

    @Test
    @Description("Проверка: дорогой ингредиент")
    public void testExpensiveIngredient() {
        Ingredient expensive = new Ingredient(IngredientType.FILLING, "Золотой ингредиент", 10000.0f);
        assertThat(expensive.getPrice(), greaterThan(1000.0f));
    }

    @Test
    @Description("Проверка: дешёвый ингредиент")
    public void testCheapIngredient() {
        Ingredient cheap = new Ingredient(IngredientType.SAUCE, "Дешёвый ингредиент", 1.0f);
        assertThat(cheap.getPrice(), lessThan(10.0f));
    }

    @Test
    @Description("Проверка: ингредиент с нулевой ценой")
    public void testIngredientWithZeroPrice() {
        Ingredient zeroPriceIngredient = new Ingredient(IngredientType.SAUCE, "Бесплатный", 0.0f);
        assertThat(zeroPriceIngredient.getPrice(), equalTo(0.0f));
    }

    @Test
    @Description("Проверка: разные ингредиенты одного типа")
    public void testDifferentIngredientsSameType() {
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "Соус 1", 10.0f);
        Ingredient ingredient2 = new Ingredient(IngredientType.SAUCE, "Соус 2", 20.0f);

        assertThat(ingredient1.getType(), equalTo(ingredient2.getType()));
        assertNotEquals(ingredient1.getPrice(), ingredient2.getPrice(), 0.01f);
    }
}
