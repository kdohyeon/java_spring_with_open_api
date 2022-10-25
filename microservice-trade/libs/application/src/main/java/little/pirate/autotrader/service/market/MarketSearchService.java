package little.pirate.autotrader.service.market;

import little.pirate.autotrader.contract.market.Pair;
import little.pirate.autotrader.converter.market.MarketDtoConverter;
import little.pirate.autotrader.domain.market.Market;
import little.pirate.autotrader.port.in.market.SearchMarketUseCase;
import little.pirate.autotrader.port.in.market.command.MarketSearchCommand;
import little.pirate.autotrader.port.in.market.dto.MarketDto;
import little.pirate.autotrader.port.out.market.MarketClause;
import little.pirate.autotrader.port.out.market.MarketPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        MarketClause clause = new MarketClause(false);
        List<Market> result = marketPort.getAllMarkets(clause);
        return marketDtoConverter.convert(result);
    }

    @Override
    public List<MarketDto> searchMarketsBy(MarketSearchCommand command) {
        MarketClause clause = new MarketClause(command.isDetails());
        List<Market> result = marketPort.getAllMarkets(clause);
        List<Market> filtered = result.stream()
                .filter(each -> command.getPair() == Pair.getPair(each.getMarketSymbol()))
                .collect(Collectors.toList());
        return marketDtoConverter.convert(filtered);
    }
}
