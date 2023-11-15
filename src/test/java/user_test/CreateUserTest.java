package user_test;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.UserGenerator.generateUserRequest;
import base_test.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.user.CreateUserResponse;
import pojo.user.UserRequest;

@Feature("User Api")
@DisplayName("Создание новых пользователей")
public class CreateUserTest extends BaseTest {

    @Test
    @Story("Создать пользователя")
    @DisplayName("Создание нового пользователя")
    @Description("Отправить запрос на создание нового пользователя. Убедиться, что пользователь создан. "
        + "Имя в ответе соответствует переданному имени в запросе.")
    void createUserPositiveTest() {
        UserRequest request = generateUserRequest.get();

        assertThat(api.userService.createUser(request))
            .as("Имя созданного пользователя не соответствует тому, что мы передали")
            .isNotNull()
            .extracting(CreateUserResponse::getName)
            .isEqualTo(request.getName());

    }
}
