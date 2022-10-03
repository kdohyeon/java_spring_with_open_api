package little.pirate.autotrader.port.out.upbit;

import little.pirate.autotrader.domain.upbit.UpbitRequestQuery;

public interface UpbitRepository {
    String request(UpbitRequestQuery query);
}
