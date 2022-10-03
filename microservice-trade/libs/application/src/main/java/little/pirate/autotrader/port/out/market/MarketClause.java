package little.pirate.autotrader.port.out.market;

import lombok.Getter;

@Getter
public class MarketClause {
    private boolean isDetails;

    public MarketClause(boolean isDetails) {
        this.isDetails = isDetails;
    }
}
