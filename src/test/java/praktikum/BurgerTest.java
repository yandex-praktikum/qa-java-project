package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {
    @Mock
    Bun bunMock;//мок нельзя создать как локальную переменную внутри уже тестового метода
    //надо еще продумать дальнейшую логику

    @DisplayName("setBun метод должен сохранять переданный объект bun")
    @Test
    public void setBunsShouldSetBun() {
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.getBun());
    }
}
