package little.pirate.autotrader.http.market;

import little.pirate.autotrader.contract.protocol.HttpMethod;
import little.pirate.autotrader.contract.request.UpbitRequestType;
import little.pirate.autotrader.converter.market.MarketConverter;
import little.pirate.autotrader.domain.market.Market;
import little.pirate.autotrader.domain.upbit.UpbitRequestQuery;
import little.pirate.autotrader.http.upbit.UpbitHttpClient;
import little.pirate.autotrader.port.out.market.MarketClause;
import little.pirate.autotrader.port.out.market.MarketPort;
import little.pirate.autotrader.protocol.deserializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarketHttpClient implements MarketPort {

    private final UpbitHttpClient upbitHttpClient;
    private final JsonDeserializer jsonDeserializer;
    private final MarketConverter marketConverter;

    public MarketHttpClient(UpbitHttpClient upbitHttpClient,
                            JsonDeserializer jsonDeserializer,
                            MarketConverter marketConverter) {
        this.upbitHttpClient = upbitHttpClient;
        this.jsonDeserializer = jsonDeserializer;
        this.marketConverter = marketConverter;
    }

    @Override
    public List<Market> getAllMarkets(MarketClause clause) {
        UpbitRequestType requestType = UpbitRequestType.MARKET_ALL_V1;
        UpbitRequestQuery query = UpbitRequestQuery.builder()
                .url(UpbitRequestType.getFullUrl(requestType))
                .body(null)
                .param("isDetails=" + clause.isDetails())
                .method(HttpMethod.GET)
                .build();
        String data = upbitHttpClient.request(query);
        List<UpbitMarket> result = jsonDeserializer.deserializeAsList(data, UpbitMarket.class);
        return marketConverter.convert(result);
    }
}
