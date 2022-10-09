package little.pirate.autotrader.port.out.market;

import little.pirate.autotrader.domain.market.Market;

public interface MarketPortV2 {
    void save(Market market);
    Market findByMarketSymbol(String marketSymbol);
}
