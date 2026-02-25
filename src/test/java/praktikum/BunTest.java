package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BunTest {

    @Test
    public void testGettersCorrectlyReturnValues() {
        Bun bun = new Bun("Classic Bread", 100.0f);
        assertEquals("Classic Bread", bun.getName());
        assertEquals(100.0f, bun.getPrice(), 0.001);
    }

    @Test
    public void testConstructorSetsValuesCorrectly() {
        Bun bun = new Bun("White Bread", 50.0f);
        assertEquals("White Bread", bun.getName());
        assertEquals(50.0f, bun.getPrice(), 0.001);
    }

    @Test
    public void testEmptyOrNullNameAccepted() {
        Bun emptyNamedBun = new Bun("", 50.0f);
        assertEquals("", emptyNamedBun.getName());

        Bun nullNamedBun = new Bun(null, 50.0f);
        assertNull(nullNamedBun.getName());
    }

    @Test
    public void testNegativePriceAllowed() {
        Bun negativePriceBun = new Bun("Black Bread", -10.0f);
        assertEquals(-10.0f, negativePriceBun.getPrice(), 0.001);
    }

    @Test
    public void testDirectPropertyModification() {
        Bun modifiableBun = new Bun("Whole Wheat", 100.0f);
        modifiableBun.name = "Rye";
        modifiableBun.price = 150.0f;
        assertEquals("Rye", modifiableBun.getName());
        assertEquals(150.0f, modifiableBun.getPrice(), 0.001);
    }

    @Test
    public void testSimpleGetterFunctionality() {
        Bun simpleBun = new Bun("Cheap Bread", 20.0f);
        assertEquals("Cheap Bread", simpleBun.getName());
        assertEquals(20.0f, simpleBun.getPrice(), 0.001);
    }

    @Test
    public void testZeroPriceBunCreation() {
        Bun zeroPriceBun = new Bun("Free Sample", 0.0f);
        assertEquals(0.0f, zeroPriceBun.getPrice(), 0.001);
    }

    @Test
    public void testMaxLengthName() {
        String maxLengthName = "A".repeat(255);
        Bun longNameBun = new Bun(maxLengthName, 100.0f);
        assertEquals(maxLengthName, longNameBun.getName());
    }

    @Test
    public void testMinPositivePrice() {
        Bun minPriceBun = new Bun("Small Bun", Float.MIN_VALUE);
        assertEquals(Float.MIN_VALUE, minPriceBun.getPrice(), 0.001);
    }

    @Test
    public void testNegativePrice() {
        Bun negPriceBun = new Bun("Discount Bun", -100.0f);
        assertEquals(-100.0f, negPriceBun.getPrice(), 0.001);
    }

    @Test
    public void testDirectFieldChange() {
        Bun changeableBun = new Bun("Original Name", 100.0f);
        changeableBun.name = "Changed Name";
        changeableBun.price = 200.0f;
        assertEquals("Changed Name", changeableBun.getName());
        assertEquals(200.0f, changeableBun.getPrice(), 0.001);
    }

    @Test
    public void testEmptyBunName() {
        Bun bun = new Bun("", 100.0f);
        assertEquals("", bun.getName());
    }

    @Test
    public void testMaximumBunNameLength() {
        String veryLongName = "A".repeat(255);
        Bun bun = new Bun(veryLongName, 100.0f);
        assertEquals(veryLongName, bun.getName());
    }

    @Test
    public void testNegativeBunPrice() {
        Bun bun = new Bun("Dark Bun", -100.0f);
        assertEquals(-100.0f, bun.getPrice(), 0.001);
    }

    @Test
    public void testSettingBunWithoutName() {
        Bun bun = new Bun(null, 100.0f);
        assertNull(bun.getName());
    }
}