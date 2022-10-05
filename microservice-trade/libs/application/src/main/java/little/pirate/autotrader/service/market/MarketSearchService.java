package little.pirate.autotrader.service.market;

import little.pirate.autotrader.contract.market.Pair;
import little.pirate.autotrader.converter.market.MarketDtoConverter;
import little.pirate.autotrader.port.in.market.SearchMarketUseCase;
import little.pirate.autotrader.port.in.market.command.MarketSearchCommand;
import little.pirate.autotrader.port.in.market.dto.MarketDto;
import little.pirate.autotrader.port.out.market.MarketClause;
import little.pirate.autotrader.port.out.market.MarketPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketSearchService implements SearchMarketUseCase {

    private final MarketPort marketPort;
    private final MarketDtoConverter marketDtoConverter;

    public MarketSearchService(MarketPort marketPort,
                               MarketDtoConverter marketDtoConverter) {
        this.marketPort = marketPort;
        this.marketDtoConverter = marketDtoConverter;
    }

    @Override
    public List<MarketDto> getAllMarkets() {
        var clause = new MarketClause(false);
        var result = marketPort.getAllMarkets(clause);
        return marketDtoConverter.convert(result);
    }

    @Override
    public List<MarketDto> searchMarketsBy(MarketSearchCommand command) {
        var clause = new MarketClause(command.isDetails());
        var result = marketPort.getAllMarkets(clause);
        var filtered = result.stream()
                .filter(each -> command.getPair() == Pair.getPair(each.getMarketSymbol()))
                .toList();
        return marketDtoConverter.convert(filtered);
    }
}
