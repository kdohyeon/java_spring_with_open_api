package little.pirate.autotrader.domain.upbit;

import little.pirate.autotrader.contract.protocol.HttpMethod;
import lombok.Builder;
import lombok.Getter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Getter
public class UpbitRequestQuery {
    private final String url;
    private final HttpMethod method;
    private final String body;
    private final String param;

    @Builder
    public UpbitRequestQuery(String url, HttpMethod method, String body, String param) {
        assertThat(url).isNotBlank();
        assertThat(method).isNotNull();

        this.url = url;
        this.method = method;
        this.body = body;
        this.param = param;
    }
}
