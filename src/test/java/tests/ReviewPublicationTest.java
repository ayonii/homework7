package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import steps.ReviewSteps;
import junit.UITest;
import com.codeborne.selenide.Selenide;

import static org.assertj.core.api.Assertions.assertThat;

@UITest
public class ReviewPublicationTest {

    private final ReviewSteps reviewSteps = new ReviewSteps();

    @Test
    @Step("Публикация отзыва на фильм")
    public void shouldPublishReviewSuccessfully() {

        // 1. Открываем страницу конкретного фильма
        Selenide.open("https://cinescope.t-qa.ru/movies/689");

        // Подходящий текст для военного фильма в Санкт-Петербурге
        String reviewText = "Мощная военная драма! Особенно актуально смотреть в культурной столице — Санкт-Петербурге. Атмосфера, актёрская игра — всё на высшем уровне.";
        int rating = 5; // Можно выбрать любое значение от 1 до 5

        // Действие
        reviewSteps.publishReview(reviewText, rating);

        // Проверка
        assertThat(reviewSteps.isSuccessMessageDisplayed())
                .as("Сообщение об успешной публикации должно отображаться")
                .isTrue();

        String message = reviewSteps.getSuccessMessage();
        assertThat(message)
                .as("Сообщение должно содержать 'Отзыв успешно создан'")
                .contains("Отзыв успешно создан");
    }
}