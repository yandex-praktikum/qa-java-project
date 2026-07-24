package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
//qa
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;

     @Mock
    private Ingredient mockSauce;

    @Mock
    private Ingredient mockFilling;

    @Mock
    private Ingredient mockCheese;

    @Test
    public void testSetBunsAndAddIngredient() {
        Burger burger = new Burger();

        when(mockBun.getPrice()).thenReturn(2.0f);
        when(mockSauce.getPrice()).thenReturn(5.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);

        float expectedPrice = 2.0f * 2 + 5.0f; // 4 + 5 = 9
        assertEquals("Цена бургера должна быть 9", expectedPrice, burger.getPrice(), 0.001f);
    }


    @Test
    public void testPriceBeforeRemovingIngredient() {
          Burger burger = new Burger();

        when(mockBun.getPrice()).thenReturn(2.0f);
        when(mockSauce.getPrice()).thenReturn(5.0f);
        when(mockFilling.getPrice()).thenReturn(3.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        float expectedPrice = 2.0f * 2 + 5.0f + 3.0f; // 12
        assertEquals("Цена до удаления должна быть 12", expectedPrice, burger.getPrice(), 0.001f);
    }


    @Test
    public void testPriceAfterRemovingIngredient() {
        Burger burger = new Burger();

        when(mockBun.getPrice()).thenReturn(2.0f);
        when(mockFilling.getPrice()).thenReturn(3.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        burger.removeIngredient(0);

        float expectedPrice = 2.0f * 2 + 3.0f; // 7
        assertEquals("Цена после удаления должна быть 7", expectedPrice, burger.getPrice(), 0.001f);
    }


    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();

        when(mockBun.getName()).thenReturn("Булочка");
        when(mockBun.getPrice()).thenReturn(1.0f);
        when(mockSauce.getName()).thenReturn("Соус 1");
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getPrice()).thenReturn(1.0f);
        when(mockFilling.getName()).thenReturn("Начинка 2");
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockFilling.getPrice()).thenReturn(2.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        burger.moveIngredient(0, 1);

        // Нормализуем строки: заменяем \r\n на \n
        String actual = burger.getReceipt().replace("\r\n", "\n");
        String expected = "(==== Булочка ====)\n" +
                "= filling Начинка 2 =\n" +
                "= sauce Соус 1 =\n" +
                "(==== Булочка ====)\n" +
                "\n" +
                "Price: 5,000000\n";

        assertEquals(expected, actual);
    }

    @Test
     public void testGetReceipt() {
        Burger burger = new Burger();

        when(mockBun.getName()).thenReturn("Крафтовая булочка");
        when(mockBun.getPrice()).thenReturn(2.0f);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getName()).thenReturn("Чесночный соус");
        when(mockSauce.getPrice()).thenReturn(5.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);

        String actual = burger.getReceipt().replace("\r\n", "\n");
        String expected = "(==== Крафтовая булочка ====)\n" +
                "= sauce Чесночный соус =\n" +
                "(==== Крафтовая булочка ====)\n" +
                "\n" +
                "Price: 9,000000\n";

        assertEquals(expected, actual);
    }


    @RunWith(Parameterized.class)
    public static class BurgerPriceParameterizedTest {

        private final int ingredientsCount;
        private final float expectedPrice;

        public BurgerPriceParameterizedTest(int ingredientsCount, float expectedPrice) {
            this.ingredientsCount = ingredientsCount;
            this.expectedPrice = expectedPrice;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {0, 4.0f},    // только булка (цена 2*2 = 4)
                    {1, 9.0f},    // булка + 1 ингредиент (4 + 5 = 9)
                    {2, 14.0f}    // булка + 2 ингредиента (4 + 5 + 5 = 14)
            });
        }

        @Test
        public void testGetPriceWithDifferentIngredients() {
            Burger burger = new Burger();
            Bun bun = new Bun("Булочка", 2.0f);
            burger.setBuns(bun);

            for (int i = 0; i < ingredientsCount; i++) {
                burger.addIngredient(new Ingredient(IngredientType.SAUCE, "Соус", 5.0f));
            }

            assertEquals("Цена должна быть " + expectedPrice, expectedPrice, burger.getPrice(), 0.001f);
        }
    }
}