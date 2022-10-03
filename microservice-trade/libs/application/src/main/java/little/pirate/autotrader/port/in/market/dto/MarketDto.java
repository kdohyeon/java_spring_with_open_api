package little.pirate.autotrader.port.in.market.dto;

import lombok.Getter;

@Getter
public class MarketDto {
    private final String market;
    private final String koreanName;
    private final String englishName;

    public MarketDto(String market, String koreanName, String englishName) {
        this.market = market;
        this.koreanName = koreanName;
        this.englishName = englishName;
    }
}
