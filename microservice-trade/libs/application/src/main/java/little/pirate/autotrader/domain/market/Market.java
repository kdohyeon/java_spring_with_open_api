package little.pirate.autotrader.domain.market;

import lombok.Getter;

@Getter
public class Market {
    private final String market;
    private final String koreanName;
    private final String englishName;
    private final String marketWarning;

    public Market(String market, String koreanName, String englishName,
                  String marketWarning) {
        this.market = market;
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.marketWarning = marketWarning;
    }
}
