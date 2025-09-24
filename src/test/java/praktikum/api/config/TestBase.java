package praktikum.api.config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.BeforeClass;
import praktikum.api.client.ApiClient;
import praktikum.api.model.LoginRequest;
import praktikum.api.model.RegisterRequest;
import praktikum.api.util.TestData;

public abstract class TestBase {

    protected static RequestSpecification spec;
    protected final ApiClient api = new ApiClient();
    protected String token; // токен пользователя для тестов, если нужен
    protected boolean needUser = false; // включить для тестов, где нужен пользователь

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        String DEFAULT_BASE = "https://stellarburgers.nomoreparties.site";
        if (base == null || base.isBlank()) {
            base = DEFAULT_BASE;
            System.out.println("baseUrl not provided, using default: " + DEFAULT_BASE);
        }

        RestAssured.baseURI = base + "/api";

        spec = new RequestSpecBuilder()
                .setContentType("application/json")
                .addFilter(new AllureRestAssured()) // логирование в Allure
                .build();

        RestAssured.requestSpecification = spec;
    }

    /**
     * Создает нового пользователя и логинит его.
     * @return accessToken пользователя
     */
    protected String createAndLoginUser() {
        String email = TestData.uniqueEmail();
        String password = TestData.strongPassword();

        api.registerUser(new RegisterRequest(email, password, TestData.randomName()))
                .then().statusCode(200);

        token = api.loginUser(new LoginRequest(email, password))
                .then().statusCode(200)
                .extract().jsonPath().getString("accessToken");

        if (token == null || token.isBlank()) {
            throw new IllegalStateException("accessToken пустой");
        }

        return token;
    }

    @After
    public void cleanupUser() {
        if (needUser && token != null && !token.isBlank()) {
            api.deleteUser(token).then().statusCode(202);
        }
    }
}
