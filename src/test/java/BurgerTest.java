import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockIng1;
    @Mock
    private Ingredient mockIng2;
    @Mock
    private Ingredient mockIng3;

    @Before
    public void setUp() {
        burger = new Burger();
            }

    @Test
    public void setBunTest() {
        burger.setBuns(mockBun);
        assertThat(burger.bun).as("Булка должна быть выбрана").isSameAs(mockBun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(mockIng1);
        burger.addIngredient(mockIng2);
        assertThat(burger.ingredients)
                .hasSize(2)
                .contains(mockIng1, mockIng2);
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(mockIng1);
        burger.addIngredient(mockIng2);
        burger.removeIngredient(0);
        assertThat(burger.ingredients)
                .hasSize(1)
                .containsExactly(mockIng2);
    }

    @Test
    public void removeIngredientOutOfBoundsTest() {
        assertThatThrownBy(() -> burger.removeIngredient(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(mockIng1); // index 0
        burger.addIngredient(mockIng2); // index 1
        burger.addIngredient(mockIng3); // index 2
        burger.moveIngredient(2, 0);
        assertThat(burger.ingredients)
                .hasSize(3)
                .containsExactly(mockIng3, mockIng1, mockIng2);
    }

    @Test
    public void getReceiptTest() {
        Burger burgerSpy = spy(burger);
        doReturn(450.0f).when(burgerSpy).getPrice();

        when(mockBun.getName()).thenReturn("Black Bun");
        when(mockIng1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIng1.getName()).thenReturn("Hot Sauce");
        when(mockIng2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIng2.getName()).thenReturn("Cutlet");

        burgerSpy.setBuns(mockBun);
        burgerSpy.addIngredient(mockIng1);
        burgerSpy.addIngredient(mockIng2);

        String expected = String.format(
                "(==== Black Bun ====)%n" +
                        "= sauce Hot Sauce =%n" +
                        "= filling Cutlet =%n" +
                        "(==== Black Bun ====)%n" +
                        "%nPrice: %f%n",
                450.0f
        );
        assertThat(burgerSpy.getReceipt()).isEqualTo(expected);

    }
    @Test
    public void getPriceThrowsNpeIfBunIsNullTest() {
        assertThatThrownBy(() -> burger.getPrice())
                .isInstanceOf(NullPointerException.class);
    }
}




