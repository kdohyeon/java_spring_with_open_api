package little.pirate.autotrader.domain.market;

import lombok.Getter;

@Getter
public class Market {
    private final String market;
    private final String koreanName;
    private final String englishName;

    public Market(String market, String koreanName, String englishName) {
        this.market = market;
        this.koreanName = koreanName;
        this.englishName = englishName;
    }
}
