package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockSauce;
    @Mock
    private Ingredient mockFilling;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertSame("Булка должна установиться", mockBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockSauce);
        assertEquals("Должен добавиться 1 ингредиент", 1, burger.ingredients.size());
        assertSame("Добавленный ингредиент должен быть тем же", mockSauce, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        assertEquals(2, burger.ingredients.size());

        burger.removeIngredient(0);
        assertEquals("После удаления должен остаться 1 ингредиент", 1, burger.ingredients.size());
        assertSame("Оставшийся ингредиент должен быть вторым", mockFilling, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockSauce);     // index 0
        burger.addIngredient(mockFilling);   // index 1

        burger.moveIngredient(0, 1); // перемещаем sauce на место filling

        assertSame("На позиции 0 должен быть filling", mockFilling, burger.ingredients.get(0));
        assertSame("На позиции 1 должен быть sauce", mockSauce, burger.ingredients.get(1));
    }

    @Test
    public void testGetPriceWithMocks() {
        // Настраиваем моки
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockSauce.getPrice()).thenReturn(50f);
        when(mockFilling.getPrice()).thenReturn(75f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        float expectedPrice = (100f * 2) + 50f + 75f; // 200 + 125 = 325
        float actualPrice = burger.getPrice();

        assertEquals("Цена должна считаться правильно", expectedPrice, actualPrice, 0.001);

        // Проверяем, что методы были вызваны
        verify(mockBun, times(1)).getPrice();
        verify(mockSauce, times(1)).getPrice();
        verify(mockFilling, times(1)).getPrice();
    }

    @Test
    public void testGetPriceWithoutIngredients() {
        when(mockBun.getPrice()).thenReturn(150f);
        burger.setBuns(mockBun);

        float expectedPrice = 150f * 2; // 300
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetPriceWithoutBun() {
        when(mockSauce.getPrice()).thenReturn(100f);
        burger.addIngredient(mockSauce);

        // bun == null, поэтому при вычислении цены будет NullPointerException
        // Проверяем, что без булки метод getPrice() падает
        assertThrows(NullPointerException.class, () -> burger.getPrice());
    }

    // Параметризованный тест для getReceipt
    @RunWith(Parameterized.class)
    public static class BurgerReceiptTest {

        private final String bunName;
        private final List<Ingredient> ingredients;
        private final String expectedReceipt;

        public BurgerReceiptTest(String bunName, List<Ingredient> ingredients, String expectedReceipt) {
            this.bunName = bunName;
            this.ingredients = ingredients;
            this.expectedReceipt = expectedReceipt;
        }

        @Parameterized.Parameters
        public static Object[][] data() {
            // Создаем моки ингредиентов
            Ingredient sauceMock = mock(Ingredient.class);
            when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);
            when(sauceMock.getName()).thenReturn("hot sauce");

            Ingredient fillingMock = mock(Ingredient.class);
            when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
            when(fillingMock.getName()).thenReturn("cutlet");

            return new Object[][]{
                    {"black bun", Arrays.asList(),
                            String.format("(==== black bun ====)%n%n(==== black bun ====)%n%nPrice: 0,000000%n")},

                    {"white bun", Arrays.asList(sauceMock),
                            String.format("(==== white bun ====)%n= sauce hot sauce =%n(==== white bun ====)%n%nPrice: 0,000000%n")},

                    {"red bun", Arrays.asList(fillingMock),
                            String.format("(==== red bun ====)%n= filling cutlet =%n(==== red bun ====)%n%nPrice: 0,000000%n")},

                    {"black bun", Arrays.asList(sauceMock, fillingMock),
                            String.format("(==== black bun ====)%n= sauce hot sauce =%n= filling cutlet =%n(==== black bun ====)%n%nPrice: 0,000000%n")}
            };
        }

        @Test
        public void testGetReceipt() {
            Burger burger = new Burger();
            Bun bun = new Bun(bunName, 0f);
            burger.setBuns(bun);

            for (Ingredient ingredient : ingredients) {
                burger.addIngredient(ingredient);
            }

            String actualReceipt = burger.getReceipt();
            assertEquals("Чек должен соответствовать ожидаемому", expectedReceipt, actualReceipt);
        }
    }

    // Тест с реальными данными как в main методе Praktikum
    @Test
    public void testBurgerFromMainMethod() {
        Database database = new Database();
        Burger burger = new Burger();

        List<Bun> buns = database.availableBuns();
        List<Ingredient> ingredients = database.availableIngredients();

        burger.setBuns(buns.get(0)); // black bun

        burger.addIngredient(ingredients.get(1)); // sour cream
        burger.addIngredient(ingredients.get(4)); // dinosaur
        burger.addIngredient(ingredients.get(3)); // cutlet
        burger.addIngredient(ingredients.get(5)); // sausage

        burger.moveIngredient(2, 1); // перемещаем cutlet на позицию 1
        burger.removeIngredient(3);  // удаляем sausage

        // Проверяем итоговый состав
        assertEquals("black bun", burger.bun.getName());
        assertEquals(3, burger.ingredients.size());
        assertEquals("sour cream", burger.ingredients.get(0).getName());
        assertEquals("cutlet", burger.ingredients.get(1).getName());
        assertEquals("dinosaur", burger.ingredients.get(2).getName());

        // Проверяем цену: булка 100*2 + sour cream(200) + cutlet(100) + dinosaur(200) = 200 + 500 = 700
        assertEquals(700f, burger.getPrice(), 0.001);
    }
}