package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientFirst;

    @Mock
    private Ingredient mockIngredientSecond;

    @Mock
    private Ingredient mockIngredientThird;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(200.0f);

        when(mockIngredientFirst.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientFirst.getName()).thenReturn("hot sauce");
        when(mockIngredientFirst.getPrice()).thenReturn(50.0f);

        when(mockIngredientSecond.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientSecond.getName()).thenReturn("cheese");
        when(mockIngredientSecond.getPrice()).thenReturn(30.0f);

        when(mockIngredientThird.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientThird.getName()).thenReturn("mayo");
        when(mockIngredientThird.getPrice()).thenReturn(20.0f);
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        assertThat(burger.ingredients).hasSize(2);

        burger.removeIngredient(0);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients).hasSize(1);
        softly.assertThat(burger.ingredients.get(0).getName()).isEqualTo("cheese");
        softly.assertAll();
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.moveIngredient(0, 2);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(0).getName()).isEqualTo("cheese");
        softly.assertThat(burger.ingredients.get(1).getName()).isEqualTo("mayo");
        softly.assertThat(burger.ingredients.get(2).getName()).isEqualTo("hot sauce");
        softly.assertAll();
    }

    @Test
    public void testGetPriceWithMultipleIngredients() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        float expectedPrice = (200.0f * 2) + 50.0f + 30.0f;
        float actualPrice = burger.getPrice();

        assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void testGetReceiptWithMultipleIngredients() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        String receipt = burger.getReceipt();
        float price = burger.getPrice();

        String expectedReceipt = String.format("(==== %s ====)%n", "white bun") +
                String.format("= %s %s =%n", "sauce", "hot sauce") +
                String.format("= %s %s =%n", "filling", "cheese") +
                String.format("(==== %s ====)%n", "white bun") +
                String.format("%nPrice: %f%n", price);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt).isEqualTo(expectedReceipt);
        softly.assertAll();
    }

    @Test
    public void testGetReceiptStructureWithMultipleIngredients() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        String receipt = burger.getReceipt();
        String[] lines = receipt.split("\n");

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(lines.length).isGreaterThanOrEqualTo(4);
        softly.assertThat(lines[0]).contains("white bun");
        softly.assertThat(lines[lines.length - 1]).contains("Price:");
        softly.assertAll();
    }

    @Test
    public void testBurgerWithNoIngredients() {
        burger.setBuns(mockBun);

        float price = burger.getPrice();

        assertThat(price).isEqualTo(400.0f);
    }

    @Test
    public void testReceiptFormatWithNoIngredients() {
        burger.setBuns(mockBun);

        float price = burger.getPrice();
        String receipt = burger.getReceipt();

        String expectedReceipt = String.format("(==== %s ====)%n", "white bun") +
                String.format("(==== %s ====)%n", "white bun") +
                String.format("%nPrice: %f%n", price);

        assertThat(receipt).isEqualTo(expectedReceipt);
    }

    @Test
    public void testRemoveIngredientWithInvalidIndex() {
        burger.addIngredient(mockIngredientFirst);

        assertThatThrownBy(() -> burger.removeIngredient(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testMoveIngredientWithInvalidIndex() {
        burger.addIngredient(mockIngredientFirst);

        assertThatThrownBy(() -> burger.moveIngredient(0, 5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }
}