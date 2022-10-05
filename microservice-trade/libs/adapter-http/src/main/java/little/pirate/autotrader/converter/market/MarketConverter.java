package little.pirate.autotrader.converter.market;

import little.pirate.autotrader.configuration.mapstruct.MapStructConfig;
import little.pirate.autotrader.domain.market.Market;
import little.pirate.autotrader.http.market.UpbitMarket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface MarketConverter {
    @Mapping(source = "market", target = "marketSymbol")
    Market convert(UpbitMarket source);

    @Mapping(source = "market", target = "marketSymbol")
    List<Market> convert(List<UpbitMarket> sources);
}
