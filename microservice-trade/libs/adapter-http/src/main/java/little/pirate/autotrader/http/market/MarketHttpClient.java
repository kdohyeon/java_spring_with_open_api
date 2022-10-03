package little.pirate.autotrader.http.market;

import little.pirate.autotrader.contract.protocol.HttpMethod;
import little.pirate.autotrader.contract.request.UpbitRequestType;
import little.pirate.autotrader.converter.market.MarketConverter;
import little.pirate.autotrader.domain.market.Market;
import little.pirate.autotrader.domain.upbit.UpbitRequestQuery;
import little.pirate.autotrader.port.out.market.MarketRepository;
import little.pirate.autotrader.port.out.upbit.UpbitRepository;
import little.pirate.autotrader.protocol.deserializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarketHttpClient implements MarketRepository {

    private final UpbitRepository upbitRepository;
    private final JsonDeserializer jsonDeserializer;
    private final MarketConverter marketConverter;

    public MarketHttpClient(UpbitRepository upbitRepository,
                            JsonDeserializer jsonDeserializer,
                            MarketConverter marketConverter) {
        this.upbitRepository = upbitRepository;
        this.jsonDeserializer = jsonDeserializer;
        this.marketConverter = marketConverter;
    }

    @Override
    public List<Market> getAllMarkets() {
        var requestType = UpbitRequestType.MARKET_ALL_V1;
        var query = UpbitRequestQuery.builder()
                .url(UpbitRequestType.getFullUrl(requestType))
                .body(null)
                .param(null)
                .method(HttpMethod.GET)
                .authRequired(false)
                .build();
        var data = upbitRepository.request(query);
        var result = jsonDeserializer.deserializeAsList(data, UpbitMarket.class);
        return marketConverter.convert(result);
    }
}
