package junit;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SelenideExtension implements BeforeAllCallback, AfterEachCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        // Настройка Selenide
        Configuration.browser = "chrome";
        Configuration.browserSize = "maximize";
        Configuration.timeout = 10000; // Увеличенный таймаут
        Configuration.pageLoadTimeout = 30000;
        Configuration.baseUrl = "https://cinescope.t-qa.ru";
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Selenide.closeWebDriver();
    }
}