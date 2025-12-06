package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement emailInput = $("#email");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("[data-qa-id='login_submit_button']");

    public void enterEmail(String email) {
        emailInput.setValue(email);
    }

    public void enterPassword(String password) {
        passwordInput.setValue(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
}