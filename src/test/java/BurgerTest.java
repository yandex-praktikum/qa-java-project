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
import static org.mockito.Mockito.verify;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    @Mock
    private Ingredient newIngredientMock;

    @Before
    public void setUp(){
        burger = new Burger();
    }


    @Test
    public void testSetBuns(){
        burger.setBuns(bunMock);
        assertEquals("Булочка не выбрана", bunMock,burger.bun);
    }

    @Test
    public void testAddIngredient(){
        burger.addIngredient(ingredientMock);
        assertTrue("Ингредиент не добавлен", burger.ingredients.contains(ingredientMock));
    }

    @Test
    public void testRemoveIngredient(){
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertFalse("Ингредиент не получилось удалить",burger.ingredients.contains(ingredientMock));
    }

    @Test
    public void testMoveIngredient(){
        burger.addIngredient(ingredientMock);
        burger.addIngredient(newIngredientMock);
        burger.moveIngredient(0,1);
        assertEquals("Ингредиент не переместился на первое место в списке",newIngredientMock, burger.ingredients.get(0));
        assertEquals("Ингредиент не переместился на второе место в списке", ingredientMock, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice(){
        Mockito.when(bunMock.getPrice()).thenReturn(200.0F);
        Mockito.when(ingredientMock.getPrice()).thenReturn(500.0F);
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        assertEquals("Неверная цена бургера",900.0, burger.getPrice(), 0);

    }

    @Test
    public void testGetReceipt(){
        Mockito.when(bunMock.getName()).thenReturn("Бородинская");
        Mockito.when(ingredientMock.getType()).thenReturn(FILLING);
        Mockito.when(ingredientMock.getName()).thenReturn("Котлета");
        Mockito.when(newIngredientMock.getName()).thenReturn("Кетчуп");
        Mockito.when(newIngredientMock.getType()).thenReturn(SAUCE);
        Mockito.when(bunMock.getPrice()).thenReturn(100.0F);
        Mockito.when(ingredientMock.getPrice()).thenReturn(500.0F);
        Mockito.when(newIngredientMock.getPrice()).thenReturn(50.0F);
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(newIngredientMock);
        String expectedResult = String.format("(==== %s ====)%n= filling %s =%n= sauce %s =%n(==== %s ====)%n%nPrice: %f%n",
                "Бородинская","Котлета", "Кетчуп", "Бородинская", 750.0);
        assertEquals("Формат рецепта неверный, либо несоответствует ожидаемым ингрединтам", expectedResult, burger.getReceipt());

    }
}
