package burger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Ingredient;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerIngredientsTest extends BurgerBaseTest {

    // Не в базе, нужен только тут для moveIngredient
    @Mock
    protected Ingredient mockOtherSauce;

    /*Хелпер: последовательно добавляет первые count ингредиентов фикстуры
    (mockSauce [0], mockFilling [1], mockOtherSauce [2])
     */
    private void addIngredients(int count) {
        if (count > 0) burger.addIngredient(mockSauce); // индекс [0]
        if (count > 1) burger.addIngredient(mockFilling); // индекс [1]
        if (count > 2) burger.addIngredient(mockOtherSauce); // индекс [2]
    }

    @Test
    public void setBunsStoresThePassedBunReferenceTest() {

        //очищаем поле, чтобы ассерт доказывал работу setBuns
        burger.bun = null;

        burger.setBuns(mockBun);
        assertSame("Поле bun должно хранить именно переданный объект",
                mockBun, burger.bun);
    }

//__Метод addIngredient_________________________________________________________________________________________________

    @Test
    public void addIngredientIncreasesSizeByOneTest() {
        //Запоминаем кол-во ингредиентов до добавления
        int initialSize = burger.ingredients.size();

        burger.addIngredient(mockSauce);

        assertEquals("Размер списка должен увеличиться ровно на 1",
                initialSize + 1, burger.ingredients.size());
    }

    @Test
    public void addIngredientStoresThePassedReferenceTest() {
        int initialSize = burger.ingredients.size();

        burger.addIngredient(mockSauce);

        assertSame("В списке должен храниться именно переданный объект (проверка ссылки)",
                mockSauce, burger.ingredients.get(initialSize));
    }

//__Метод removeIngredient______________________________________________________________________________________________

    @Test
    public void removeIngredientDecreasesSizeTest() {

        addIngredients(2);

        burger.removeIngredient(0);

        assertEquals("После удаления одного ингредиента размер должен стать 1",
                1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShiftsRemainingElementsTest() {

        addIngredients(2);

        //Удаляем соус - [0]
        burger.removeIngredient(0);

        //Проверяем, что начинка становится [0]
        assertSame("На индексе 0 должен остаться именно объект filling (проверка ссылки)",
                mockFilling, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientRemovesExactElementTest() {

        addIngredients(2);

        burger.removeIngredient(0);

        assertFalse("Объект sauce больше не должен присутствовать в списке",
                burger.ingredients.contains(mockSauce));
    }

    //Для removeIngredient с несуществующим индексом:
    @Test
    public void removeIngredientInvalidIndexThrowsExceptionTest() {

        addIngredients(1);
        //Пытаемся удалить по заведомо неверному индексу
        assertThrows(IndexOutOfBoundsException.class,
                () -> burger.removeIngredient(9));
    }

//__Метод moveIngredient________________________________________________________________________________________________

    @Test
    public void moveIngredientPlacesElementAtTargetIndexTest() {

        addIngredients(3);
        // Перемещаем "другой соус" индекс [2] на позицию [0]
        burger.moveIngredient(2, 0);

        assertSame("На индексе 0 должен быть перемещённый элемент (otherSauce)",
                mockOtherSauce, burger.ingredients.get(0));

    }

    @Test
    public void moveIngredientMovesPreviousFirstElementForwardTest() {

        addIngredients(3);

        burger.moveIngredient(2, 0);

        assertSame("На индексе 1 должен быть бывший первый элемент (sauce)",
                mockSauce, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientMovesPreviousSecondElementForwardTest() {

        addIngredients(3);

        burger.moveIngredient(2, 0);

        assertSame("На индексе 2 должен быть бывший второй элемент (filling)",
                mockFilling, burger.ingredients.get(2));
    }

    //На попытку переместить НЕсуществующий индекс ингредиента на существующую позицию
    @Test
    public void moveIngredientInvalidSourceIndexThrowsExceptionTest() {

        addIngredients(1);

        assertThrows(IndexOutOfBoundsException.class,
                () -> burger.moveIngredient(5, 0));
    }

    //На попытку переместить существующий ингредиент на НЕсуществующую позицию
    @Test
    public void moveIngredientInvalidTargetIndexThrowsExceptionTest() {

        addIngredients(2);

        assertThrows(IndexOutOfBoundsException.class,
                () -> burger.moveIngredient(0, 10));
    }
}
