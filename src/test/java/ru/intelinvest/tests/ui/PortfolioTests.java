package ru.intelinvest.tests.ui;

import io.qameta.allure.Feature;
import ru.intelinvest.api.trades.DeleteTradeApi;
import ru.intelinvest.api.trades.TradesApi;
import ru.intelinvest.helpers.WithLogin;
import ru.intelinvest.models.AssetModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.intelinvest.consts.UiConsts;
import ru.intelinvest.models.TradeModel;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Feature("Portfolio")
@DisplayName("Web tests for portfolio")
public class PortfolioTests extends UiTestBase {
    private static Map<AssetModel, Integer> tradesList = Map.of(
            AssetModel.builder().id("1344").ticker("SBER").shortName("Сбербанк").build(), 10,
            AssetModel.builder().id("1075").ticker("GAZP").shortName("ГАЗПРОМ ао").build(), 10,
            AssetModel.builder().id("17803").ticker("SU26230RMFS1").shortName("ОФЗ 26230").build(), 10);

    @BeforeAll
    static void addTradesToPortfolio(){
        TradesApi.addMultipleTrades(trades);
    }

    @AfterAll
    static void deleteAllTradesFromPortfolio(){
        DeleteTradeApi.deleteMultipleTrades(trades);
    }

    @ValueSource(strings = {
            "USD",
            "EUR",
            "RUB"
    })
    @ParameterizedTest(name = "Correct sign is shown for currency {0}")
    @WithLogin
    public void changeCurrencyTest(String currency){
        portfolioPage.openPage();
        topbar.selectCurrency(currency);
        portfolioPage.verifyCurrencySign(currency);
    }

    @Test
    @WithLogin
    @DisplayName("Verify that all tabs are shown in Portfolio screen")
    public void infoTabsTest(){
        portfolioPage.openPage()
                .verifyTabExists(UiConsts.PORTFOLIO_CONTENT_TAB)
                .verifyTabExists(UiConsts.ALL_ASSETS)
                .verifyTabExists(UiConsts.CONSOLIDATED_REPORT)
                .verifyTabExists(UiConsts.STOCKS_TAB)
                .verifyTabExists(UiConsts.BONDS_TAB);
    }

    @Test
    @WithLogin
    @DisplayName("Verify that added stocks and bonds are shown in the table")
    public void portfolioInfoWindowTest(){
        ArrayList<String> shortNamesExpected = new ArrayList(trades.stream().map(TradeModel::getAsset).map(AssetModel::getShortName)
                .collect(Collectors.toList()));;
        ArrayList<String> tickersExpected = new ArrayList(trades.stream().map(TradeModel::getAsset).map(AssetModel::getTicker)
                .collect(Collectors.toList()));

        portfolioPage.openPage()
                    .openTab(UiConsts.ALL_ASSETS)
                    .verifyShortNamesList(shortNamesExpected)
                    .verifyTickers(tickersExpected);
    }
}
