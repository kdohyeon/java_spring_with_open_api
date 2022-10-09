package little.pirate.autotrader.batch.market;

import little.pirate.autotrader.port.in.market.SearchMarketUseCase;
import little.pirate.autotrader.port.in.market.dto.MarketDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class MarketBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final SearchMarketUseCase searchMarketUseCase;

    public static final String JOB_NAME = "marketBatch";

    public MarketBatchConfiguration(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            SearchMarketUseCase searchMarketUseCase
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.searchMarketUseCase = searchMarketUseCase;
    }

    @Bean(value = JOB_NAME)
    public Job job(){
        return jobBuilderFactory.get(JOB_NAME)
                .start(step())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @JobScope
    @Bean(value = JOB_NAME + "_step")
    public Step step() {
        return stepBuilderFactory.get(JOB_NAME + "_step")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Start step for {}", JOB_NAME);

                    List<MarketDto> markets = searchMarketUseCase.getAllMarkets();

                    markets.forEach(market -> log.info("Found market: {}, {}", market.getMarketSymbol(), market.getKoreanName()));
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
