package little.pirate.autotrader.converter.market;

import little.pirate.autotrader.configuration.mapstruct.MapStructConfig;
import little.pirate.autotrader.domain.market.Market;
import little.pirate.autotrader.http.market.UpbitMarket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface MarketConverter {
    Market convert(UpbitMarket source);
    List<Market> convert(List<UpbitMarket> sources);
}
