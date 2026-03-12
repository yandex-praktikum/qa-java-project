package praktikum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static praktikum.IngredientType.*;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {
    private Burger burger;
    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockIngredient1;
    @Mock
    private Ingredient mockIngredient2;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @AfterEach
    public void tearDown() {
        burger.bun = null;
        burger.ingredients.clear();
        burger = null;
    }

    @Test
    @DisplayName("Проверка корректного присвоения или изменения значения поля Bun класса Burger")
    public void setBunsTest() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    @DisplayName("Проверка добавления нового ingredient в коллекцию ingredients класса Burger")
    public void addIngredientTest() {
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size(), "В коллекции ingredients у класса Burger должен быть 1 ingredient");
        assertEquals(mockIngredient1, burger.ingredients.get(0), "В коллекции ingredients у класса Burger первым должен быть mockIngredient1");
    }

    @Test
    @DisplayName("Проверка удаления ingredient из коллекции ingredients класса Burger")
    public void removeIngredientTest() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size(), "В коллекции ingredients у класса Burger должен быть 1 ingredient");
        assertEquals(mockIngredient2, burger.ingredients.get(0), "В коллекции ingredients у класса Burger первым должен быть mockIngredient2");
    }

    @Test
    @DisplayName("Проверка перемещения ingredient в коллекции ingredients класса Burger")
    public void moveIngredientTest() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.moveIngredient(0, 1);
        assertEquals(mockIngredient2, burger.ingredients.get(0), "В коллекции ingredients у класса Burger первым должен быть mockIngredient2");
        assertEquals(mockIngredient1, burger.ingredients.get(1), "В коллекции ingredients у класса Burger вторым должен быть mockIngredient1");
    }

    @Test
    @DisplayName("Проверка запроса цены бургера")
    public void getPriceWithMock() {
        when(mockBun.getPrice()).thenReturn(50.0f);
        when(mockIngredient1.getPrice()).thenReturn(15.0f);
        when(mockIngredient2.getPrice()).thenReturn(20.5f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedResult = 50.0f * 2 + 15.0f + 20.5f;
        assertEquals(expectedResult, burger.getPrice(), "Ожидаемая цена за бургер и полученная не совпадают");
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
    }

    @Test
    @DisplayName("Проверка печати чека с информацией о бургере")
    public void getReceiptMock() {
        when(mockBun.getName()).thenReturn("белая булка");
        when(mockBun.getPrice()).thenReturn(50.0f);

        when(mockIngredient1.getType()).thenReturn(SAUCE);
        when(mockIngredient1.getName()).thenReturn("острый соус");
        when(mockIngredient1.getPrice()).thenReturn(20.0f);

        when(mockIngredient2.getType()).thenReturn(FILLING);
        when(mockIngredient2.getName()).thenReturn("куриная котлета");
        when(mockIngredient2.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== белая булка ====)"));
        assertTrue(receipt.contains("= sauce острый соус ="));
        assertTrue(receipt.contains("= filling куриная котлета ="));
        assertTrue(receipt.contains("Price: 170,000000"));

        verify(mockBun, atLeast(2)).getName();
        verify(mockIngredient1, times(1)).getType();
        verify(mockIngredient1, times(1)).getName();
        verify(mockIngredient2, times(1)).getType();
        verify(mockIngredient2, times(1)).getName();
    }
}
