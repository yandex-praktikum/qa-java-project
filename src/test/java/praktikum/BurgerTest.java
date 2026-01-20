package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ig1;

    @Mock
    private Ingredient ig2;

    @Test
    void setBuns() {
        Burger b = new Burger();
        b.setBuns(bun);
        assertEquals(bun, b.bun);
    }

    @Test
    void addIngredient() {
        Burger b = new Burger();
        assertEquals( 0, b.ingredients.size());
        b.addIngredient(ig1);
        assertAll(
                () -> assertEquals(1, b.ingredients.size()),
                () -> assertEquals(ig1, b.ingredients.get(0))
        );
        b.addIngredient(ig2);
        assertAll(
                () -> assertEquals(2, b.ingredients.size()),
                () -> assertEquals(ig1, b.ingredients.get(0)),
                () -> assertEquals(ig2, b.ingredients.get(1))
        );
    }

    @Test
    void removeIngredient() {
        Burger b = new Burger();
        b.addIngredient(ig1);
        b.addIngredient(ig2);
        b.removeIngredient(0);
        assertAll(
                () -> assertEquals(1, b.ingredients.size()),
                () -> assertEquals(ig2, b.ingredients.get(0))
        );
        b.removeIngredient(0);
        assertEquals(0, b.ingredients.size());
    }

    @Test
    void moveIngredient() {
        Burger b = new Burger();
        b.addIngredient(ig1);
        b.addIngredient(ig2);
        b.moveIngredient(0, 1);
        assertAll(
                () -> assertEquals(2, b.ingredients.size()),
                () -> assertEquals(ig1, b.ingredients.get(1)),
                () -> assertEquals(ig2, b.ingredients.get(0))
        );
    }

    @Test
    void getPrice() {
        Burger b = new Burger();
        assertThrows(NullPointerException.class, () -> {b.getPrice();});
        b.setBuns(bun);
        assertAll(
                () -> Mockito.when(bun.getPrice()).thenReturn(123.4f),
                () -> assertEquals(246.8f, b.getPrice())
        );

        b.addIngredient(ig1);
        assertAll(
                () -> Mockito.when(bun.getPrice()).thenReturn(123.4f),
                () -> Mockito.when(ig1.getPrice()).thenReturn(13.1f),
                () -> assertEquals(259.9f, b.getPrice())
        );

        b.addIngredient(ig2);
        assertAll(
                () -> Mockito.when(bun.getPrice()).thenReturn(123.4f),
                () -> Mockito.when(ig1.getPrice()).thenReturn(13.1f),
                () -> Mockito.when(ig2.getPrice()).thenReturn(1.f),
                () -> assertEquals(260.9f, b.getPrice())
        );

        b.removeIngredient(0);
        assertAll(
                () -> Mockito.when(bun.getPrice()).thenReturn(123.4f),
                () -> Mockito.when(ig2.getPrice()).thenReturn(1.f),
                () -> assertEquals( 247.8f,b.getPrice())
        );
    }

    @Test
    void getReceipt() {
        Burger b = new Burger();
        assertThrows(NullPointerException.class, () -> {b.getReceipt();});
        b.setBuns(bun);
        assertAll(
                () -> Mockito.when(bun.getName()).thenReturn("My bun"),
                () -> Mockito.when(bun.getPrice()).thenReturn(666.f),
                () -> assertEquals( "(==== My bun ====)\n(==== My bun ====)\n\nPrice: 1332,000000\n", b.getReceipt())
        );
        b.addIngredient(ig1);
        assertAll(
                () -> Mockito.when(bun.getName()).thenReturn("My bun"),
                () -> Mockito.when(bun.getPrice()).thenReturn(666.f),
                () -> Mockito.when(ig1.getPrice()).thenReturn(100.f),
                () -> Mockito.when(ig1.getType()).thenReturn(IngredientType.SAUCE),
                () -> Mockito.when(ig1.getName()).thenReturn("Red"),
                () -> assertEquals( "(==== My bun ====)\n= sauce Red =\n(==== My bun ====)\n\nPrice: 1432,000000\n", b.getReceipt())
        );
        b.addIngredient(ig2);
        assertAll(
                () -> Mockito.when(bun.getName()).thenReturn("My bun"),
                () -> Mockito.when(bun.getPrice()).thenReturn(666.f),
                () -> Mockito.when(ig1.getPrice()).thenReturn(100.f),
                () -> Mockito.when(ig1.getType()).thenReturn(IngredientType.SAUCE),
                () -> Mockito.when(ig1.getName()).thenReturn("Red"),
                () -> Mockito.when(ig2.getPrice()).thenReturn(1000.f),
                () -> Mockito.when(ig2.getType()).thenReturn(IngredientType.FILLING),
                () -> Mockito.when(ig2.getName()).thenReturn("Chicken"),
                () -> assertEquals("(==== My bun ====)\n= sauce Red =\n= filling Chicken =\n(==== My bun ====)\n\nPrice: 2432,000000\n", b.getReceipt())
        );
    }
}