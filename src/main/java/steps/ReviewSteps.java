package steps;

import pages.MoviePage;

public class ReviewSteps {
    private final MoviePage moviePage = new MoviePage();

    public void publishReview(String text, int rating) {
        moviePage.publishReview(text, rating);
    }

    public boolean isSuccessMessageDisplayed() {
        return moviePage.isReviewSuccessMessageDisplayed();
    }

    public String getSuccessMessage() {
        return moviePage.getSuccessMessageText();
    }

    public String getPublishedReviewText() {
        return moviePage.getSuccessMessageText();
    }
}