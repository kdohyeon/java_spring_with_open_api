package little.pirate.autotrader.http.upbit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import little.pirate.autotrader.domain.upbit.UpbitRequestQuery;
import little.pirate.autotrader.exception.InvalidUpbitRequestException;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.util.UUID.randomUUID;

@Slf4j
@Component
public class UpbitHttpClient {

    @Value("${http.upbit.open-api.secret-key}")
    private String secretKey;

    @Value("${http.upbit.open-api.access-key}")
    private String accessKey;

    public String request(UpbitRequestQuery query) {
        String authToken = getAuthToken(query.getParam());
        try {
            URL url = new URL(query.getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(query.getMethod().name());
            conn.setRequestProperty("Authorization", authToken);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            if (StringUtils.isNotBlank(query.getBody())) {
                OutputStream os = conn.getOutputStream();
                os.write(query.getBody().getBytes());
                os.flush();
            }

            return getApiResponse(conn);
        } catch (Exception e) {
            log.error("failed to request API with auth: {}", e.getMessage());
            throw new InvalidUpbitRequestException("failed to request API with auth");
        }
    }

    private String getAuthToken(String query) {
        Algorithm algo = Algorithm.HMAC256(secretKey);

        if (StringUtils.isBlank(query)) {
            return "Bearer " + JWT.create()
                    .withClaim("access_key", accessKey)
                    .withClaim("nonce", randomUUID().toString())
                    .sign(algo);
        }

        return "Bearer " + JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", randomUUID().toString())
                .withClaim("query", query)
                .sign(algo);
    }

    private String getApiResponse(HttpURLConnection conn) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            br.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            log.error("failed to get api response: {}", e.getMessage(), e);
            throw new InvalidUpbitRequestException("failed to get api response");
        }
    }
}
