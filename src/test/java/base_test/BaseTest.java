package base_test;

import org.junit.jupiter.api.BeforeAll;
import services.base_service.RestWrapper;

public abstract class BaseTest {

    protected static RestWrapper api;

    @BeforeAll
    public static void init() {
        api = RestWrapper.loginAs("eve.holt@reqres.in", "cityslicka");
    }
}
