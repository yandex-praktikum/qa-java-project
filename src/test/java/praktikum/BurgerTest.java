package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Mock
    private Ingredient ingredient3;

    private Burger burger;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }


    @Test
    void setBuns_shouldSetBun() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    void addIngredient_shouldAddToList() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }


    @Test
    void removeIngredient_shouldRemoveByIndex() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }


    @Test
    void moveIngredient_shouldSwapPositions() {
        burger.addIngredient(ingredient1); // индекс 0
        burger.addIngredient(ingredient2); // индекс 1
        burger.moveIngredient(0, 1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @ParameterizedTest(name = "цена с {0} ингредиентами = {1}")
    @MethodSource("priceTestCases")
    void getPrice_shouldCalculateCorrectly(int ingredientCount, float expectedPrice) {
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        List<Ingredient> mocks = List.of(ingredient1, ingredient2, ingredient3);
        for (int i = 0; i < ingredientCount; i++) {
            when(mocks.get(i).getPrice()).thenReturn(50f);
            burger.addIngredient(mocks.get(i));
        }
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.001);
    }

    static Stream<Arguments> priceTestCases() {
        return Stream.of(
                Arguments.of(0, 200f),   // только булка: 100*2 + 0
                Arguments.of(1, 250f),   // 100*2 + 50
                Arguments.of(2, 300f),   // 100*2 + 50*2
                Arguments.of(3, 350f)    // 100*2 + 50*3
        );
    }

    @Test
    void getReceipt_shouldContainBunAndIngredientInfo() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient1.getName()).thenReturn("cutlet");
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient1.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("black bun"));
        assertTrue(receipt.contains("filling cutlet"));
    }
}