package user_test;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.UserGenerator.generateUserRequest;
import base_test.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.user.UserRequest;
import pojo.user.UserUpdateResponse;

@Feature("User Api")
@DisplayName("Обновление данных пользователей")
public class UpdateUserTest extends BaseTest {

    @Test
    @Story("Обновить данные пользователя")
    @DisplayName("Обновление данных пользователя")
    @Description("Отправить запрос на обновление данных пользователя. "
        + "Убедиться, что значение поля job соответствует переданному в запросе.")
    void updateUserPositiveTest() {
        UserRequest userRequest = generateUserRequest.get();

        assertThat(api.userService.updateUserById(userRequest, 2))
            .as("Юзер не был обновлен")
            .extracting(UserUpdateResponse::getJob)
            .isEqualTo(userRequest.getJob());
    }
}
