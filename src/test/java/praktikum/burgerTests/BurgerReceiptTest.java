package praktikum.burgerTests;

import praktikum.IngredientType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.regex.Pattern;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BurgerReceiptTest extends BurgerBaseTest {

    @Before
    public void stubForReceipt() {
        //Стабим то, что нужно для тестов
        when(bun.getPrice()).thenReturn(100.0f);
        when(bun.getName()).thenReturn("Mock Bun");

        when(filling.getName()).thenReturn("Default Filling");
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getPrice()).thenReturn(30f);
    }

//__Метод getReceipt____________________________________________________________________________________________________

    //Шапка
    @Test
    public void getReceiptShouldHaveCorrectHeaderTest() {

        String receipt = burger.getReceipt();

        //Строим строку в том же формате, что в getReceipt
        String expectedHeader = String.format("(==== %s ====)%n", bun.getName());
        assertTrue("Чек должен строго начинаться с шапки",
                receipt.startsWith(expectedHeader));
    }

    //Строка ингредиента
    @Test
    public void getReceiptShouldHaveCorrectIngredientFormatTest() {

        burger.addIngredient(filling);
        String receipt = burger.getReceipt();

        String expectedLine = String.format("= %s %s =%n",
                filling.getType().toString().toLowerCase(),
                filling.getName());

        assertTrue("Строка ингредиента должна точно соответствовать формату",
                receipt.contains(expectedLine));
    }

    //Футер - наличие
    @Test
    public void getReceiptShouldHaveCorrectFooterTest() {

        String receipt = burger.getReceipt();
        String bunLine = String.format("(==== %s ====)%n", bun.getName());

        //Счетчик, сколько раз появилось bunLine в receipt
        long bunLineCount = Pattern.compile(Pattern.quote(bunLine))
                .matcher(receipt)
                .results()
                .count();

        assertEquals("Строка булки должна встречаться ровно 2 раза (шапка и подвал)",
                2, bunLineCount);
    }

    //Футер - позиция: подвал идёт после ингредиентов и перед ценой
    @Test
    public void getReceiptShouldPlaceFooterBetweenIngredientsAndPriceTest() {

        burger.addIngredient(filling);
        String receipt = burger.getReceipt();

        String bunLine = String.format("(==== %s ====)%n", bun.getName());
        String ingredientLine = String.format("= %s %s =%n",
                filling.getType().toString().toLowerCase(),
                filling.getName());
        String priceLine = String.format("Price: %f", burger.getPrice());

        assertTrue("Подвал (вторая строка булки) должен идти после строки ингредиента",
                receipt.lastIndexOf(bunLine) > receipt.indexOf(ingredientLine));
        assertTrue("Подвал (вторая строка булки) должен идти до строки цены",
                receipt.lastIndexOf(bunLine) < receipt.indexOf(priceLine));
    }

    //Формат цены
    @Test
    public void getReceiptShouldContainPriceLineTest() {

        burger.addIngredient(filling);
        String receipt = burger.getReceipt();

        String expectedPriceLine = String.format("Price: %f", burger.getPrice());

        assertTrue("В чеке должна быть строка цены в ожидаемом формате",
                receipt.contains(expectedPriceLine));
    }

    //Для цены - одна строка
    @Test
    public void getReceiptShouldHaveExactlyOnePriceLineTest() {

        burger.addIngredient(filling);
        String receipt = burger.getReceipt();

        String expectedPriceLine = String.format("Price: %f", burger.getPrice());

        long priceLineCount = Pattern.compile(Pattern.quote(expectedPriceLine))
                .matcher(receipt)
                .results()
                .count();

        assertEquals("Строка цены должна встречаться ровно 1 раз",
                1, priceLineCount);
    }

    @Test
    public void getReceiptShouldEndWithPriceLineTest() {

        burger.addIngredient(filling);
        String receipt = burger.getReceipt();

        String expectedEnding = String.format("%nPrice: %f%n", burger.getPrice());

        assertTrue("Чек должен строго заканчиваться строкой цены с переносом строки",
                receipt.endsWith(expectedEnding));
    }

    @Test
    public void getReceiptWithoutIngredientsShouldNotContainIngredientLineTest() {

        String receipt = burger.getReceipt();

        boolean hasIngredientLine = Pattern.compile("(?m)^= ").matcher(receipt).find();

        assertFalse("В чеке без ингредиентов не должно быть строки ингредиента",
                hasIngredientLine);
    }
}
