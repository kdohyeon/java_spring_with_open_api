package little.pirate.autotrader.persistence.market;

import little.pirate.autotrader.domain.market.Market;
import little.pirate.autotrader.port.out.market.MarketPortV2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MarketJpaRepository extends QuerydslRepositorySupport implements MarketPortV2 {

    public MarketJpaRepository() {
        super(Market.class);
    }

    @PersistenceContext
    @Override
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    public void save(Market market) {
        var entityManager = getEntityManager();
        Assert.notNull(entityManager, "Entity manager must not null.");
        entityManager.persist(market);
        entityManager.flush();
    }
}
