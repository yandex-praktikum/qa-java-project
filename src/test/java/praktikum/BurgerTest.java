package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("hot sauce");
        when(ingredient1.getPrice()).thenReturn(50f);

        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getName()).thenReturn("cutlet");
        when(ingredient2.getPrice()).thenReturn(80f);
    }

    @Test
    public void testReceiptStructure() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        String receipt = burger.getReceipt();
        System.out.println("Actual receipt:\n" + receipt); // Для отладки

        // Разбиваем на непустые строки (используем Collectors.toList() для Java 11)
        List<String> nonEmptyLines = Arrays.stream(receipt.split("\\R"))
                .filter(line -> !line.trim().isEmpty())
                .collect(Collectors.toList());

        // Проверяем минимальную структуру
        assertTrue("Receipt should contain at least 3 lines", nonEmptyLines.size() >= 3);
        assertTrue("First line should be bun header",
                nonEmptyLines.get(0).contains("(==== black bun ====)"));
        assertTrue("Second line should be ingredient",
                nonEmptyLines.get(1).contains("= sauce hot sauce ="));
        assertTrue("Last line should be price",
                nonEmptyLines.get(nonEmptyLines.size() - 1).startsWith("Price: "));
    }

    @Test
    public void testReceiptContentWithOneIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        String receipt = burger.getReceipt();

        assertTrue("Receipt should contain bun header",
                receipt.contains("(==== black bun ====)"));
        assertTrue("Receipt should contain ingredient",
                receipt.contains("= sauce hot sauce ="));
        assertTrue("Receipt should contain price",
                receipt.matches("(?s).*Price: ?250[.,]0?.*"));
    }

    @Test
    public void testReceiptContentWithMultipleIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String receipt = burger.getReceipt();

        assertTrue("Receipt should contain bun header",
                receipt.contains("(==== black bun ====)"));
        assertTrue("Receipt should contain sauce",
                receipt.contains("= sauce hot sauce ="));
        assertTrue("Receipt should contain filling",
                receipt.contains("= filling cutlet ="));
        assertTrue("Receipt should contain price",
                receipt.matches("(?s).*Price: ?330[.,]0?.*"));
    }

    // Другие тесты (setBuns, addIngredient и т.д.) остаются без изменений
}