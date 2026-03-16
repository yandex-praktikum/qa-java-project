package tests;

import org.junit.Test;
import praktikum.Praktikum;

public class PraktikumTest {

    @Test
    public void mainShouldRunWithoutExceptions() {
        Praktikum.main(new String[]{});
    }

    @Test
    public void constructorShouldCreateObject() {
        new Praktikum();
    }
}