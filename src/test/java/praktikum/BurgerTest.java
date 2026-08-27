package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockHotSauce;

    @Mock
    private  Ingredient mockSourCream;

    @Mock
    private Ingredient mockChiliSauce;

    @Mock
    private Ingredient mockCutlet;

    @Mock
    private Ingredient mockDinosaur;

    @Mock
    private Ingredient mockSausage;


    private Burger burger;


    @Before
    public void setUp() {
            burger = new Burger();
    }

    // Тесты для метода setBuns()

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(mockBun);

        assertEquals("Булка должна быть установлена", mockBun, burger.bun);
    }

    @Test
    public void setBunsShouldSetCorrectBunName() {
        when(mockBun.getName()).thenReturn("black bun");
        burger.setBuns(mockBun);
        assertEquals("Имя булки должно быть 'black bun'", "black bun", burger.bun.getName());
    }

    @Test
    public void setBunsShouldOverridePreviousBun() {
        Bun mockBun2 = mock(Bun.class);
        burger.setBuns(mockBun);
        burger.setBuns(mockBun2);

        assertEquals("Должна быть установлена вторая булка", mockBun2, burger.bun);
    }

    @Test
    public void setBunsShouldSetCorrectNameAfterOverride() {
        Bun mockBun2 = mock(Bun.class);
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun2.getName()).thenReturn("white bun");
        burger.setBuns(mockBun);
        burger.setBuns(mockBun2);
        assertEquals("После перезаписи имя булки должно быть 'white bun'", "white bun", burger.bun.getName());
    }




    // Тесты для метода addIngredient()

    @Test
    public void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(mockHotSauce);

        assertEquals("Список должен содержать 1 элемент", 1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddCorrectIngredient() {
        burger.addIngredient(mockHotSauce);
        assertEquals("В списке должен быть ингредиент 'hot sauce' на позиции 0", mockHotSauce, burger.ingredients.get(0) );
    }



    @Test
    public void addMultipleIngredientsShouldHaveCorrectSize() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSourCream);

        assertEquals("Список должен содержать 3 элемента", 3, burger.ingredients.size());
    }

    @Test
    public void addMultipleIngredientsShouldKeepOrder() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSourCream);
        assertEquals("На позиции 0 должен быть ингредиент 'hot sauce'",
                mockHotSauce, burger.ingredients.get(0));
        assertEquals("На позиции 1 должен быть ингредиент 'cutlet'",
                mockCutlet, burger.ingredients.get(1));
        assertEquals("На позиции 2 должен быть ингредиент 'sour cream'",
                mockSourCream, burger.ingredients.get(2));
    }




    // Тесты для метода removeIngredient()

    @Test
    public void removeIngredientShouldDecreaseSize() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSourCream);
        burger.removeIngredient(1);
        assertEquals("После удаления элемента с индексом 1 список должен содержать 2 элемента",
                2, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldKeepFirstElement() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSourCream);
        burger.removeIngredient(1);
        assertEquals("На позиции 0 должен остаться ингредиент 'hot sauce'",
                mockHotSauce, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldShiftThirdToSecond() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSourCream);
        burger.removeIngredient(1);
        assertEquals("На позицию 1 должен сместиться ингредиент 'sour cream'",
                mockSourCream, burger.ingredients.get(1));
    }

    @Test
    public void removeFirstElementShouldDecreaseSize() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.removeIngredient(0);
        assertEquals("После удаления первого элемента список должен содержать 1 элемент", 1, burger.ingredients.size());
    }

    @Test
    public void removeFirstElementShouldShiftSecondToFirst() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.removeIngredient(0);
        assertEquals("На позицию 0 должен сместиться ингредиент 'cutlet'", mockCutlet, burger.ingredients.get(0));
    }

    @Test
    public void removeLastElementShouldDecreaseSize() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSourCream);
        burger.removeIngredient(2);
        assertEquals("После удаления последнего элемента список должен содержать 2 элемента",
                2, burger.ingredients.size());
    }

    @Test
    public void removeLastElementShouldKeepFirstAndSecond() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSourCream);
        burger.removeIngredient(2);
        assertEquals("На позиции 0 должен остаться ингредиент 'hot sauce'",
                mockHotSauce, burger.ingredients.get(0));
        assertEquals("На позиции 1 должен остаться ингредиент 'cutlet'",
                mockCutlet, burger.ingredients.get(1));
    }

    @Test
    public void removeOnlyIngredientShouldResultInEmptyList() {
        burger.addIngredient(mockHotSauce);
        burger.removeIngredient(0);
        assertTrue("Список должен быть пуст после удаления единственного ингредиента",
                burger.ingredients.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithInvalidIndexShouldThrowException() {
        burger.removeIngredient(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithNegativeIndexShouldThrowException() {
        burger.addIngredient(mockHotSauce);

        burger.removeIngredient(-1);
    }



    // Тесты для метода moveIngredient()

    @Test
    public void moveIngredientShouldMoveThirdToFirst() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSourCream);
        burger.moveIngredient(2, 0);
        assertEquals("На позицию 0 должен переместиться ингредиент 'sour cream'",
                mockSourCream, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldShiftFirstToSecond() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSourCream);
        burger.moveIngredient(2, 0);
        assertEquals("На позицию 1 должен сместиться ингредиент 'hot sauce'",
                mockHotSauce, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientShouldShiftSecondToThird() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSourCream);
        burger.moveIngredient(2, 0);
        assertEquals("На позицию 2 должен сместиться ингредиент 'cutlet'",
                mockCutlet, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientToSamePositionShouldNotChangeOrder() {
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSourCream);
        burger.moveIngredient(1, 1);
        assertEquals("На позиции 0 должен остаться ингредиент 'hot sauce'",
                mockHotSauce, burger.ingredients.get(0));
        assertEquals("На позиции 1 должен остаться ингредиент 'cutlet'",
                mockCutlet, burger.ingredients.get(1));
        assertEquals("На позиции 2 должен остаться ингредиент 'sour cream'",
                mockSourCream, burger.ingredients.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithInvalidIndexShouldThrowException() {
        burger.moveIngredient(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithNegativeIndexShouldThrowException() {
        burger.addIngredient(mockHotSauce);

        burger.moveIngredient(-1, 0);
    }



    // Тесты для метода getReceipt()

    @Test
    public void getReceiptShouldContainBun() {
        when(mockBun.getName()).thenReturn("black bun");
        burger.setBuns(mockBun);
        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать строку '(==== black bun ====)'",
                receipt.contains("(==== black bun ====)"));
    }

    @Test
    public void getReceiptShouldContainBunTwice() {
        when(mockBun.getName()).thenReturn("white bun");
        burger.setBuns(mockBun);
        String receipt = burger.getReceipt();

        int count = receipt.split("\\(==== white bun ====\\)").length - 1;
        assertEquals("Булка должна встречаться дважды в чеке", 2, count);
    }

    @Test
    public void getReceiptShouldContainSauce() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockHotSauce.getName()).thenReturn("hot sauce");
        when(mockHotSauce.getType()).thenReturn(IngredientType.SAUCE);
        burger.setBuns(mockBun);
        burger.addIngredient(mockHotSauce);
        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать строку '= sauce hot sauce ='",
                receipt.contains("= sauce hot sauce ="));
    }

    @Test
    public void getReceiptShouldContainFilling() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockCutlet.getName()).thenReturn("cutlet");
        when(mockCutlet.getType()).thenReturn(IngredientType.FILLING);
        burger.setBuns(mockBun);
        burger.addIngredient(mockCutlet);
        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать строку '= filling cutlet ='",
                receipt.contains("= filling cutlet ="));
    }

    @Test
    public void getReceiptShouldContainCorrectPrice() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockHotSauce.getName()).thenReturn("hot sauce");
        when(mockHotSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockHotSauce.getPrice()).thenReturn(50f);
        when(mockCutlet.getName()).thenReturn("cutlet");
        when(mockCutlet.getType()).thenReturn(IngredientType.FILLING);
        when(mockCutlet.getPrice()).thenReturn(80f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockHotSauce);
        burger.addIngredient(mockCutlet);
        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать цену 330", receipt.contains("Price: 330"));
    }

    @Test
    public void getReceiptWithNoIngredientsShouldContainPrice() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(200f);
        burger.setBuns(mockBun);
        String receipt = burger.getReceipt();
        assertTrue("Чек без ингредиентов должен содержать цену 400", receipt.contains("Price: 400"));
    }

    @Test
    public void getReceiptShouldDisplayTypeInLowerCase() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockSourCream.getName()).thenReturn("sour cream");
        when(mockSourCream.getType()).thenReturn(IngredientType.SAUCE);
        burger.setBuns(mockBun);
        burger.addIngredient(mockSourCream);
        String receipt = burger.getReceipt();
        assertTrue("Тип ингредиента должен быть в нижнем регистре: 'sauce'", receipt.contains("= sauce sour cream ="));
    }

    @Test
    public void getReceiptShouldContainIngredientsInCorrectOrder() {
        when(mockBun.getName()).thenReturn("red bun");
        when(mockChiliSauce.getName()).thenReturn("chili sauce");
        when(mockChiliSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockDinosaur.getName()).thenReturn("dinosaur");
        when(mockDinosaur.getType()).thenReturn(IngredientType.FILLING);
        when(mockSausage.getName()).thenReturn("sausage");
        when(mockSausage.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(mockBun);
        burger.addIngredient(mockChiliSauce);
        burger.addIngredient(mockDinosaur);
        burger.addIngredient(mockSausage);

        String receipt = burger.getReceipt();

        int saucePos = receipt.indexOf("= sauce chili sauce =");
        int dinosaurPos = receipt.indexOf("= filling dinosaur =");
        int sausagePos = receipt.indexOf("= filling sausage =");

        assertTrue("chili sauce не найден", saucePos != -1);
        assertTrue("dinosaur не найден", dinosaurPos != -1);
        assertTrue("sausage не найден", sausagePos != -1);
        assertTrue("Порядок должен быть: chili sauce → dinosaur → sausage", saucePos < dinosaurPos && dinosaurPos < sausagePos);
    }

}
