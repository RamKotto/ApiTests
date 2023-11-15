package user_test;

import static org.assertj.core.api.Assertions.assertThat;
import base_test.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.user.UserData;

@Feature("User Api")
@DisplayName("Получение данных о пользователях")
public class GetUserTest extends BaseTest {

    private static final String EMAIL_USER_ONE = "george.bluth@reqres.in";
    private static final String EMAIL_USER_TWO = "janet.weaver@reqres.in";

    @Test
    @Story("Получить список пользователей")
    @DisplayName("Получение всех пользователей с первой страницы")
    @Description("Отправить запрос на получение всех пользователей с первой страницы. "
        + "Убедиться, что среди пользователей присутствует пользователь с e-mail: " + EMAIL_USER_ONE + ".")
    void hasUserWithEmailInListTest() {
        assertThat(api.userService.getUsers())
            .as("Ожидаемый e-mail пользователя отсутствует в ответе")
            .extracting(UserData::getEmail)
            .contains(EMAIL_USER_ONE);
    }

    @Test
    @Story("Получить данные пользователя")
    @DisplayName("Получение пользователя по id")
    @Description("Отправить запрос на получение пользователя по id. "
        + "Убедиться, что e-mail пользователя, вернувшегося в ответе соответствует: " + EMAIL_USER_TWO + ".")
    void getUserByIdTest() {
        assertThat(api.userService.getUserById(2))
            .as("Запрос вернул некорректного пользователя")
            .extracting(UserData::getEmail)
            .isEqualTo(EMAIL_USER_TWO);
    }
}
