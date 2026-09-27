package test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Nested
@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты класса Burger")
class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    private Burger burger;
    private Ingredient sauce;
    private Ingredient filling;

    @BeforeEach
    void setUp() {
        burger = new Burger();
        burger.setBuns(bun);
    }
    @Test
    @DisplayName("setBuns() устанавливает булочку")
    void setBunsShouldAssignBun() {
        assertEquals(bun, burger.bun);
    }
    @Test
    @DisplayName("addIngredient добавляет ингредиент в список")
    void addIngredientShouldAddToList() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(2, burger.ingredients.size());
        assertSame(sauce, burger.ingredients.get(0));
        assertSame(filling, burger.ingredients.get(1));
    }
    @Test
    @DisplayName("Новый бургер содержит пустой список ингредиентов")
    void newBurgerHasEmptyIngredients() {
        assertNotNull(burger.ingredients);
        assertTrue(burger.ingredients.isEmpty());
    }
    @Test
    @DisplayName("moveIngredient перемещает ингредиент на новую позицию")
    void moveIngredientShouldReorder() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        assertEquals(2, burger.ingredients.size());
        assertSame(filling, burger.ingredients.get(0));
        assertSame(sauce, burger.ingredients.get(1));
    } @Test
    @DisplayName("moveIngredient не меняет количество элементов")
    void moveIngredientKeepsSize() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        assertEquals(2, burger.ingredients.size());
    }@Test
    @DisplayName("getPrice считает цену как 2*булочка + сумма ингредиентов")
    void getPriceShouldSumBunTwiceAndIngredients() {
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getPrice()).thenReturn(50f);
        when(filling.getPrice()).thenReturn(80f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(330f, burger.getPrice(), 0.0001f);
    } @Test
    @DisplayName("getPrice для бургера без ингредиентов = 2*булочка")
    void getPriceWithoutIngredients() {
        when(bun.getPrice()).thenReturn(120f);

        burger.setBuns(bun);

        assertEquals(240f, burger.getPrice(), 0.0001f);
    }
    @Test
    @DisplayName("getReceipt формирует чек с булочкой, ингредиентами и ценой")
    void getReceiptShouldContainAllParts() {
        when(bun.getName()).thenReturn("bulka");
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("sauce");
        when(sauce.getPrice()).thenReturn(50f);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("kotleta");
        when(filling.getPrice()).thenReturn(80f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String expectedReceipt = String.format(
                "(==== bulka ====)%n"
                        + "= sauce sauce =%n"
                        + "= filling kotleta =%n"
                        + "(==== bulka ====)%n"
                        + "%n"
                        + "Price: 330.000000%n" );
        assertEquals(expectedReceipt, burger.getReceipt());
    }
    @Test
    @DisplayName("getReceipt содержит тип и имя каждого ингредиента")
    void getReceiptContainsIngredientTypeAndName() {
        when(bun.getName()).thenReturn("bulka");
        when(bun.getPrice()).thenReturn(10f);
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("cheese");
        when(ingredient1.getPrice()).thenReturn(5f);

        burger.addIngredient(ingredient1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= sauce cheese ="));
        assertTrue(receipt.contains("(==== bulka ====)"));
    }
}