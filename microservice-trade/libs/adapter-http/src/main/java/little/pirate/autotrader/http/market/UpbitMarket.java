package little.pirate.autotrader.http.market;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class UpbitMarket {
    private String market;

    @SerializedName("korean_name")
    private String koreanName;

    @SerializedName("english_name")
    private String englishName;

    @SerializedName("market_warning")
    private String marketWarning;
}
