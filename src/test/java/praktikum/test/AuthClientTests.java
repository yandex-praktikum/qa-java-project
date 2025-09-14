package praktikum.test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.model.Client;
import praktikum.steps.ClientSteps;



import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Тесты аутентификации")
public class AuthClientTests extends TestBase {
    private ClientSteps clientSteps;
    private Client validClient;
    private String accessToken;

    @Before
    @Step("Подготовка тестовых данных")
    public void setUp() {
        clientSteps = new ClientSteps();
        Faker faker = new Faker();

        validClient = new Client()
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password())
                .setName(faker.name().firstName());
        clientSteps.createClient(validClient);
        ValidatableResponse loginResp = clientSteps.loginClient(validClient);
        try {
            accessToken = loginResp.extract().path("accessToken");
        } catch (Exception ignore){
            accessToken =null;
        }
    }
    @After
    @Step("Удаление тестового пользователя")
    public void delClient(){
        if (accessToken != null && !accessToken.isEmpty()){
            clientSteps.deleteClient(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная авторизация")
    @Description("Позитивный тест")
    public void shouldLoginSuccessfullyTest() {
        ValidatableResponse response = clientSteps.loginClient(validClient);

        response
                .statusCode(SC_OK)
                .body("success", equalTo(true));

    }

    @Test
    @DisplayName("Авторизация без Email")
    @Description("Негативный тест")
    public void shouldNotLoginWithoutEmailTest() {
        Client clientWithoutEmail = new Client()
                .setPassword(validClient.getPassword());
        clientSteps.loginClient(clientWithoutEmail)
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
    @Test
    @DisplayName("Авторизация без Password")
    @Description("Негативный тест")
    public void shouldNotLoginWithoutPasswordTest() {
        Client clientWithoutPassword = new Client()
                .setPassword(validClient.getEmail());
        clientSteps.loginClient(clientWithoutPassword)
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
    @Test
    @DisplayName("Авторизация с неверным Email")
    @Description("Негативный тест")
    public void shouldNotLoginWithWrongEmailTest(){
        Client clientWithWrongEmail = new Client()
                .setEmail("wrong_" + validClient.getEmail())
                .setPassword(validClient.getPassword());
        clientSteps.loginClient(clientWithWrongEmail)
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
    @Test
    @DisplayName("Авторизация с неверным Password")
    @Description("Негативный тест")
    public void shouldNotLoginWithWrongPasswordTest(){
        Client clientWithWrongPassword = new Client()
                .setPassword("wrong_" + validClient.getPassword())
                .setEmail(validClient.getEmail());
        clientSteps.loginClient(clientWithWrongPassword)
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}