package little.pirate.autotrader.protocol.deserializer;

import little.pirate.autotrader.protocol.gson.GsonUtil;
import little.pirate.autotrader.protocol.parameterized.ListParameterizedType;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Component
public class JsonDeserializer {
    private final GsonUtil gsonUtil;

    public JsonDeserializer(GsonUtil gsonUtil) {
        this.gsonUtil = gsonUtil;
    }

    public <T> List<T> deserializeAsList(String data, Class<T> classOfT) {
        if (StringUtils.isBlank(data)) {
            return Collections.emptyList();
        }

        Type type = new ListParameterizedType(classOfT);
        return gsonUtil.fromJson(data, type);
    }

    public <T> T deserializeAsObject(String data, Class<T> classOfT) {
        if (StringUtils.isBlank(data)) {
            return null;
        }

        return gsonUtil.fromJson(data, classOfT);
    }
 }
