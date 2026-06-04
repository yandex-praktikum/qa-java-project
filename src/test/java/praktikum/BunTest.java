package praktikum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class BunTest {
    Bun bun = new Bun("Сдоба", 10);
   @Test
    public void getBunNameReturn(){
        assertEquals("Вернул Сдоба - ОР", "Сдоба", bun.getName());
    }
    @Test
    public void getBunPriceReturn(){
        assertEquals("Вернул 10 - ОР", 10.0, bun.getPrice(), 0.01);
    }
}
