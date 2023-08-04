package ru.intelinvest.tests.api;

import io.qameta.allure.Feature;
import ru.intelinvest.api.portfolio.PortfolioApi;
import ru.intelinvest.config.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static ru.intelinvest.consts.ApiConsts.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;


@DisplayName("API tests for portfolio overview endpoint")
@Feature("Portfolio")

public class PortfolioOverviewTests extends ApiTestBase{
    @Test
    @DisplayName("Get portfolio overview from another user")
    public void getForeignPortfolioOverviewTest(){
        PortfolioApi.getPortfolioOverview(Integer.valueOf(App.config.foreignPortfolio()), FORBIDDEN_CODE);
    }

    @Test
    @DisplayName("Get portfolio that does not exist")
    public void getNonExistingPortfolioTest(){
        Integer nonExistingPortfolio = 999999;
        PortfolioApi.getPortfolioOverview(nonExistingPortfolio, NOT_FOUND_CODE);
    }

    //добавить все виды активов и проверить, что они появляются в портфеле. параметризованный тест


}
