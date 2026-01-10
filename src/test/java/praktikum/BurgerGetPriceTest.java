package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class BurgerGetPriceTest {

        @Mock
        Bun bunMock;//мок нельзя создать как локальную переменную внутри уже тестового метода
        //надо еще продумать дальнейшую логику
        @Mock
        Ingredient ingredient1;
        @Mock
        Ingredient ingredient2;

        @DisplayName("getPrice вызывает методы bun.getPrice и ingredient.getPrice")
        @Test
        public void getPriceForBurgerRevokesGetPriceForBunAndIngredients() {
            Burger burger = new Burger();
            //назначили булочку для бургера
            burger.setBuns(bunMock);
            //добавлю ингредиенты
            burger.addIngredient(ingredient1);
            burger.addIngredient(ingredient2);
            //создаем условие для стаба
            Mockito.when(bunMock.getPrice()).thenReturn(3.0f);
            Mockito.when(ingredient1.getPrice()).thenReturn(4.7f);
            Mockito.when(ingredient2.getPrice()).thenReturn(4.9f);
            // ожидаемая цена: булочка*2 + ингредиенты
            float expectedPrice = 3.0f * 2 + 4.7f + 4.9f; // 15.6
            //assertions
            assertEquals(expectedPrice, burger.getPrice(), 0.001f);
            // проверю еще вызов методов getPrice у моков
            Mockito.verify(bunMock, Mockito.times(1)).getPrice();
            Mockito.verify(ingredient1, Mockito.times(1)).getPrice();
            Mockito.verify(ingredient2, Mockito.times(1)).getPrice();


        }
}
