package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class ReviewPage {
    private final SelenideElement reviewTextarea = $("#review-text");
    private final SelenideElement publishButton = $("#publish-review");
    private final SelenideElement publishedReview = $(".review-item:first-child .review-content");

    public void enterReview(String text) {
        reviewTextarea.setValue(text);
    }

    public void clickPublish() {
        publishButton.click();
    }

    public String getPublishedReviewText() {
        return publishedReview.getText();
    }
}