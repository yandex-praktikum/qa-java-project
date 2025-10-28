package praktikum;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Тест для проверки метода main() в классе Praktikum.
 * Цель — покрыть оставшийся код и довести общее покрытие до 100%.
 * Проверяется, что программа печатает корректный чек.
 */
public class PraktikumTest {

    @Test
    public void mainMethodPrintsReceipt() {
        // Перенаправляем стандартный вывод в поток, чтобы перехватить System.out.println
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Запускаем метод main()
        Praktikum.main(new String[]{});

        // Возвращаем стандартный поток вывода
        System.setOut(originalOut);

        // Получаем содержимое вывода программы
        String printedOutput = outputStream.toString().trim();

        // Проверяем, что чек содержит ключевые строки
        assertTrue("Вывод не содержит строки 'Price:'", printedOutput.contains("Price:"));
        assertTrue("Вывод не содержит строки '(===='", printedOutput.contains("(===="));
        assertTrue("Вывод не содержит слова 'filling'", printedOutput.contains("filling"));
        assertTrue("Вывод не содержит слова 'sauce'", printedOutput.contains("sauce"));
    }
}