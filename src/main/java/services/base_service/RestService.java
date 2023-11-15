package services.base_service;

import constants.UrlAndPathConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;

public abstract class RestService {

    private static final String BASE_URL = UrlAndPathConstants.BASE_URL.get();
    protected Cookies cookies;
    protected RequestSpecification REQ_SPEC;
    protected final String JSON_PATH_DATA = "data";
    protected final String ID_PATH_PATTERN = "/%d";

    protected abstract String getBasePath();

    public RestService(Cookies cookies) {
        this.cookies = cookies;

        REQ_SPEC = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .addCookies(cookies)
            .setBaseUri(BASE_URL)
            .setBasePath(getBasePath())
            .setContentType(ContentType.JSON)
            .build();
    }
}
