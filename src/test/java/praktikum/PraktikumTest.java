package praktikum;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class PraktikumTest {

    @Test
    public void mainPrintsBurgerReceiptToConsole() {
        // Перехватываем System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Вызываем main, как будто запустили программу
            Praktikum.main(new String[]{});
        } finally {
            // Возвращаем стандартный вывод обратно
            System.setOut(originalOut);
        }

        String output = outContent.toString();

        // Очень жёстко к формату не привязываемся, главное — что чек напечатан
        assertTrue("Должен быть заголовок чека с булкой", output.contains("(===="));
        assertTrue("Должна быть строка с ценой", output.contains("Price:"));
    }
}
