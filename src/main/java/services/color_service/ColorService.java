package services.color_service;

import static io.restassured.RestAssured.given;
import constants.UrlAndPathConstants;
import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import java.util.List;
import pojo.color.ColorData;
import services.base_service.RestService;

public class ColorService extends RestService {

    public ColorService(Cookies cookies) {
        super(cookies);
    }

    @Override
    protected String getBasePath() {
        return UrlAndPathConstants.COLOR_PATH.get();
    }

    @Step("Получение списка цветов")
    public List<ColorData> getColors() {
        return given().spec(REQ_SPEC)
            .when().get()
            .jsonPath().getList(JSON_PATH_DATA, ColorData.class);
    }

    @Step("Получение цвета по id: {id}")
    public ColorData getColorById(int id) {
        return given().spec(REQ_SPEC)
            .when().get(ID_PATH_PATTERN.formatted(id))
            .jsonPath().getObject(JSON_PATH_DATA, ColorData.class);
    }
}
