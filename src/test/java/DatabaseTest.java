import org.junit.Assert;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Database;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {
    @Test
    public void availableBunsTest(){
        Database database = new Database();
        List<Bun> bunsNew = database.availableBuns();

        bunsNew.add(new Bun("black bun", 100));
        bunsNew.add(new Bun("white bun", 200));
        bunsNew.add(new Bun("red bun", 300));

        int expected = bunsNew.size();
        int actual = database.availableBuns().size();
        assertEquals("Упс. Что-то пошло не так.", expected, actual);
    }

    @Test
    public void availableIngredientsTest(){
        Database database = new Database();
        Assert.assertEquals(6, database.availableIngredients().size());
    }
}
