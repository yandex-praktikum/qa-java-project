package praktikum;

import org.junit.Test;

public class PraktikumTest {

    @Test
    public void mainMethodRuns() {
        Praktikum.main(new String[]{});
    }

    @Test
    public void createPraktikumInstance() {
        Praktikum praktikum = new Praktikum();
    }
}
