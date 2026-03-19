package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient filling;

    @Mock
    private Ingredient sauce;

    private Burger burger;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void getPriceTest() {   // все тесты

        Mockito.when(bun.getName()).thenReturn("Black Bun");
        Mockito.when(bun.getPrice()).thenReturn(100f);

        Mockito.when(filling.getName()).thenReturn("cutlet");
        Mockito.when(filling.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(filling.getPrice()).thenReturn(100f);

        Mockito.when(sauce.getName()).thenReturn("chili sauce");
        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getPrice()).thenReturn(300f);

        burger.setBuns(bun);
        burger.addIngredient(filling);
        burger.addIngredient(sauce);

        String actualReceipt  = burger.getReceipt();

        String expectedReceipt =
                String.format("(==== %s ====)%n" , bun.getName()) +
                        String.format("= %s %s =%n", filling.getType().toString().toLowerCase(),
                                filling.getName()) +
                        String.format("= %s %s =%n", sauce.getType().toString().toLowerCase(),
                                sauce.getName())+
                        String.format("(==== %s ====)%n", bun.getName()) +
                                  String.format("%nPrice: %f%n", burger.getPrice());
        assertEquals(expectedReceipt, actualReceipt);
    }
    @Test
    public void addIngredientTest () {
       burger.addIngredient(filling);
        assertEquals(filling, burger.ingredients.get(0));
    }
    @Test
    public void setBunsTest() {
        Bun bun = new Bun("red bun", 300);
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }
    @Test
    public void  removeIngredientTest () {
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        burger.removeIngredient(0);
        assertEquals("Должен остаться 1 ингредиента", 1, burger.ingredients.size());
    }
    @Test
    public void  moveIngredientTest () {
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        burger.moveIngredient(1,0);
        assertEquals("Первый элемент должен стать sauce",
                sauce, burger.ingredients.get(0));
    }
    }
