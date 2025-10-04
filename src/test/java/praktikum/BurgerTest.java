package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;
    
    @Mock
    private Ingredient mockSauce;
    @Mock
    private Ingredient mockFilling;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetTheBun() {
        burger.setBuns(mockBun);
        
        assertEquals("Булочка не была установлена", mockBun, burger.bun);
    }

    
    @Test
    public void addIngredientShouldIncreaseListSize() {
        burger.addIngredient(mockSauce);
        
        assertEquals("Размер списка должен быть 1", 1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddCorrectIngredient() {
        burger.addIngredient(mockSauce);
        
        assertSame("Добавлен неверный ингредиент", mockSauce, burger.ingredients.get(0));
    }

    
    @Test
    public void removeIngredientShouldDecreaseListSize() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.removeIngredient(0);
        
        assertEquals("Размер списка должен быть 1 после удаления", 1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldRemoveCorrectIngredient() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.removeIngredient(0);
        
        assertSame("Оставшийся ингредиент неверен", mockFilling, burger.ingredients.get(0));
    }

    
    @Test
    public void moveIngredientShouldPlaceIngredientAtNewIndex() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.moveIngredient(0, 1);
        
        assertSame("Соус должен быть на новой позиции", mockSauce, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientShouldShiftOtherIngredient() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.moveIngredient(0, 1);
        
        assertSame("Начинка должна была сдвинуться", mockFilling, burger.ingredients.get(0));
    }

    @Test
    public void getReceiptShouldReturnCorrectString() {
        
        when(mockBun.getName()).thenReturn("Краторная булка");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getName()).thenReturn("Соус галактический");
        when(mockSauce.getPrice()).thenReturn(20.5f);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockFilling.getName()).thenReturn("Котлета");
        when(mockFilling.getPrice()).thenReturn(150.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        float expectedPrice = 100.0f * 2 + 20.5f + 150.0f;
        String expectedReceipt = String.format("(==== Краторная булка ====)%n" +
                "= sauce Соус галактический =%n" +
                "= filling Котлета =%n" +
                "(==== Краторная булка ====)%n" +
                "%nPrice: %f%n", expectedPrice);

        
        String actualReceipt = burger.getReceipt();

        
        assertEquals("Сгенерированный чек не соответствует ожидаемому", expectedReceipt, actualReceipt);
    }
}
