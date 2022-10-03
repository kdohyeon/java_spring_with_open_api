package little.pirate.autotrader.port.in.market.command;

import little.pirate.autotrader.contract.market.Pair;
import lombok.Getter;

@Getter
public class MarketSearchCommand {
    private final boolean isDetails;
    private final Pair pair;

    public MarketSearchCommand(boolean isDetails, String pair) {
        this.isDetails = isDetails;
        this.pair = Pair.valueOf(pair);
    }
}
