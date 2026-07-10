import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParamTest {

    //теперь в параметрах не сами объекты, а данные для их мокирования
    private final String bunName;
    private final float bunPrice;
    private final List<IngredientMockData> ingredientsData;
    private final String expectedReceipt;

    public BurgerParamTest(String bunName, float bunPrice, List<IngredientMockData> ingredientsData, String expectedReceipt) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientsData = ingredientsData;
        this.expectedReceipt = expectedReceipt;
    }

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();

        //создаем мок булки и настраиваем его
        Bun bunMock = Mockito.mock(Bun.class);
        when(bunMock.getName()).thenReturn(bunName);
        when(bunMock.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bunMock);

        //создаем моки ингредиентов и добавляем в бургер
        for (IngredientMockData data : ingredientsData) {
            Ingredient ingMock = Mockito.mock(Ingredient.class);
            when(ingMock.getType()).thenReturn(data.type);
            when(ingMock.getName()).thenReturn(data.name);
            when(ingMock.getPrice()).thenReturn(data.price);
            burger.addIngredient(ingMock);
        }
    }

    @Parameterized.Parameters(name = "receipt_test_{index}")
    public static Collection<Object[]> data() {
        String ls = System.lineSeparator();

        return Arrays.asList(new Object[][]{
                //бургер только с булкой
                {
                        "Black Bun",
                        100f,
                        new ArrayList<>(),
                        "(==== Black Bun ====" + ls +
                                "(==== Black Bun ====" + ls +
                                ls +
                                "Price: " + String.format("%.6f", 100f * 2) + ls
                },

                //бургер с одним ингредиентом
                {
                        "White Bun",
                        200f,
                        Arrays.asList(new IngredientMockData(IngredientType.SAUCE, "sour cream", 200f)),
                        "(==== White Bun ====" + ls +
                                "= sauce sour cream =" + ls +
                                "(==== White Bun ====" + ls +
                                ls +
                                "Price: " + String.format("%.6f", (200f * 2 + 200f)) + ls
                },

                //бургер с несколькими ингредиентами
                {
                        "Red Bun",
                        300f,
                        Arrays.asList(
                                new IngredientMockData(IngredientType.FILLING, "cutlet", 100f),
                                new IngredientMockData(IngredientType.SAUCE, "hot sauce", 100f)
                        ),
                        "(==== Red Bun ====" + ls +
                                "= filling cutlet =" + ls +
                                "= sauce hot sauce =" + ls +
                                "(==== Red Bun ====" + ls +
                                ls +
                                "Price: " + String.format("%.6f", (300f * 2 + 100f + 100f)) + ls
                }
        });
    }

    @Test
    public void testGetReceipt() {
        String actual = burger.getReceipt();
        Assert.assertEquals(expectedReceipt, actual);
    }

    //вспомогательный класс-контейнер для данных ингредиента (этот маленький класс живет только внутри BurgerParamTest)
    private static class IngredientMockData {
        final IngredientType type;
        final String name;
        final float price;

        IngredientMockData(IngredientType type, String name, float price) {
            this.type = type;
            this.name = name;
            this.price = price;
        }
    }
}


