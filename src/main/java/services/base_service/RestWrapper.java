package services.base_service;

import static io.restassured.RestAssured.given;
import constants.UrlAndPathConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import pojo.user.UserLogin;
import services.color_service.ColorService;
import services.user_service.UserService;

public class RestWrapper {

    private static final String BASE_URL = UrlAndPathConstants.BASE_URL.get();
    private Cookies cookies;

    public UserService userService;
    public ColorService colorService;

    private RestWrapper(Cookies cookies) {
        this.cookies = cookies;

        colorService = new ColorService(cookies);
        userService = new UserService(cookies);
    }

    public static RestWrapper loginAs(String login, String password) {
        Cookies cookies = given()
            .filter(new AllureRestAssured())
            .contentType(ContentType.JSON)
            .baseUri(BASE_URL)
            .basePath(UrlAndPathConstants.LOGIN_PATH.get())
            .body(new UserLogin(login, password))
            .post()
            .getDetailedCookies();

        return new RestWrapper(cookies);
    }
}
