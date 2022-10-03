package little.pirate.autotrader.controller.market;

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

    public MarketController(SearchMarketUseCase searchMarketUseCase) {
        this.searchMarketUseCase = searchMarketUseCase;
    }

    @GetMapping(value = "/v1/market/all")
    public ResultResponse<List<MarketDto>> getMarkets() {
        var result = searchMarketUseCase.getAllMarkets();
        return ResultResponse.success(result);
    }

    @GetMapping(value = "/v1/market")
    public ResultResponse<List<MarketDto>> getMarkets(
            @RequestParam(defaultValue = "false") boolean isDetails,
            @RequestParam String pair
    ) {
        var command = new MarketSearchCommand(isDetails, pair);
        var result = searchMarketUseCase.searchMarketsBy(command);
        return ResultResponse.success(result);
    }
}
