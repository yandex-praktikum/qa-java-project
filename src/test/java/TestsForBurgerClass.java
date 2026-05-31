import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestsForBurgerClass {
    // Создаем мок булочки
    @Mock
    private Bun mockBun;

    // Создаем мок первого ингредиента
    @Mock
    private Ingredient firstMockIngredient;

    // Создаем мок второго ингредиента
    @Mock
    private Ingredient secondMockIngredient;

// тестирование метода добавления ингредиента
    @Test
    public void shouldAddIngredientToBurger() {
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(firstMockIngredient);
        assertEquals(1, burger.ingredients.size());
    }
// тестирование метода удаления ингредиента
    @Test
    public void shouldRemoveIngredientFromBurger() {
    Burger burger = new Burger();
    burger.setBuns(mockBun);
// добавляем мок-ингредиенты
    burger.addIngredient(firstMockIngredient);
    burger.addIngredient(firstMockIngredient);
    burger.addIngredient(firstMockIngredient);

    burger.removeIngredient(0);
    assertEquals(2, burger.ingredients.size());
    }
// тестирование метода перемещения ингредиентов
    @Test
    public void shouldMoveIngredientInBurger() {
        Burger burger = new Burger();
        burger.setBuns(mockBun);

        burger.addIngredient(firstMockIngredient);
        burger.addIngredient(secondMockIngredient);

        burger.moveIngredient(1, 0);
        assertEquals("Порядок ингредиентов после перемещения не соответствует ожидаемому",
                secondMockIngredient, burger.ingredients.get(0));
    }

    // тестирование метода получения цены
    @Test
    public void shouldCalculateCorrectlyPriceOfBurger(){
        Burger burger = new Burger();

// Настраиваем моки
        Mockito.when(mockBun.getPrice()).thenReturn(100f);
        Mockito.when(firstMockIngredient.getPrice()).thenReturn(150f);


        burger.setBuns(mockBun);
        burger.addIngredient(firstMockIngredient);
        float expectedBurgerPrice = 350f;
        float actualBurgerPrice = burger.getPrice();
        assertEquals(expectedBurgerPrice,actualBurgerPrice,0.001f);
    }
}
