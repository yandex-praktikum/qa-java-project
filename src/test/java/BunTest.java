import org.junit.jupiter.api.Test;
import praktikum.Bun;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testng.asserts.SoftAssert;


class BunTest {
    SoftAssert softAssert = new SoftAssert();

    @Test
    void testGettingNameAndPrice() {
        Bun bun = new Bun("Вкусно (нет) и точка!", 100.5F);
        softAssert.assertEquals(bun.getName(), "Вкусно (нет) и точка!");
        softAssert.assertEquals(bun.getPrice(), 100.5F);
        softAssert.assertAll();
    }

    @Test
    void testCheckingEmptyName() {
        Bun bun = new Bun("", 100.5F);
        assertEquals("", bun.getName());
    }

    @Test
    void testZeroPrice() {
        Bun bun = new Bun("Вкусно (нет) и точка!", 0F);
        assertEquals(0F, bun.getPrice());
    }
}