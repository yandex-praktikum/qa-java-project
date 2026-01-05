package praktikum;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class PraktikumTest {

    @Test
    public void mainShouldPrintBurgerReceipt() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            Praktikum.main(new String[]{});

            String output = outContent.toString();

            // Проверяем, что что-то напечаталось
            assertNotNull(output);
            assertFalse(output.trim().isEmpty());

            // Проверяем якорные части чека
            assertTrue(output.contains("(==== black bun ====)"));
            assertTrue(output.contains("Price:"));

            // удалённый ингредиент "sausage" не должен быть в чеке
            assertFalse(output.contains("sausage"));

        } finally {
            System.setOut(originalOut);
        }
    }
}
