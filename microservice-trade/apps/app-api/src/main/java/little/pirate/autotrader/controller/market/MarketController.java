package little.pirate.autotrader.controller.market;

import little.pirate.autotrader.port.in.market.CreateMarketUseCase;
import little.pirate.autotrader.port.in.market.SearchMarketUseCase;
import little.pirate.autotrader.port.in.market.command.MarketSearchCommand;
import little.pirate.autotrader.port.in.market.dto.MarketDto;
import little.pirate.autotrader.protocol.response.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarketController {

    private final SearchMarketUseCase searchMarketUseCase;
    private final CreateMarketUseCase createMarketUseCase;

    public MarketController(SearchMarketUseCase searchMarketUseCase,
                            CreateMarketUseCase createMarketUseCase) {
        this.searchMarketUseCase = searchMarketUseCase;
        this.createMarketUseCase = createMarketUseCase;
    }

    @GetMapping(value = "/v1/market/all")
    public ResultResponse<List<MarketDto>> getMarkets() {
        List<MarketDto> result = searchMarketUseCase.getAllMarkets();
        return ResultResponse.success(result);
    }

    @GetMapping(value = "/v1/market")
    public ResultResponse<List<MarketDto>> getMarkets(
            @RequestParam(defaultValue = "false") boolean isDetails,
            @RequestParam String pair
    ) {
        MarketSearchCommand command = new MarketSearchCommand(isDetails, pair);
        List<MarketDto> result = searchMarketUseCase.searchMarketsBy(command);
        return ResultResponse.success(result);
    }

    @GetMapping(value = "/v1/market/create")
    public ResultResponse<Void> save() {
        createMarketUseCase.create();
        return ResultResponse.success();
    }
}
