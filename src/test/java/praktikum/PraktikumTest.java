package praktikum;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class PraktikumTest {

    private static String captureMainOutput() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            Praktikum.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }
        return buffer.toString();
    }

    @Test
    public void mainPrintsBurgerTopLine() {
        assertTrue(captureMainOutput().contains("(===="));
    }

    @Test
    public void mainPrintsPriceLine() {
        assertTrue(captureMainOutput().contains("Price:"));
    }
}
