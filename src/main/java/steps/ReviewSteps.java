// src/main/java/steps/ReviewSteps.java
package steps;

import pages.MoviePage;

public class ReviewSteps {

    private final MoviePage moviePage = new MoviePage();

    public void publishReview(String text, int rating) {
        moviePage.publishReview(text, rating);
    }

    // --- ИСПРАВЛЕНО: Используем правильные имена методов из MoviePage ---
    public boolean isReviewPublishedSuccessfully() {
        return moviePage.isReviewPublishedSuccessfully(); // <- Совпадает с именем в MoviePage
    }

    public String getReviewConfirmationMessage() {
        return moviePage.getReviewConfirmationMessage(); // <- Совпадает с именем в MoviePage
    }
}