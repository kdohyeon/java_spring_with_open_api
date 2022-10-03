package little.pirate.autotrader.service.market;

import little.pirate.autotrader.contract.market.Pair;
import little.pirate.autotrader.converter.market.MarketDtoConverter;
import little.pirate.autotrader.port.in.market.SearchMarketUseCase;
import little.pirate.autotrader.port.in.market.command.MarketSearchCommand;
import little.pirate.autotrader.port.in.market.dto.MarketDto;
import little.pirate.autotrader.port.out.market.MarketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketSearchService implements SearchMarketUseCase {

    private final MarketRepository marketRepository;
    private final MarketDtoConverter marketDtoConverter;

    public MarketSearchService(MarketRepository marketRepository,
                               MarketDtoConverter marketDtoConverter) {
        this.marketRepository = marketRepository;
        this.marketDtoConverter = marketDtoConverter;
    }

    @Override
    public List<MarketDto> getAllMarkets() {
        var result = marketRepository.getAllMarkets();
        return marketDtoConverter.convert(result);
    }

    @Override
    public List<MarketDto> searchMarketsBy(MarketSearchCommand command) {
        var result = marketRepository.getAllMarkets();
        var filtered = result.stream()
                .filter(each -> command.getPair() == Pair.getPair(each.getMarket()))
                .toList();
        return marketDtoConverter.convert(filtered);
    }
}
