package api.portfolio;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class PortfolioOverviewDto {
    StockPortfolioDto stockPortfolio;
    BondPortfolioDto bondPortfolio;

}
