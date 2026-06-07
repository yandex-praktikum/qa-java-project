package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.assertj.core.api.SoftAssertions;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

// Тесты функциональности класса бургер.

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    @Mock
    private Ingredient ingredientMock2;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void shouldInstantiateBurger() {
        assertNotNull("Burger должен успешно создаваться через конструктор по умолчанию", burger);
    }

    //Расчет цены с использованием Mock.
    @Test
    public void shouldCalculatePriceWithMocks() {
        when(bunMock.getPrice()).thenReturn(TestData.PRICE_BUN_DELUXE);
        when(ingredientMock.getPrice()).thenReturn(TestData.PRICE_ING_HEARTY);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        // Then: Ожидаем сумму: 2 булки и 1 ингредиент
        float expected = TestData.PRICE_BUN_DELUXE * 2 + TestData.PRICE_ING_HEARTY;
        assertEquals("Итоговая цена должна складываться из цен моков", expected, burger.getPrice(), TestData.FLOAT_DELTA);
    }

    @Test
    public void shouldStoreBunReference() {
        // Arrange: настраиваем мок
        burger.setBuns(bunMock);
        assertEquals("Ссылка на установленную булку должна сохраняться внутри бургера", bunMock, burger.bun);
    }

    @Test
    public void shouldAddIngredientToList() {
        // Arrange: настраиваем мок
        burger.addIngredient(ingredientMock);
        assertEquals("После добавления ингредиента, список ингредиентов должен содержать 1 элемент", 1, burger.ingredients.size());
    }

    @Test
    public void shouldRemoveIngredientFromList() {
        // Arrange: настраиваем мок
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertEquals("После удаления единственного ингредиента, список должен стать пустым", 0, burger.ingredients.size());
    }

    @Test
    public void shouldMoveIngredient() {
        //моки
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock2);

        burger.moveIngredient(0, 1);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(burger.ingredients.get(0))
                    .as("Первый элемент должен стать вторым (B)")
                    .isEqualTo(ingredientMock2);
            softly.assertThat(burger.ingredients.get(1))
                    .as("Второй элемент должен стать первым (A)")
                    .isEqualTo(ingredientMock);
        });
    }

    // Проверка чека
    @Test
    public void shouldReturnReceiptWithCorrectComponents() {
        when(bunMock.getName()).thenReturn("mocked bun");
        when(bunMock.getPrice()).thenReturn(TestData.PRICE_BUN_DELUXE);
        when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock.getName()).thenReturn("mocked sauce");
        when(ingredientMock.getPrice()).thenReturn(TestData.PRICE_ING_SAUCY);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String actualReceipt = burger.getReceipt();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualReceipt)
                    .as("Чек должен содержать название булки")
                    .contains(bunMock.getName());
            softly.assertThat(actualReceipt)
                    .as("Чек должен содержать тип ингредиента")
                    .contains(ingredientMock.getType().toString().toLowerCase());
            softly.assertThat(actualReceipt)
                    .as("Чек должен содержать название соуса")
                    .contains(ingredientMock.getName());
            softly.assertThat(burger.getPrice())
                    .as("Цена в чеке должна рассчитываться корректно")
                    .isCloseTo(2 * TestData.PRICE_BUN_DELUXE + TestData.PRICE_ING_SAUCY,
                            org.assertj.core.data.Offset.offset(TestData.FLOAT_DELTA));
        });
    }
}