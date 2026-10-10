package praktikum;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PraktikumTest {

    @Test
    public void testPraktikumConstructor() {
        // Покрываем неявный публичный конструктор класса Praktikum
        Praktikum praktikum = new Praktikum();
        assertNotNull(praktikum);
    }

    @Test
    public void testMainRunsWithoutErrorsAndPrintsReceipt() {
        // Перехватываем System.out, чтобы проверить вывод main
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));

        try {
            Praktikum.main(new String[]{});
        } catch (Exception e) {
            fail("Praktikum.main завершился с ошибкой: " + e.getMessage());
        } finally {
            System.setOut(originalOut);
        }

        String printed = out.toString();

        // Проверяем, что main что-то вывел и это похоже на чек
        assertTrue("main должен вывести чек", printed.contains("Price:"));
        assertTrue("в чеке должна быть булка", printed.contains("black bun"));
    }
}