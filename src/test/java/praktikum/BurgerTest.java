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
    private Ingredient mockIngredient1;

    @Mock
    private  Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    private Burger burger;


    @Before
    public void setUp() {
            burger = new Burger();
    }

    // Тесты для метода setBuns()

    @Test
    public void setBunsShouldSetBunCorrectly() {
        when(mockBun.getName()).thenReturn("black bun");
        burger.setBuns(mockBun);
        String actualName = burger.bun.getName();

        assertEquals("Булка должна быть установлена", mockBun, burger.bun);
        assertEquals(String.format("Имя булки должно быть '%s', но получено: '%s'", "black bun", actualName), "black bun", actualName);

        verify(mockBun, times(1)).getName();
    }

    @Test
    public void setBunsShouldOverridePreviousBun() {
        Bun mockBun2 = mock(Bun.class);
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun2.getName()).thenReturn("white bun");

        burger.setBuns(mockBun);
        burger.setBuns(mockBun2);
        String actualName = burger.bun.getName();

        assertEquals("Должна быть установлена вторая булка", mockBun2, burger.bun);
        assertEquals(String.format("Имя должно быть '%s', но получено: '%s'", "white bun", actualName), "white bun", actualName);

        verify(mockBun2, times(1)).getName();
    }



    // Тесты для метода addIngredient()

    @Test
    public void addIngredientShouldAddIngredientToList() {
        when(mockIngredient1.getName()).thenReturn("hot sauce");
        burger.addIngredient(mockIngredient1);
        String actualName = burger.ingredients.get(0).getName();

        assertEquals("Список должен содержать 1 элемент", 1, burger.ingredients.size());
        assertEquals("Добавленный ингредиент должен быть в списке", mockIngredient1, burger.ingredients.get(0));
        assertEquals(String.format("Имя ингредиента должно быть '%s', но получено: '%s'", "hot sauce", actualName), "hot sauce", actualName);

        verify(mockIngredient1, times(1)).getName();
    }

    @Test
    public void addMultipleIngredientsShouldAddAllToList() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        assertEquals("Список должен содержать 3 элемента", 3, burger.ingredients.size());
        assertEquals("Первый ингредиент должен быть на позиции 0", mockIngredient1, burger.ingredients.get(0));
        assertEquals("Второй ингредиент должен быть на позиции 1", mockIngredient2, burger.ingredients.get(1));
        assertEquals("Третий ингредиент должен быть на позиции 2", mockIngredient3, burger.ingredients.get(2));
    }

    @Test
    public void addIngredientShouldMaintainOrder() {
        when(mockIngredient1.getName()).thenReturn("hot sauce");
        when(mockIngredient2.getName()).thenReturn("cutlet");
        when(mockIngredient3.getName()).thenReturn("sour cream");

        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        String name1 = burger.ingredients.get(0).getName();
        String name2 = burger.ingredients.get(1).getName();
        String name3 = burger.ingredients.get(2).getName();

        assertEquals(String.format("Первый ингредиент должен быть 'hot sauce', но получен: '%s", name1), "hot sauce", name1);
        assertEquals(String.format("Второй ингредиент должен быть 'cutlet', но получен: '%s'", name2), "cutlet", name2);
        assertEquals(String.format("Третий ингредиент должен быть 'sour cream', но получен: '%s'", name3), "sour cream", name3);

        verify(mockIngredient1, atLeastOnce()).getName();
        verify(mockIngredient2, atLeastOnce()).getName();
        verify(mockIngredient3, atLeastOnce()).getName();
    }



    // Тесты для метода removeIngredient()

    @Test
    public void removeIngredientShouldRemoveElementAtIndex() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.removeIngredient(1);

        assertEquals("После удаления список должен содержать 2 элемента", 2, burger.ingredients.size());
        assertEquals("Первый элемент должен остаться", mockIngredient1, burger.ingredients.get(0));
        assertEquals("Третий элемент должен сместиться на позицию 1", mockIngredient3, burger.ingredients.get(1));
    }

    @Test
    public void removeIngredientShouldRemoveFirstElement() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);

        assertEquals("После удаления список должен содержать 1 элемент", 1, burger.ingredients.size());
        assertEquals("Второй элемент должен сместиться на позицию 0", mockIngredient2, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveLastElement() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.removeIngredient(2);

        assertEquals("После удаления список должен содержать 2 элемента", 2, burger.ingredients.size());
        assertEquals("Первый элемент должен остаться", mockIngredient1, burger.ingredients.get(0));
        assertEquals("Второй элемент должен остаться", mockIngredient2, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithInvalidIndexShouldThrowException() {
        burger.removeIngredient(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithNegativeIndexShouldThrowException() {
        burger.addIngredient((mockIngredient1));

        burger.removeIngredient(-1);
    }



    // Тесты для метода moveIngredient()

    @Test
    public void moveIngredientShouldMoveElementToNewPosition() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(2, 0);

        assertEquals("Третий элемент должен стать первым", mockIngredient3, burger.ingredients.get(0));
        assertEquals("Первый элемент должен сместиться на позицию 1", mockIngredient1, burger.ingredients.get(1));
        assertEquals("Второй элемент должен сместиться на позицию 2", mockIngredient2, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientShouldMoveElementToEnd() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(0, 2);

        assertEquals("Второй элемент должен стать первым", mockIngredient2, burger.ingredients.get(0));
        assertEquals("Третий элемент должен стать вторым", mockIngredient3, burger.ingredients.get(1));
        assertEquals("Первый элемент должен стать последним", mockIngredient1, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientToSamePositionShouldNotChangeOrder() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(1, 1);

        assertEquals("Первый элемент должен остаться на месте", mockIngredient1, burger.ingredients.get(0));
        assertEquals("Второй элемент должен остаться на месте", mockIngredient2, burger.ingredients.get(1));
        assertEquals("Третий элемент должен остаться на месте", mockIngredient3, burger.ingredients.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithInvalidIndexShouldThrowException() {
        burger.moveIngredient(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithNegativeIndexShouldThrowException() {
        burger.addIngredient(mockIngredient1);

        burger.moveIngredient(-1, 0);
    }



    // Тесты для метода getReceipt()

    @Test
    public void getReceiptShouldReturnCorrectFormat() {
        String expectedBunName = "black bun";
        String expectedIngredient1Name = "hot sauce";
        String expectedIngredient2Name = "cutlet";

        when(mockBun.getName()).thenReturn(expectedBunName);
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getName()).thenReturn(expectedIngredient1Name);
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getPrice()).thenReturn(50f);
        when(mockIngredient2.getName()).thenReturn(expectedIngredient2Name);
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getPrice()).thenReturn(80f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();


        assertTrue("Чек должен содержать верхнюю часть булки 'black bun'", receipt.contains("(==== black bun ====)"));
        assertTrue("Чек должен содержать соус 'hot sauce' в нижнем регистре", receipt.contains("= sauce hot sauce ="));
        assertTrue("Чек должен содержать начинку 'cutlet' в нижнем регистре", receipt.contains("= filling cutlet ="));
        assertTrue("Чек должен содержать нижнюю часть булки 'black bun'", receipt.contains("(==== black bun ====)"));
        assertTrue("Чек должен содержать цену 330 (100*2 + 50 + 80 = 330)", receipt.contains("Price: 330"));

        verify(mockBun, times(2)).getName();
        verify(mockIngredient1, times(1)).getName();
        verify(mockIngredient1, times(1)).getType();
        verify(mockIngredient2, times(1)).getName();
        verify(mockIngredient2, times(1)).getType();
    }

    @Test
    public void getReceiptWithNoIngredientsShouldReturnOnlyBunLines() {
        String expectedBunName = "white bun";
        when(mockBun.getName()).thenReturn(expectedBunName);
        when(mockBun.getPrice()).thenReturn(200f);

        burger.setBuns(mockBun);
        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать верхнюю часть булки", receipt.contains("(==== white bun ====)"));
        assertTrue("Чек должен содержать нижнюю часть булки", receipt.contains("(==== white bun ====)"));
        assertTrue("Чек должен содержать цену 400 (200*2 = 400)", receipt.contains("Price: 400"));

        verify(mockBun, times(2)).getName();
    }

    @Test
    public void getReceiptShouldIncludeAllIngredientsInCorrectOrder() {
        when(mockBun.getName()).thenReturn("red bun");
        when(mockBun.getPrice()).thenReturn(300f);
        when(mockIngredient1.getName()).thenReturn("chili sauce");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getPrice()).thenReturn(150f);
        when(mockIngredient2.getName()).thenReturn("dinosaur");
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getPrice()).thenReturn(250f);
        when(mockIngredient3.getName()).thenReturn("sausage");
        when(mockIngredient3.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient3.getPrice()).thenReturn(180f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать 'chili sauce'", receipt.contains("= sauce chili sauce ="));
        assertTrue("Чек должен содержать 'dinosaur'", receipt.contains("= filling dinosaur ="));
        assertTrue("Чек должен содержать 'sausage'", receipt.contains("= filling sausage ="));

        int saucePos = receipt.indexOf("= sauce chili sauce =");
        int dinosaurPos = receipt.indexOf("= filling dinosaur =");
        int sausagePos = receipt.indexOf("= filling sausage =");

        assertTrue("chili sauce не найден", saucePos != -1);
        assertTrue("dinosaur не найден", dinosaurPos != -1);
        assertTrue("sausage не найден", sausagePos != -1);

        assertTrue("Порядок должен быть: chili sauce → dinosaur → sausage", saucePos < dinosaurPos && dinosaurPos < sausagePos);

        verify(mockIngredient1, times(1)).getName();
        verify(mockIngredient1, times(1)).getType();
        verify(mockIngredient2, times(1)).getName();
        verify(mockIngredient2, times(1)).getType();
        verify(mockIngredient3, times(1)).getName();
        verify(mockIngredient3, times(1)).getType();
    }

    @Test
    public void getReceiptShouldDisplayIngredientTypeInLowerCase() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getName()).thenReturn("sour cream");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getPrice()).thenReturn(50f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        assertTrue("Тип ингредиента должен быть в нижнем регистре: 'sauce'", receipt.contains("= sauce sour cream ="));
        assertFalse("Тип ингредиента не должен быть в верхнем регистре: 'SAUCE'", receipt.contains("= SAUCE sour cream ="));

        verify(mockIngredient1, times(1)).getName();
        verify(mockIngredient1, times(1)).getType();
    }

    @Test
    public void getReceiptShouldIncludeCorrectPrice() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(150f);
        when(mockIngredient1.getName()).thenReturn("cutlet");
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getPrice()).thenReturn(75f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        assertTrue("Цена должна быть 375 (150*2 + 75 = 375)", receipt.contains("Price: 375"));
    }

}
