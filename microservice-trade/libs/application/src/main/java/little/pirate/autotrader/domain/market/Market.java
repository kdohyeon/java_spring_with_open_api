package little.pirate.autotrader.domain.market;

import little.pirate.autotrader.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "markets")
public class Market extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private int marketId;

    @Column(name = "market_symbol")
    private String marketSymbol;

    @Column(name = "market_korean_name")
    private String koreanName;

    @Column(name = "market_english_name")
    private String englishName;

    @Column(name = "market_warning")
    private String marketWarning;

    @Builder
    public Market(String marketSymbol,
                  String koreanName, String englishName,
                  String marketWarning) {
        this.marketSymbol = marketSymbol;
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.marketWarning = marketWarning;
    }
}
