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
    private Ingredient mockIngFirst;
    @Mock
    private Ingredient mockIngSecond;
    @Mock
    private Ingredient mockIngThird;

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
        burger.addIngredient(mockIngFirst);
        burger.addIngredient(mockIngSecond);
        assertThat(burger.ingredients)
                .hasSize(2)
                .contains(mockIngFirst, mockIngSecond);
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(mockIngFirst);
        burger.addIngredient(mockIngSecond);
        burger.removeIngredient(0);
        assertThat(burger.ingredients)
                .hasSize(1)
                .containsExactly(mockIngSecond);
    }

    @Test
    public void removeIngredientOutOfBoundsTest() {
        assertThatThrownBy(() -> burger.removeIngredient(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(mockIngFirst); // index 0
        burger.addIngredient(mockIngSecond); // index 1
        burger.addIngredient(mockIngThird); // index 2
        burger.moveIngredient(2, 0);
        assertThat(burger.ingredients)
                .hasSize(3)
                .containsExactly(mockIngThird, mockIngFirst, mockIngSecond);
    }

    @Test
    public void getReceiptTest() {
        Burger burgerSpy = spy(burger);
        doReturn(450.0f).when(burgerSpy).getPrice();

        when(mockBun.getName()).thenReturn("Black Bun");
        when(mockIngFirst.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngFirst.getName()).thenReturn("Hot Sauce");
        when(mockIngSecond.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngSecond.getName()).thenReturn("Cutlet");

        burgerSpy.setBuns(mockBun);
        burgerSpy.addIngredient(mockIngFirst);
        burgerSpy.addIngredient(mockIngSecond);

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




