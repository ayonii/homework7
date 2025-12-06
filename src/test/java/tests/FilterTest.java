package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import junit.UITest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import steps.FilterSteps;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import static org.assertj.core.api.Assertions.assertThat;

@UITest
public class FilterTest {

    private final FilterSteps filterSteps = new FilterSteps();

    @Test
    @Step("Применение фильтров: SPB и Военный")
    public void shouldDisplayCorrectMoviesAfterFiltering() {
        // Открываем страницу всех фильмов
        Selenide.open("https://cinescope.t-qa.ru/movies");

        // Ждём появления фильтров
        $("[data-qa-id='movies_filter_location_select']").shouldBe(visible);

        // Применяем фильтры
        filterSteps.applyFilters();

        // Проверки
        Assertions.assertThat(filterSteps.isMovie689Visible())
                .as("Фильм с ID 689 должен отображаться")
                .isTrue();

        Assertions.assertThat(filterSteps.getMovie689Title())
                .as("Название фильма должно быть корректным")
                .isEqualTo("Все немного не толерантны к Рафику");
    }
}