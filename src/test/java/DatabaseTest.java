import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Database;



@RunWith(MockitoJUnitRunner.class)
public class DatabaseTest {
    //Объявление переменной database типа Database
    Database database;

    @Before
    public void setUp() {
        // Создание экземпляра базы данных перед каждым тестом
        database = new Database();
    }

    @Test
    public void testAvailableBunsSize() {
        // Проверка количества доступных булок
        Assert.assertEquals(3, database.availableBuns().size());
    }

    @Test
    public void testAvailableIngredientsSize() {
        // Проверка количества доступных ингредиентов
        Assert.assertEquals(6, database.availableIngredients().size());
    }
}
