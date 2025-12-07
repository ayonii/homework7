package steps;

import pages.LoginPage;

public class AuthSteps {
    private final LoginPage loginPage = new LoginPage();

    public void login(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }
}