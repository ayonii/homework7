// src/test/java/tests/ReviewPublicationTest.java
package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import junit.UITest;
import org.junit.jupiter.api.Test;
import steps.AuthSteps;
import steps.FilterSteps;
import steps.ReviewSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

@UITest
public class ReviewPublicationTest {

    private final AuthSteps authSteps = new AuthSteps();
    private final FilterSteps filterSteps = new FilterSteps();
    private final ReviewSteps reviewSteps = new ReviewSteps();

    @Test
    @Step("Публикация отзыва на фильм")
    public void shouldPublishReviewSuccessfully() {
        // --- ШАГ 1: Открытие главной страницы ---
        System.out.println("🔍 Открываем главную страницу...");
        Selenide.open("https://cinescope.t-qa.ru/");

        // --- ШАГ 2: Проверка/восстановление сессии ---
        System.out.println("✅ Проверяем сессию...");
        authSteps.ensureLoggedIn("waitan123@tavorot.ru", "qwerty123_OOO");

        // --- ШАГ 3: Применение фильтров (SPB, Все) ---
        System.out.println("⚙️ Применяем фильтры: SPB, Все...");
        filterSteps.applyFilters();

        // --- ШАГ 4: Найти и кликнуть по фильму ID 689 ---
        System.out.println("🔍 Ищем и кликаем по фильму с ID 689...");
        $("[data-qa-id='movie_more_689']").shouldBe(visible).click();

        // --- ШАГ 5: Проверка сессии на странице фильма ---
        System.out.println("✅ Проверяем сессию на странице фильма...");
        authSteps.ensureLoggedIn("waitan123@tavorot.ru", "qwerty123_OOO");

        // --- ШАГ 6: Публикация отзыва ---
        System.out.println("📝 Начинаем публикацию отзыва...");
        String reviewText = "Это отличный военный фильм!";
        int rating = 5; // Оценка от 1 до 5

        reviewSteps.publishReview(reviewText, rating);

        // --- ШАГ 7: Проверка успешной публикации ---
        System.out.println("✅ Проверяем результат публикации...");
        assertThat(reviewSteps.isReviewPublishedSuccessfully())
                .as("Отзыв должен быть опубликован успешно")
                .isTrue();

        String confirmation = reviewSteps.getReviewConfirmationMessage();
        assertThat(confirmation)
                .as("Сообщение должно содержать 'успешно'")
                .containsIgnoringCase("успешно");
    }
}