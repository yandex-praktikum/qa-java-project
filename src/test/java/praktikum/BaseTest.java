package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTest {

    protected Burger burger;

    @Mock
    protected Bun bunMock;

    @Mock
    protected Ingredient ingredientMock;

    @Mock
    protected Ingredient ingredientMock1;

    @Mock
    protected Ingredient ingredientMock2;

    protected SoftAssertions softAssertions;

    @Before
    public void setUpBase() {
        burger = new Burger();
        softAssertions = new SoftAssertions();
    }
}