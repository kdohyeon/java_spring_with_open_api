package little.pirate.autotrader.converter.market;

import little.pirate.autotrader.configuration.mapstruct.MapStructConfig;
import little.pirate.autotrader.domain.market.Market;
import little.pirate.autotrader.port.in.market.dto.MarketDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface MarketDtoConverter {
    MarketDto convert(Market source);
    List<MarketDto> convert(List<Market> sources);
}
