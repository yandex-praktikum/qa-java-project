import org.junit.jupiter.api.Test;
import praktikum.Praktikum;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class PraktikumTest {

    @Test
    public void testMainMethod() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            Praktikum.main(new String[]{});

            String output = outContent.toString();

            assertTrue(output.contains("black bun"));

            assertTrue(output.contains("sour cream"));
            assertTrue(output.contains("dinosaur"));
            assertTrue(output.contains("cutlet"));

            assertFalse(output.contains("sausage"));

            assertTrue(output.contains("Price:"));

            assertTrue(output.contains("sauce"));
            assertTrue(output.contains("filling"));

            assertTrue(output.contains("(==== black bun ====)"));
            assertTrue(output.contains("= sauce sour cream ="));
            assertTrue(output.contains("= filling dinosaur ="));
            assertTrue(output.contains("= filling cutlet ="));

        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testMainMethodWithArguments() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            Praktikum.main(new String[]{"arg1", "arg2"});
            String output = outContent.toString();
            assertNotNull(output);
            assertFalse(output.isEmpty());

        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testMainMethodOutputStructure() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            Praktikum.main(new String[]{});
            String output = outContent.toString();
            String[] lines = output.split("\n");
            assertTrue(lines[0].contains("(==== black bun ====)"));
            boolean hasIngredientLine = false;
            for (String line : lines) {
                if (line.contains("= ") && line.contains(" =")) {
                    hasIngredientLine = true;
                    break;
                }
            }
            assertTrue(hasIngredientLine);
            boolean hasPriceLine = false;
            for (String line : lines) {
                if (line.contains("Price:")) {
                    hasPriceLine = true;
                    break;
                }
            }
            assertTrue(hasPriceLine);

        } finally {
            System.setOut(originalOut);
        }
    }

}