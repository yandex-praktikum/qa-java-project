import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {

    private Burger burger;

    @Mock
    Bun mockBun;

    @Mock
    Ingredient mockIngredient1;

    @Mock
    Ingredient mockIngredient2;

    @Mock
    Ingredient mockIngredient3;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(mockBun);
        assertEquals("Значение поля bun должно соответствовать переданному объекту Bun", mockBun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(mockIngredient1);
        assertEquals("Ингредиент должен добавиться в список ингредиентов", 1, burger.ingredients.size());
        assertEquals("Добавленный ингредиент должен совпадать с mock-ингредиентом", mockIngredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.ingredients.add(mockIngredient1);
        burger.ingredients.add(mockIngredient2);
        burger.ingredients.add(mockIngredient3);
        burger.removeIngredient(2);
        assertEquals("Должно остаться 2 ингредиента", 2, burger.ingredients.size());
        assertEquals("Первый ингредиент должен остаться", mockIngredient1, burger.ingredients.get(0));
        assertEquals("Последний ингредиент должен сдвинуться", mockIngredient2, burger.ingredients.get(1));
        assertFalse("Второй ингредиент должен быть удален", burger.ingredients.contains(mockIngredient3));
    }

    @Test
    public void moveIngredientTest() {
        burger.ingredients.add(mockIngredient1);
        burger.ingredients.add(mockIngredient2);
        burger.ingredients.add(mockIngredient3);
        burger.moveIngredient(0, 2);
        assertEquals("Количество ингредиентов не должно измениться", 3, burger.ingredients.size());
        assertEquals("Первый элемент должен стать ingredient1", mockIngredient2, burger.ingredients.get(0));
        assertEquals("Второй элемент должен стать ingredient2", mockIngredient3, burger.ingredients.get(1));
        assertEquals("Третий элемент должен стать ingredient0", mockIngredient1, burger.ingredients.get(2));
    }

    @Test
    public void getPriceTest() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        when(mockIngredient2.getPrice()).thenReturn(30.0f);

        burger.bun = mockBun;
        burger.ingredients.add(mockIngredient1);
        burger.ingredients.add(mockIngredient2);

        float actualPrice = burger.getPrice();

        assertEquals("Цена должна быть рассчитана правильно", 280.0f, actualPrice, 0.01f);

        // Verify - проверяем, что методы были вызваны
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
    }

    @Test
    public void getReceiptTest() {

        burger.bun = mockBun;
        burger.ingredients.add(mockIngredient1);
        burger.ingredients.add(mockIngredient2);

        Burger burgerSpy = Mockito.spy(burger);

        when(mockBun.getName()).thenReturn("white bun");
        when(mockIngredient1.getType()).thenReturn(SAUCE);
        when(mockIngredient1.getName()).thenReturn("chili sauce");
        when(mockIngredient2.getType()).thenReturn(FILLING);
        when(mockIngredient2.getName()).thenReturn("dinosaur");
        when(burgerSpy.getPrice()).thenReturn(500.0f);


        String actualReceipt = burgerSpy.getReceipt();

        assertTrue("Чек должен содержать название булки", actualReceipt.contains("white bun"));
        assertTrue("Чек должен содержать соус", actualReceipt.contains("sauce chili sauce"));
        assertTrue("Чек должен содержать начинку", actualReceipt.contains("filling dinosaur"));
        assertTrue("Чек должен содержать цену", actualReceipt.contains("Price: 500"));
        assertTrue("В чеке должены быть правильные разделители", actualReceipt.contains("(===="));

    }
}
