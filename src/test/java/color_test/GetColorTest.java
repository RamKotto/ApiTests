package color_test;

import static org.assertj.core.api.Assertions.assertThat;
import base_test.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.color.ColorData;

@Feature("Color Api")
@DisplayName("Получение данных о цветах")
public class GetColorTest extends BaseTest {

    private static final String CERULEAN = "cerulean";
    private static final String FUCHSIA_ROSE = "fuchsia rose";
    private static final String TRUE_RED = "true red";

    @Test
    @Story("Получить список цветов")
    @DisplayName("Получение всех цветов с первой страницы")
    @Description("Отправить запрос на получение списка цветов с первой страницы."
        + "Убедиться, что на первой странице присутствуют: " + CERULEAN + ", " + FUCHSIA_ROSE + " и " + TRUE_RED + ".")
    void getListOfColorsTest() {
        assertThat(api.colorService.getColors())
            .as("Необходимые цвета не отображаются в ответе")
            .extracting(ColorData::getName)
            .contains(CERULEAN, FUCHSIA_ROSE, TRUE_RED);
    }

    @Test
    @Story("Получить данные о цвете")
    @DisplayName("Получение цвета по id")
    @Description("Отправить запрос на получение цвета по id: 2"
        + "Убедиться, что в вернувшемся ответе, поле name содержит: " + FUCHSIA_ROSE + ".")
    void getColorByIdTest() {
        assertThat(api.colorService.getColorById(2))
            .as("Запрашиваемый цвет отличается от полученного")
            .extracting(ColorData::getName)
            .isEqualTo(FUCHSIA_ROSE);
    }
}
