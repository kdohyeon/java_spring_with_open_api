package little.pirate.autotrader.port.in.market;

import little.pirate.autotrader.port.in.market.command.MarketSearchCommand;
import little.pirate.autotrader.port.in.market.dto.MarketDto;

import java.util.List;

public interface SearchMarketUseCase {
    List<MarketDto> getAllMarkets();
    List<MarketDto> searchMarketsBy(MarketSearchCommand command);
}
