package ru.intelinvest.tests.mobile;

import com.codeborne.selenide.logevents.SelenideLogger;
import ru.intelinvest.activities_mobile.ImportPortfolioActivity;
import ru.intelinvest.activities_mobile.LoginActivity;
import ru.intelinvest.activities_mobile.MainActivity;
import ru.intelinvest.activities_mobile.SettingsActivity;
import ru.intelinvest.helpers.ConfigRunner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import ru.intelinvest.tests.TestBase;

import static com.codeborne.selenide.Selenide.open;

@Tag("ANDROID")
public class MobileTestBase extends TestBase {
    LoginActivity loginActivity = new LoginActivity();
    ImportPortfolioActivity importPortfolioActivity = new ImportPortfolioActivity();
    MainActivity mainActivity = new MainActivity();
    SettingsActivity settingsActicity = new SettingsActivity();
    @BeforeAll
    public static void beforeAll(){
        ConfigRunner.runMobile();

    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        ConfigRunner.finishMobile();

    }

}
