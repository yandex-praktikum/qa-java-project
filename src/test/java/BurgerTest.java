import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.assertj.core.api.SoftAssertions;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.*;
import static org.mockito.Mockito.lenient;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockSauce;
    @Mock
    private Ingredient mockFilling;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();

        lenient().when(mockBun.getName()).thenReturn("white bun");
        lenient().when(mockBun.getPrice()).thenReturn(200F);
        burger.setBuns(mockBun);

        lenient().when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        lenient().when(mockSauce.getName()).thenReturn("chili sauce");
        lenient().when(mockSauce.getPrice()).thenReturn(300F);

        lenient().when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
        lenient().when(mockFilling.getName()).thenReturn("cutlet");
        lenient().when(mockFilling.getPrice()).thenReturn(100F);
    }

    @After
    public void tearDown() {
        burger = null;
        mockBun = null;
        mockSauce = null;
        mockFilling = null;
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(mockBun);
        assertEquals("Булочка должна соответствовать выбранной",
                mockBun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        int sizeBefore = burger.ingredients.size();
        burger.addIngredient(mockSauce);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size())
                .as("Размер списка ингредиентов должен увеличится на 1").isEqualTo(sizeBefore + 1);
        softly.assertThat(burger.ingredients)
                .as("Список должен содержать добавленный ингредиент").contains(mockSauce);
        softly.assertAll();
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        int sizeBefore = burger.ingredients.size();
        burger.removeIngredient(0);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size())
                .as("Размер списка ингредиентов должен уменьшится на 1").isEqualTo(sizeBefore - 1);
        softly.assertThat(burger.ingredients)
                .as("Список не должен содержать удалённый ингредиент").doesNotContain(mockSauce);
        softly.assertAll();
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(mockFilling);
        burger.addIngredient(mockSauce);
        burger.moveIngredient(0, 1);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(1))
                .as("Ингредиент перемещен").isEqualTo(mockFilling);
        softly.assertThat(burger.ingredients.get(0))
                .as("Другой ингредиент тоже перемещен").isEqualTo(mockSauce);
        softly.assertAll();
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        String expectedReceipt = String.format(
                "(==== %s ====)%n= %s %s =%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n",
                "white bun",
                "sauce", "chili sauce",
                "filling", "cutlet",
                "white bun",
                burger.getPrice()
        );

        String actualReceipt = burger.getReceipt();
        assertEquals("Чек сформирован неправильно", expectedReceipt, actualReceipt);
    }
}
