package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Enclosed.class)
public class BurgerTest {

    public static class BasicOperationsTest {

        private Burger burger;

        @Before
        public void setUp() {
            burger = new Burger();
        }

        @Test
        public void setBunsShouldSetBunField() {
            Bun bun = mock(Bun.class);

            burger.setBuns(bun);

            assertSame("Bun должен быть установлен в поле bun", bun, burger.bun);
        }

        @Test
        public void addIngredientShouldAddIngredientToList() {
            Ingredient ingredient = mock(Ingredient.class);

            burger.addIngredient(ingredient);

            assertSame("Добавленный ингредиент должен быть в списке", ingredient, burger.ingredients.get(0));
        }

        @Test
        public void addIngredientShouldIncreaseIngredientsCount() {
            Ingredient ingredient = mock(Ingredient.class);

            burger.addIngredient(ingredient);

            assertEquals("После добавления должен быть 1 ингредиент", 1, burger.ingredients.size());
        }

        @Test
        public void removeIngredientShouldDecreaseIngredientsCount() {
            Ingredient firstIngredient = mock(Ingredient.class);
            Ingredient secondIngredient = mock(Ingredient.class);

            burger.addIngredient(firstIngredient);
            burger.addIngredient(secondIngredient);

            burger.removeIngredient(0);

            assertEquals("После удаления должен остаться 1 ингредиент", 1, burger.ingredients.size());
        }

        @Test
        public void removeIngredientShouldRemoveIngredientByIndex() {
            Ingredient firstIngredient = mock(Ingredient.class);
            Ingredient secondIngredient = mock(Ingredient.class);

            burger.addIngredient(firstIngredient);
            burger.addIngredient(secondIngredient);

            burger.removeIngredient(0);

            assertSame("После удаления по индексу 0 должен остаться второй ингредиент",
                    secondIngredient, burger.ingredients.get(0));
        }

        @Test
        public void moveIngredientShouldMoveIngredientToNewIndex() {
            Ingredient firstIngredient = mock(Ingredient.class);
            Ingredient secondIngredient = mock(Ingredient.class);
            Ingredient thirdIngredient = mock(Ingredient.class);

            burger.addIngredient(firstIngredient);
            burger.addIngredient(secondIngredient);
            burger.addIngredient(thirdIngredient);

            burger.moveIngredient(1, 0);

            assertEquals("Ингредиенты должны поменять порядок",
                    Arrays.asList(secondIngredient, firstIngredient, thirdIngredient),
                    burger.ingredients);
        }

        @Test
        public void getPriceWithoutIngredientsShouldReturnBunPriceTimesTwo() {
            Bun bun = mock(Bun.class);
            when(bun.getPrice()).thenReturn(100f);

            burger.setBuns(bun);

            assertEquals("Цена должна быть bunPrice * 2", 200f, burger.getPrice(), 0.0001f);
        }

        @Test
        public void getPriceWithIngredientsShouldReturnBunTimesTwoPlusIngredientsSum() {
            Bun bun = mock(Bun.class);
            when(bun.getPrice()).thenReturn(100f);

            Ingredient sauceIngredient = mock(Ingredient.class);
            when(sauceIngredient.getPrice()).thenReturn(50f);

            Ingredient fillingIngredient = mock(Ingredient.class);
            when(fillingIngredient.getPrice()).thenReturn(70f);

            burger.setBuns(bun);
            burger.addIngredient(sauceIngredient);
            burger.addIngredient(fillingIngredient);

            float expectedPrice = 100f * 2 + 50f + 70f;

            assertEquals("Цена должна быть bun*2 + сумма ингредиентов", expectedPrice, burger.getPrice(), 0.0001f);
        }

        @Test
        public void getReceiptWithoutIngredientsShouldStartWithHeader() {
            Bun bun = mock(Bun.class);
            when(bun.getName()).thenReturn("black bun");
            when(bun.getPrice()).thenReturn(100f);

            burger.setBuns(bun);

            String receipt = burger.getReceipt();
            String header = String.format("(==== %s ====)%n", "black bun");

            assertTrue("Чек должен начинаться с заголовка", receipt.startsWith(header));
        }

        @Test
        public void getReceiptWithoutIngredientsShouldContainHeader() {
            Bun bun = mock(Bun.class);
            when(bun.getName()).thenReturn("black bun");
            when(bun.getPrice()).thenReturn(100f);

            burger.setBuns(bun);

            String receipt = burger.getReceipt();
            String header = String.format("(==== %s ====)%n", "black bun");

            assertTrue("Чек должен содержать заголовок", receipt.contains(header));
        }

        @Test
        public void getReceiptWithoutIngredientsShouldContainPriceLine() {
            Bun bun = mock(Bun.class);
            when(bun.getName()).thenReturn("black bun");
            when(bun.getPrice()).thenReturn(100f);

            burger.setBuns(bun);

            String receipt = burger.getReceipt();
            float expectedPrice = 200f;

            assertTrue("Чек должен содержать строку с ценой",
                    receipt.contains(String.format("%nPrice: %f%n", expectedPrice)));
        }
    }

    @RunWith(Parameterized.class)
    public static class ReceiptParameterizedTest {

        private final IngredientType ingredientType;
        private final String expectedLowercaseType;

        public ReceiptParameterizedTest(IngredientType ingredientType, String expectedLowercaseType) {
            this.ingredientType = ingredientType;
            this.expectedLowercaseType = expectedLowercaseType;
        }

        @Parameterized.Parameters(name = "{index}: type={0} -> {1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {IngredientType.SAUCE, "sauce"},
                    {IngredientType.FILLING, "filling"}
            });
        }

        @Test
        public void getReceiptShouldContainIngredientLineWithLowercaseType() {
            Burger burger = new Burger();

            Bun bun = mock(Bun.class);
            when(bun.getName()).thenReturn("black bun");
            when(bun.getPrice()).thenReturn(100f);

            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getType()).thenReturn(ingredientType);
            when(ingredient.getName()).thenReturn("hot sauce");
            when(ingredient.getPrice()).thenReturn(50f);

            burger.setBuns(bun);
            burger.addIngredient(ingredient);

            String receipt = burger.getReceipt();
            String expectedLine = String.format("= %s %s =%n", expectedLowercaseType, "hot sauce");

            assertTrue("Чек должен содержать строку ингредиента в нужном формате",
                    receipt.contains(expectedLine));
        }
    }
}
