package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BurgerGetReceiptTest {

    @Mock
    Bun bunMock;//мок нельзя создать как локальную переменную внутри уже тестового метода
    //надо еще продумать дальнейшую логику
    @Mock
    Ingredient ingredient1;
    @Mock
    Ingredient ingredient2;

    @DisplayName("getReceipt вызывает методы bun.getName и ingredient.getName")
    @Test
    public void getReceiptCallsGetNameForBunAndIngredients() {
        Burger burger = new Burger();
        //назначили булочку для бургера
        burger.setBuns(bunMock);
        //добавлю ингредиенты
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        //создаем условие для стаба
        Mockito.when(bunMock.getName()).thenReturn("KingBun");
        Mockito.when(ingredient1.getName()).thenReturn("Milk Sausage");
        Mockito.when(ingredient2.getName()).thenReturn("Ketchup");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);

        //вызову метод getReceipt for burger
        String receipt = burger.getReceipt();

        // проверю  вызов методов у моков
        Mockito.verify(bunMock, Mockito.times(2)).getName();
        Mockito.verify(ingredient1, Mockito.times(1)).getName();
        Mockito.verify(ingredient2, Mockito.times(1)).getName();
        //
        assertTrue(receipt.contains("KingBun"));
        assertTrue(receipt.contains("Milk Sausage"));
        assertTrue(receipt.contains("Ketchup"));


    }
}
