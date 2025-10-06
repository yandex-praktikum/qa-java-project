package praktikum;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
 import java.util.Arrays;
 import java.util.List;
 import static org.junit.Assert.*;
 import static org.mockito.Mockito.*;

 @RunWith(MockitoJUnitRunner.class)
 public class BurgerTest {

     @Mock
     private Bun bun;

     @Mock
     private Ingredient ingredient1;

     @Mock
     private Ingredient ingredient2;

     @Mock
     private Ingredient ingredient3;

     @Test
     public void testSetBuns() {
         Burger burger = new Burger();
         burger.setBuns(bun);

         assertSame(bun, burger.bun);
     }

     @Test
     public void testAddIngredient() {
         Burger burger = new Burger();
         burger.addIngredient(ingredient1);

         assertEquals(1, burger.ingredients.size());
         assertSame(ingredient1, burger.ingredients.get(0));
     }

     @Test
     public void testAddMultipleIngredients() {
         Burger burger = new Burger();
         burger.addIngredient(ingredient1);
         burger.addIngredient(ingredient2);
         burger.addIngredient(ingredient3);

         assertEquals(3, burger.ingredients.size());
         assertSame(ingredient1, burger.ingredients.get(0));
         assertSame(ingredient2, burger.ingredients.get(1));
         assertSame(ingredient3, burger.ingredients.get(2));
     }

     @Test
     public void testRemoveIngredient() {
         Burger burger = new Burger();
         burger.ingredients.addAll(Arrays.asList(ingredient1, ingredient2, ingredient3));

         burger.removeIngredient(1);

         assertEquals(2, burger.ingredients.size());
         assertSame(ingredient1, burger.ingredients.get(0));
         assertSame(ingredient3, burger.ingredients.get(1));
     }

     @Test(expected = IndexOutOfBoundsException.class)
     public void testRemoveIngredientWithInvalidIndex() {
         Burger burger = new Burger();
         burger.ingredients.add(ingredient1);

         burger.removeIngredient(5);
     }

     @Test
     public void testMoveIngredientForward() {
         Burger burger = new Burger();
         burger.ingredients.addAll(Arrays.asList(ingredient1, ingredient2, ingredient3));

         burger.moveIngredient(0, 2);

         assertEquals(3, burger.ingredients.size());
         assertSame(ingredient2, burger.ingredients.get(0));
         assertSame(ingredient3, burger.ingredients.get(1));
         assertSame(ingredient1, burger.ingredients.get(2));
     }

     @Test
     public void testMoveIngredientBackward() {
         Burger burger = new Burger();
         burger.ingredients.addAll(Arrays.asList(ingredient1, ingredient2, ingredient3));

         burger.moveIngredient(2, 0);

         assertEquals(3, burger.ingredients.size());
         assertSame(ingredient3, burger.ingredients.get(0));
         assertSame(ingredient1, burger.ingredients.get(1));
         assertSame(ingredient2, burger.ingredients.get(2));
     }

     @Test(expected = IndexOutOfBoundsException.class)
     public void testMoveIngredientWithInvalidFromIndex() {
         Burger burger = new Burger();
         burger.ingredients.add(ingredient1);

         burger.moveIngredient(5, 0);
     }

     @Test(expected = IndexOutOfBoundsException.class)
      public void testMoveIngredientWithInvalidToIndex() {
         Burger burger = new Burger();
         burger.ingredients.add(ingredient1);

         burger.moveIngredient(0, 5);
     }

     @RunWith(Parameterized.class)
     public static class GetPriceParameterizedTest {

         @Parameterized.Parameters
         public static Object[][] testData() {
             return new Object[][]{
                     {100.0f, new Float[]{50.0f, 30.0f}, 280.0f},
                     {50.0f, new Float[]{10.0f, 20.0f, 5.0f}, 135.0f},
                     {0.0f, new Float[]{}, 0.0f},
                     {25.5f, new Float[]{15.25f}, 66.25f}
             };
         }

         @Parameterized.Parameter(0)
          public float bunPrice;

          @Parameterized.Parameter(1)
         public Float[] ingredientPrices;

         @Parameterized.Parameter(2)
         public float expectedTotalPrice;

         @Test
         public void testGetPriceWithDifferentCombinations() {
             Bun bun = mock(Bun.class);
             when(bun.getPrice()).thenReturn(bunPrice);

             Burger burger = new Burger();
             burger.setBuns(bun);

             for (float ingredientPrice : ingredientPrices) {
                 Ingredient ingredient = mock(Ingredient.class);
                 when(ingredient.getPrice()).thenReturn(ingredientPrice);
                 burger.addIngredient(ingredient);
             }

             float actualPrice = burger.getPrice();

             assertEquals(expectedTotalPrice, actualPrice, 0.001f);
         }
     }

     @Test
     public void testGetReceiptWithBunAndIngredients() {
         when(bun.getName()).thenReturn("white bun");
         when(bun.getPrice()).thenReturn(100.0f);

         when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
         when(ingredient1.getName()).thenReturn("hot sauce");
         when(ingredient1.getPrice()).thenReturn(50.0f);

         when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
         when(ingredient2.getName()).thenReturn("cutlet");
         when(ingredient2.getPrice()).thenReturn(30.0f);

         Burger burger = new Burger();
          burger.setBuns(bun);
         burger.addIngredient(ingredient1);
          burger.addIngredient(ingredient2);

         String expectedReceipt = "(==== white bun ====)\r\n" +
                 "= sauce hot sauce =\r\n" +
                 "= filling cutlet =\r\n" +
                 "(==== white bun ====)\r\n" +
                 "\r\n" +
                 "Price: 280,000000\r\n";

         String actualReceipt = burger.getReceipt();

         assertEquals(expectedReceipt, actualReceipt);
     }

     @Test
     public void testGetReceiptWithOnlyBun() {
         when(bun.getName()).thenReturn("black bun");
         when(bun.getPrice()).thenReturn(50.0f);

         Burger burger = new Burger();
         burger.setBuns(bun);

         String expectedReceipt = "(==== black bun ====)\r\n" +
                 "(==== black bun ====)\r\n" +
                 "\r\n" +
                 "Price: 100,000000\r\n";

         String actualReceipt = burger.getReceipt();

         assertEquals(expectedReceipt, actualReceipt);
     }
      @Test
     public void testGetReceiptCallsGetPrice() {
         when(bun.getName()).thenReturn("test bun");
         when(bun.getPrice()).thenReturn(10.0f);

         Burger burger = new Burger();
         burger.setBuns(bun);

         String receipt = burger.getReceipt();

         assertTrue(receipt.contains("Price: 20,000000"));
         verify(bun, atLeast(2)).getName();
         verify(bun).getPrice();
     }
 }