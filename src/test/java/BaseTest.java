import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.mockito.Mockito.when;

public class BaseTest {

    //Объект бургера
    protected Burger burger;

    //Моки
    @Mock
    protected Bun bunMock;
    @Mock
    protected Ingredient ingredientMock;
    @Mock
    protected Ingredient sauceMock;
    @Mock
    protected Ingredient fillingMock;

    @Before
    public void setUp() {

        //Закрытие моков после использования
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {

            //Экземпляр класса
            burger = new Burger();

            //Стабы для bunMock
            when(bunMock.getName()).thenReturn("Булочка");
            when(bunMock.getPrice()).thenReturn(100f);

            //Стабы для ingredientMock
            when(ingredientMock.getName()).thenReturn("Ингредиент");
            when(ingredientMock.getPrice()).thenReturn(50f);
            when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);

            //Стабы для sauceMock
            when(sauceMock.getName()).thenReturn("Соус");
            when(sauceMock.getPrice()).thenReturn(50f);
            when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);

            //Стабы для fillingMock
            when(fillingMock.getName()).thenReturn("Начинка");
            when(fillingMock.getPrice()).thenReturn(75f);
            when(fillingMock.getType()).thenReturn(IngredientType.FILLING);

        } catch (Exception e) {

            //Если будет ошибка
            throw new RuntimeException("Не удалось инициализировать моки", e);

        }
    }
}
