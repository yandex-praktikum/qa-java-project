package praktikum;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    @Mock
    private Ingredient ingredientMockTwo;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBuns() {
        burger.setBuns(bunMock);
        assertEquals("Булочка должна успешно устанавливаться", bunMock, burger.bun);
    }

    @Test
    public void addIngredientIncreasesListSize() {
        burger.addIngredient(ingredientMock);
        assertEquals("Размер списка ингредиентов должен быть 1", 1, burger.ingredients.size());
    }

    @Test
    public void addIngredientAddsCorrectElement() {
        burger.addIngredient(ingredientMock);
        assertEquals("Список должен содержать добавленный ингредиент", ingredientMock, burger.ingredients.get(0));
    }


    @Test
    public void removeIngredient() {
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertEquals("Список ингредиентов должен быть пуст после удаления",0, burger.ingredients.size());
    }

    @Test
    public void moveIngredient() {
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMockTwo);

        burger.moveIngredient(0, 1);

        assertEquals("Второй ингредиент должен переместиться на первую позицию", ingredientMockTwo, burger.ingredients.get(0));
    }


    @Test
    public void getPrice() {
        Mockito.when(bunMock.getPrice()).thenReturn(100.0f);
        Mockito.when(ingredientMock.getPrice()).thenReturn(50.0f);
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        float actualPrice = burger.getPrice();
        assertEquals("Итоговая цена бургера рассчитана неверно", Float.valueOf(250.0f), Float.valueOf(actualPrice));
    }

    @Test
    public void getReceipt() {
        Mockito.when(bunMock.getName()).thenReturn("tastyBurger");
        Mockito.when(ingredientMock.getName()).thenReturn("ketchup");
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(bunMock.getPrice()).thenReturn(0.0f);
        Mockito.when(ingredientMock.getPrice()).thenReturn(0.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String expectedReceipt = String.format("(==== tastyBurger ====)%n= sauce ketchup =%n(==== tastyBurger ====)%n%nPrice: %f%n", 0.0f);

        assertEquals("Формат или содержимое чека не совпадает с ожидаемым шаблоном", expectedReceipt, burger.getReceipt());
    }
}


