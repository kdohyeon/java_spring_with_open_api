package little.pirate.autotrader.persistence.market;

import little.pirate.autotrader.domain.market.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Integer>{
}
