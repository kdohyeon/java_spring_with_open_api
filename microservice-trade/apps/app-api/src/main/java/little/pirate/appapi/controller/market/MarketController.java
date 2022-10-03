package little.pirate.appapi.controller.market;

import little.pirate.protocol.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketController {
    @GetMapping(value = "/v1/market/all")
    public ResultResponse<String> getMarket() {
        return ResultResponse.success("test");
    }
}
