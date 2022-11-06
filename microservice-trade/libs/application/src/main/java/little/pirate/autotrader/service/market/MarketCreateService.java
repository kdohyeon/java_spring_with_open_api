package little.pirate.autotrader.service.market;

import little.pirate.autotrader.converter.market.MarketDtoConverter;
import little.pirate.autotrader.domain.market.Market;
import little.pirate.autotrader.port.in.market.CreateMarketUseCase;
import little.pirate.autotrader.port.out.market.MarketPort;
import little.pirate.autotrader.port.out.market.MarketPortV2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionOperations;

@Service
public class MarketCreateService implements CreateMarketUseCase {

    private final MarketPort marketPort;
    private final MarketPortV2 marketPortV2;
    private final MarketDtoConverter marketDtoConverter;
    private final TransactionOperations writeTransactionOperations;

    public MarketCreateService(MarketPort marketPort,
                               MarketPortV2 marketPortV2,
                               MarketDtoConverter marketDtoConverter,
                               TransactionOperations writeTransactionOperations) {
        this.marketPort = marketPort;
        this.marketPortV2 = marketPortV2;
        this.marketDtoConverter = marketDtoConverter;
        this.writeTransactionOperations = writeTransactionOperations;
    }

    @Override
    public void create() {

        writeTransactionOperations.executeWithoutResult(status -> marketPortV2.save(Market.builder()
                        .englishName("Bitcoin")
                        .koreanName("비트코인")
                        .marketSymbol("BTC-KRW")
                        .marketWarning(null)
                .build()));
    }
}
