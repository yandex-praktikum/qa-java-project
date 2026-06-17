package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BurgerPriceParameterizedTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    static Stream<Arguments> priceTestData() {
        return Stream.of(
                // bunPrice, ingredient1Price, ingredient2Price, expectedPrice
                Arguments.of(100f, 50f, 75f, 325f),    // 200 + 50 + 75 = 325
                Arguments.of(200f, 100f, 150f, 650f),  // 400 + 100 + 150 = 650
                Arguments.of(50f, 25f, 30f, 155f),     // 100 + 25 + 30 = 155
                Arguments.of(150f, 0f, 50f, 350f)      // 300 + 0 + 50 = 350
        );
    }

    @ParameterizedTest
    @MethodSource("priceTestData")
    @DisplayName("Параметризованный тест: расчёт цены с разными значениями")
    public void testPriceCalculationWithMocks(float bunPrice, float ing1Price, float ing2Price, float expected) {
        // Настройка моков
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockIngredient1.getPrice()).thenReturn(ing1Price);
        when(mockIngredient2.getPrice()).thenReturn(ing2Price);

        // Создаём реальный Burger
        Burger realBurger = new Burger();
        realBurger.setBuns(mockBun);
        realBurger.addIngredient(mockIngredient1);
        realBurger.addIngredient(mockIngredient2);

        float actualPrice = realBurger.getPrice();

        // Проверяем результат
        Assertions.assertEquals(expected, actualPrice, 0.001);

        // Проверяем, что методы вызвались
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
    }
}