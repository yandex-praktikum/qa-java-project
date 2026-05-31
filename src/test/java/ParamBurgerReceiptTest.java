import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParamBurgerReceiptTest {

    private static class IngredientTestData {
        IngredientType type;
        String name;
        float price;

        IngredientTestData(IngredientType type, String name, float price) {
            this.type = type;
            this.name = name;
            this.price = price;
        }
    }

    private String bunName;
    private float bunPrice;
    private IngredientTestData[] ingredientsData;
    private String expectedReceiptTemplate;

    public ParamBurgerReceiptTest(String bunName, float bunPrice, IngredientTestData[] ingredients, String expectedReceipt) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientsData = ingredients;
        this.expectedReceiptTemplate = expectedReceipt;
    }
    // инициализация Mockito внутри параметризованного класса
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                // бургер с ингредиентами
                {"white bun", 50.0f,
                        new IngredientTestData[]{
                                new IngredientTestData(IngredientType.FILLING, "cutlet", 100.0f),
                                new IngredientTestData(IngredientType.SAUCE, "hot sauce", 30.0f)
                        }, "(==== white bun ====)\r\n" +
                        "= filling cutlet =\r\n" +
                        "= sauce hot sauce =\r\n" +
                        "(==== white bun ====)\r\n" +
                        "\r\nPrice: 230,000000\r\n"},
                // пустой бургер
                {"white bun", 40.0f,
                        new IngredientTestData[0],
                        "(==== white bun ====)\r\n" +
                        "(==== white bun ====)\r\n" +
                        "\r\nPrice: 80,000000\r\n"}
        });
    }

    @Test
    public void shouldGenerateCorrectReceipt() {
// Создаем мок булочки
        Bun mockBun = Mockito.mock(Bun.class);
        Mockito.when(mockBun.getName()).thenReturn(bunName);
        Mockito.when(mockBun.getPrice()).thenReturn(bunPrice);

        Burger burger = new Burger();
        burger.setBuns(mockBun);

// Цикл для создания моков ингредиентов и их настройки
        for (IngredientTestData data : ingredientsData) {
            Ingredient mockIngredient = Mockito.mock(Ingredient.class);
            Mockito.when(mockIngredient.getType()).thenReturn(data.type);
            Mockito.when(mockIngredient.getName()).thenReturn(data.name);
            Mockito.when(mockIngredient.getPrice()).thenReturn(data.price);

            burger.addIngredient(mockIngredient);
        }

        String actualReceipt = burger.getReceipt();
        assertEquals("Чек должен соответствовать ожидаемому формату", expectedReceiptTemplate, actualReceipt);
    }
}
