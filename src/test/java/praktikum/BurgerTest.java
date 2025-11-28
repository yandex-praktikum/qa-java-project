package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient firstIngredient;
    @Mock
    private Ingredient secondIngredient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
    }

    @Test
    public void setBunsShouldOverwriteExistingBuns() {
        Bun newBun = org.mockito.Mockito.mock(Bun.class);
        when(newBun.getPrice()).thenReturn(50f);

        burger.setBuns(newBun);

        assertEquals("Цена должна быть с новыми булочками", 100f, burger.getPrice(), 0.0f);
    }

    @Test
    public void addIngredientShouldIncreasePrice() {
        when(firstIngredient.getPrice()).thenReturn(300f);
        burger.addIngredient(firstIngredient);

        assertEquals("Цена должна включать цену ингредиента", 500f, burger.getPrice(), 0.0f);
    }

    @Test
    public void removeIngredientShouldDecreasePrice() {
        when(firstIngredient.getPrice()).thenReturn(300f);
        burger.addIngredient(firstIngredient);
        burger.removeIngredient(0);

        assertEquals("После удаления ингредиента цена должна быть 200", 200f, burger.getPrice(), 0.0f);
    }

    @Test
    public void moveIngredientShouldNotChangePrice() {
        when(firstIngredient.getPrice()).thenReturn(300f);
        when(secondIngredient.getPrice()).thenReturn(400f);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float priceBeforeMove = burger.getPrice();

        burger.moveIngredient(0, 1);

        assertEquals("При перемещении ингредиентов цена не должна меняться", priceBeforeMove, burger.getPrice(), 0.0f);
    }

    @Test
    public void getPriceShouldReturnCorrectSum() {
        when(firstIngredient.getPrice()).thenReturn(500f);
        when(secondIngredient.getPrice()).thenReturn(600f);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float expectedPrice = 100f * 2 + 500f + 600f;
        assertEquals("Общая цена должна быть посчитана верно", expectedPrice, burger.getPrice(), 0.0f);
    }

    @Test
    public void getReceiptShouldReturnCorrectString() {
        when(bun.getName()).thenReturn("Флюоресцентная булочка");
        when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredient.getName()).thenReturn("Соус космический");
        when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(secondIngredient.getName()).thenReturn("Название мяса придуманное нейронкой");

        when(firstIngredient.getPrice()).thenReturn(50f);
        when(secondIngredient.getPrice()).thenReturn(250f);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                bun.getName(),
                firstIngredient.getType().toString().toLowerCase(), firstIngredient.getName(),
                secondIngredient.getType().toString().toLowerCase(), secondIngredient.getName(),
                bun.getName(),
                burger.getPrice()
        );

        assertEquals("Чек должен формироваться корректно", expectedReceipt, burger.getReceipt());
    }
}