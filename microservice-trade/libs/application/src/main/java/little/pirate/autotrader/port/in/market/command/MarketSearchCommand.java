package little.pirate.autotrader.port.in.market.command;

import little.pirate.autotrader.contract.market.Pair;
import lombok.Getter;

@Getter
public class MarketSearchCommand {
    private final Pair pair;

    public MarketSearchCommand(String pair) {
        this.pair = Pair.valueOf(pair);
    }
}
