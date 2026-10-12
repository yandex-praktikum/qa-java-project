package praktikum.burgerTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Ingredient;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerIngredientsTest extends BurgerBaseTest {

    @Test
    public void setBunsStoresThePassedBunReferenceTest() {

        //очищаем поле, чтобы ассерт доказывал работу setBuns, а не фикстуру
        burger.bun = null;

        burger.setBuns(bun);
        assertSame("Поле bun должно хранить именно переданный объект",
                bun, burger.bun);
    }

    @Test
    public void addIngredientIncreasesSizeAndStoresReferenceTest() {
        //Запоминаем кол-во ингредиентов до добавления
        int initialSize = burger.ingredients.size();

        burger.addIngredient(sauce);
        assertEquals("Размер списка должен увеличиться ровно на 1",
                initialSize + 1, burger.ingredients.size());
        assertSame("В списке должен храниться именно переданный объект (проверка ссылки)",
                sauce, burger.ingredients.get(initialSize));
    }

//__Метод removeIngredient______________________________________________________________________________________________

    @Test
    public void removeIngredientShiftsListAndDecreasesSizeTest() {

        // Добавляем в лист соус и начинку из Before
        burger.addIngredient(sauce); //Индекс [0]
        burger.addIngredient(filling); //Индекс [1]

        //Удаляем соус - [0]
        burger.removeIngredient(0);

        assertEquals("После удаления одного ингредиента размер должен стать 1",
                1, burger.ingredients.size());

        //Проверяем, что начинка становится [0]
        assertSame("На индексе 0 должен остаться именно объект filling (проверка ссылки)",
                filling, burger.ingredients.get(0));
        assertFalse("Объект sauce больше не должен присутствовать в списке",
                burger.ingredients.contains(sauce));
    }

    //Для removeIngredient с несуществующим индексом:
    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientInvalidIndexThrowsExceptionTest() {
        burger.addIngredient(sauce);
        //Пытаемся удалить по заведомо неверному индексу
        burger.removeIngredient(9);
    }

//__Метод moveIngredient________________________________________________________________________________________________

    @Test
    public void moveIngredientShouldShiftCorrectlyTest() {

        /* Второй соус, нужен только тут,
        потому не выношу в Before для каждого теста */
        Ingredient otherSauce = Mockito.mock(Ingredient.class);

        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.addIngredient(otherSauce);

        // Перемещаем "другой соус" индекс [2] на позицию [0]
        burger.moveIngredient(2, 0);

        assertSame("На индексе 0 должен быть перемещённый элемент (otherSauce)",
                otherSauce, burger.ingredients.get(0));
        assertSame("На индексе 1 должен быть бывший первый элемент (sauce)",
                sauce, burger.ingredients.get(1));
        assertSame("На индексе 2 должен быть бывший второй элемент (filling)",
                filling, burger.ingredients.get(2));
    }

    //На попытку вставить НЕсуществующий индекс ингредиента на существующую позицию
    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientInvalidSourceIndexThrowsExceptionTest() {

        burger.addIngredient(sauce);
        burger.moveIngredient(5, 0);
    }

    //На попытку вставить существующий ингредиент на НЕсуществующую позицию
    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientInvalidTargetIndexThrowsExceptionTest() {

        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        //пытаемся переместить элемент с валидного индекса 0 на недопустимый индекс 10
        burger.moveIngredient(0, 10);
    }
}
