import praktikum.Ingredient;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.IngredientType;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientFirstMock;
    private Ingredient ingredientSecondMock;

@Before
    public void setUp(){
    bunMock = mock(Bun.class);
    when(bunMock.getPrice()).thenReturn(20.0f);
    when(bunMock.getName()).thenReturn("nameBurger");

    ingredientFirstMock = mock(Ingredient.class);
    when(ingredientFirstMock.getType()).thenReturn(IngredientType.SAUCE);
    when(ingredientFirstMock.getName()).thenReturn("CheeseSauce");
    when(ingredientFirstMock.getPrice()).thenReturn(15.0f);

    ingredientSecondMock = mock(Ingredient.class);
    when(ingredientSecondMock.getType()).thenReturn(IngredientType.FILLING);
    when(ingredientSecondMock.getName()).thenReturn("MeatFilling");
    when(ingredientSecondMock.getPrice()).thenReturn(16.0f);

    burger = new Burger();
}

    @Test
    public void setBunsTest(){
    burger.setBuns(bunMock);
    assertEquals("Неправильная булочка", bunMock, burger.bun);
    }
    @Test
    public void addIngredientTest(){
    burger.addIngredient(ingredientFirstMock);
    assertEquals("Ингридиет не добавлен к бургеру", List.of(ingredientFirstMock), burger.ingredients);
    }
    @Test
    public void removeIngredientWhenListHasOneElementRemovesIt(){
    burger.addIngredient(ingredientFirstMock);
    burger.removeIngredient(0);
    assertTrue("Список ингридиентов должен быть пустым после удаления", burger.ingredients.isEmpty());
    }
    @Test
    public void removeIngredientWhenListHasOneElementIsRemovedFromList(){
        burger.addIngredient(ingredientFirstMock);
        burger.removeIngredient(0);
        assertFalse("Ингридиент не долен содержаться в списке после удаления", burger.ingredients.contains(ingredientFirstMock));
    }
    @Test
    public void removeIngredientWhenListHasTwoElementsRemovesElementAtGivenIndex() {
        burger.addIngredient(ingredientFirstMock);
        burger.addIngredient(ingredientSecondMock);
        burger.removeIngredient(1);
        List<Ingredient> expectedAfterRemove = List.of(ingredientFirstMock);
        assertEquals("После удаления второго ингредиента должен остаться только первый", expectedAfterRemove, burger.ingredients);
    }
    @Test
    public void removeIngredientWhenListHasTwoElementsRemovedElementIsNotContained() {
        burger.addIngredient(ingredientFirstMock);
        burger.addIngredient(ingredientSecondMock);
        burger.removeIngredient(1);
        assertFalse("Второй ингредиент должен быть удалён", burger.ingredients.contains(ingredientSecondMock));
    }
    @Test
    public void removeIngredientWhenListHasTwoElementsRemainingElementIsStillContained() {
        burger.addIngredient(ingredientFirstMock);
        burger.addIngredient(ingredientSecondMock);
        burger.removeIngredient(1);
        assertTrue("Первый ингредиент должен остаться в списке", burger.ingredients.contains(ingredientFirstMock));
    }
    @Test
    public void removeIngredientWhenListHasTwoElementsRemovesAllElementsSequentially() {
        burger.addIngredient(ingredientFirstMock);
        burger.addIngredient(ingredientSecondMock);
        burger.removeIngredient(1);
        burger.removeIngredient(0);
        assertTrue("Список должен быть пуст после удаления всех элементов", burger.ingredients.isEmpty());
    }
    @Test
    public void moveIngredientSwapsOrderOfIngredients(){
        burger.addIngredient(ingredientFirstMock);
        burger.addIngredient(ingredientSecondMock);
        burger.moveIngredient(0, 1);
        List<Ingredient> expectedOrder = List.of(ingredientSecondMock, ingredientFirstMock);
        assertEquals("Неверный порядок ингридиентов", expectedOrder, burger.ingredients);
    }
    @Test
    public void moveIngredientWhenFromAndToAreTheSame_orderDoesNotChange() {
        burger.addIngredient(ingredientFirstMock);
        burger.addIngredient(ingredientSecondMock);
        List<Ingredient> initialOrder = List.copyOf(burger.ingredients);
        burger.moveIngredient(0, 0);
        assertEquals("Порядок ингредиентов не должен измениться при перемещении на ту же позицию", initialOrder, burger.ingredients);
    }
    @Test
    public void getPriceTest(){
    float expectedPrice = 20.0f * 2 + 15.0f + 16.0f;
      burger.setBuns(bunMock);
      burger.addIngredient(ingredientFirstMock);
      burger.addIngredient(ingredientSecondMock);
      assertEquals("Неверная цена бургеров", expectedPrice, burger.getPrice(), 0.01f);
    }
    @Test
    public void getReceiptContainsBunName(){
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientFirstMock);
        burger.addIngredient(ingredientSecondMock);
        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать название булочки сверху", receipt.contains("==== nameBurger ===="));
    }
    @Test
    public void getReceiptFirstIngredient(){
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientFirstMock);
        burger.addIngredient(ingredientSecondMock);
        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать тип и название первого ингридиента", receipt.contains("sauce CheeseSauce"));
    }
    @Test
    public void getReceiptSecondIngredient(){
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientFirstMock);
        burger.addIngredient(ingredientSecondMock);
        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать тип название второго ингридиента", receipt.contains("filling MeatFilling"));
    }
    @Test
    public void getReceiptPriceLabel(){
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientFirstMock);
        burger.addIngredient(ingredientSecondMock);
        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать цену", receipt.contains("Price:"));
    }
}
