package little.pirate.autotrader.port.out.market;

import little.pirate.autotrader.domain.market.Market;

import java.util.List;

public interface MarketRepository {
    List<Market> getAllMarkets();
}
