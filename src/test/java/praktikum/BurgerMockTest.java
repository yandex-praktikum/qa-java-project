package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.SAUCE;

@ExtendWith(MockitoExtension.class) // Подключаем поддержку Mockito в JUnit 5
class BurgerMockTest {

    @Mock
    Bun bunMock; // Мок объекта Bun

    @Mock
    Ingredient ingredient; // Мок объекта Ingredient

    Burger burger; // Тестируемый объект Burger

    @BeforeEach
    void setUp() {
        burger = new Burger(); // Инициализация нового бургера перед каждым тестом
    }

    @Test
    @DisplayName("Добавление булочки для бургера")
    void setBuns() {
        burger.setBuns(bunMock); // Устанавливаем булочку-мок в бургер
        assertEquals(bunMock, burger.bun); // Проверяем, что булочка установлена корректно
    }

    @Test
    @DisplayName("Получение цены бургера")
    void getPrice() {
        burger.bun = bunMock; // Устанавливаем булочку-мок напрямую
        Mockito.when(bunMock.getPrice()).thenReturn(11f); // Мокаем цену булочки
        burger.addIngredient(ingredient); // Добавляем ингредиент-мок
        Mockito.when(ingredient.getPrice()).thenReturn(22f); // Мокаем цену ингредиента

        assertEquals(44f, burger.getPrice()); // Проверяем, что сумма цен корректна (булочка + 2 * ингредиент)
    }

    @Test
    @DisplayName("Получение чека с информацией о бургере")
    void getReceipt() {
        burger.bun = bunMock; // Устанавливаем булочку-мок
        Mockito.when(bunMock.getName()).thenReturn("white bun"); // Мокаем имя булочки
        Mockito.when(bunMock.getPrice()).thenReturn(11f); // Мокаем цену булочки
        burger.addIngredient(ingredient); // Добавляем ингредиент-мок
        Mockito.when(ingredient.getType()).thenReturn(SAUCE); // Мокаем тип ингредиента
        Mockito.when(ingredient.getName()).thenReturn("sour cream"); // Мокаем имя ингредиента
        Mockito.when(ingredient.getPrice()).thenReturn(22f); // Мокаем цену ингредиента

        String expectedReceipt = "(==== white bun ====)\n" + // Ожидаемый чек: верхняя булочка
                "= sauce sour cream =\n" +                   // Ингредиент
                "(==== white bun ====)\n\n" +                 // Нижняя булочка
                "Price: 44,000000\n";                         // Итоговая цена
        assertEquals(expectedReceipt, burger.getReceipt()); // Проверяем соответствие чека
    }
}
