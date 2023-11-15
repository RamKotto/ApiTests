package services.user_service;

import static io.restassured.RestAssured.given;
import constants.UrlAndPathConstants;
import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import java.util.List;
import pojo.user.CreateUserResponse;
import pojo.user.UserData;
import pojo.user.UserRequest;
import pojo.user.UserUpdateResponse;
import services.base_service.RestService;

public class UserService extends RestService {

    public UserService(Cookies cookies) {
        super(cookies);
    }

    @Override
    protected String getBasePath() {
        return UrlAndPathConstants.USER_PATH.get();
    }

    @Step("Создание нового пользователя {request}")
    public CreateUserResponse createUser(UserRequest request) {
        return given().spec(REQ_SPEC).body(request)
            .when().post()
            .as(CreateUserResponse.class);
    }

    @Step("Получение списка пользователей")
    public List<UserData> getUsers() {
        return given().spec(REQ_SPEC)
            .when().get()
            .jsonPath().getList(JSON_PATH_DATA, UserData.class);
    }

    @Step("Получение пользователя по id: {id}")
    public UserData getUserById(int id) {
        return given().spec(REQ_SPEC)
            .when().get(ID_PATH_PATTERN.formatted(id))
            .jsonPath().getObject(JSON_PATH_DATA, UserData.class);
    }

    @Step("Обновление данных пользователя: {request} по id: {id}")
    public UserUpdateResponse updateUserById(UserRequest request, int id) {
        return given().spec(REQ_SPEC).body(request)
            .when().patch(ID_PATH_PATTERN.formatted(id))
            .as(UserUpdateResponse.class);
    }
}
