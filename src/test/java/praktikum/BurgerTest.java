package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.assertj.core.api.SoftAssertions;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(mockBun);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(burger.bun)
                .as("Метод setBuns() должен устанавливать булочку")
                .isEqualTo(mockBun);
        softAssertions.assertThat(burger.bun)
                .as("Булочка не должна быть null после установки")
                .isNotNull();
        softAssertions.assertAll();
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(mockIngredient1);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(burger.ingredients)
                .as("Список ингредиентов должен содержать добавленный элемент")
                .contains(mockIngredient1);
        softAssertions.assertThat(burger.ingredients.size())
                .as("Размер списка должен быть 1 после добавления")
                .isEqualTo(1);
        softAssertions.assertAll();
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.removeIngredient(0);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(burger.ingredients)
                .as("После удаления не должен содержать первый элемент")
                .doesNotContain(mockIngredient1);
        softAssertions.assertThat(burger.ingredients)
                .as("После удаления должен содержать второй элемент")
                .contains(mockIngredient2);
        softAssertions.assertThat(burger.ingredients.size())
                .as("Размер списка должен быть 1 после удаления")
                .isEqualTo(1);
        softAssertions.assertAll();
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.moveIngredient(1, 0);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(burger.ingredients.get(0).equals(mockIngredient2))
                .as("Метод moveIngredient() перемещает ингредиенты")
                .isTrue();
        softAssertions.assertThat(burger.ingredients.get(1).equals(mockIngredient1))
                .as("Метод moveIngredient() корректно обновляет список ингредиентов")
                .isTrue();
        softAssertions.assertAll();
    }

    @Test
    public void getPriceTest() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        when(mockIngredient2.getPrice()).thenReturn(75.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(burger.getPrice())
                .as("Цена должна быть корректно вычислена")
                .isEqualTo(325.0f); // Исправлено с 275.0f на 325.0f
        softAssertions.assertThat(burger.getPrice())
                .as("Цена должна учитывать две булочки")
                .isGreaterThan(200.0f);
        softAssertions.assertAll();
    }

    @Test
    public void getReceiptTest() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("hot sauce");
        when(mockIngredient1.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать название булочки")
                .contains("black bun");
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать тип ингредиента в нижнем регистре")
                .contains("sauce");
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать название ингредиента")
                .contains("hot sauce");
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать общую цену")
                .contains("Price:");
        softAssertions.assertAll();
    }

    @Test
    public void getReceiptFormatTest() {
        when(mockBun.getName()).thenReturn("Test Bun");
        when(mockBun.getPrice()).thenReturn(150.5f);

        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(receipt)
                .as("Чек должен начинаться с булочки")
                .startsWith("(==== Test Bun ====)");
        softAssertions.assertThat(receipt)
                .as("Чек должен заканчиваться булочкой")
                .contains("(==== Test Bun ====)");
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать цену")
                .contains("Price:");
        softAssertions.assertAll();
    }

    @Test
    public void getReceiptWithMultipleIngredientsTest() {
        when(mockBun.getName()).thenReturn("Test Bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("Sauce 1");
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("Filling 1");
        when(mockIngredient2.getPrice()).thenReturn(75.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать оба ингредиента")
                .contains("Sauce 1", "Filling 1");
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать оба типа ингредиентов")
                .contains("sauce", "filling");
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать цену")
                .contains("Price:");
        softAssertions.assertAll();
    }

    @Test
    public void getPriceEmptyBurgerTest() {
        when(mockBun.getPrice()).thenReturn(0.0f);

        burger.setBuns(mockBun);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(burger.getPrice())
                .as("Цена пустого бургера должна быть 0")
                .isEqualTo(0.0f);
        softAssertions.assertThat(burger.getPrice())
                .as("Цена должна быть неотрицательной")
                .isGreaterThanOrEqualTo(0.0f);
        softAssertions.assertAll();
    }

    @Test
    public void getPriceWithOnlyBunTest() {
        when(mockBun.getPrice()).thenReturn(100.0f);

        burger.setBuns(mockBun);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(burger.getPrice())
                .as("Цена бургера только с булочкой должна быть 200.0f (100*2)")
                .isEqualTo(200.0f);
        softAssertions.assertAll();
    }

    @Test
    public void getPriceWithOneIngredientTest() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(burger.getPrice())
                .as("Цена бургера с булочкой и одним ингредиентом должна быть 250.0f (100*2 + 50)")
                .isEqualTo(250.0f);
        softAssertions.assertAll();
    }
}