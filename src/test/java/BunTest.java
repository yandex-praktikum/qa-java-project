
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Database;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)

    public class BunTest {

        private static Database database;
        private static List<Bun> availableBuns;

        private String expectedName;
        private double expectedPrice;

        @BeforeClass
        public static void setup() {
            database = new Database();
            availableBuns = database.availableBuns();
        }

        public BunTest(String expectedName, double expectedPrice) {
            this.expectedName = expectedName;
            this.expectedPrice = expectedPrice;
        }

        @Parameterized.Parameters(name = "Проверка булочки с именем {0}, цена {1}")
        public static Collection<Object[]> getData() {
            return Arrays.asList(new Object[][]{
                    {"black bun", 100},
                    {"white bun", 200},
                    {"red bun", 300}
            });
        }

        @Test
        public void bunDetailsTest() {
            boolean foundMatch = false;
            for (Bun bun : availableBuns) {
                if (expectedName.equals(bun.getName())) {
                    assertEquals("Цена булочки '" + expectedName + "' неверна",
                            expectedPrice, bun.getPrice(), 0.01);
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                fail("Булочка с именем '" + expectedName + "' отсутствует");
            }
        }


        @Test
        public void getBunNameTest() {
            Bun bun = new Bun("Black Bun", 100.0f);
            String checkedBunName = bun.getName();
            assertEquals("Black Bun", checkedBunName);
        }

        @Test
        public void getBunPriceTest() {
            Bun bun = new Bun("White Bun", 150.0f);
            float checkedBunPrice = bun.getPrice();
            assertEquals(150.0f, checkedBunPrice, 0.01);
        }
    }



