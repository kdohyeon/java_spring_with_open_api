package little.pirate.autotrader.controller.test;

import little.pirate.autotrader.protocol.deserializer.JsonDeserializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class TradeTickController {
    private final JsonDeserializer jsonDeserializer;

    public TradeTickController(JsonDeserializer jsonDeserializer) {
        this.jsonDeserializer = jsonDeserializer;
    }

    @GetMapping("/v1/trades/ticks")
    public List<UpbitTradeTick> tradeTicks(String market) {
        String url = "https://api.upbit.com/v1/trades/ticks?market=" + market + "&count=10";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return jsonDeserializer.deserializeAsList(result, UpbitTradeTick.class);
    }
}
