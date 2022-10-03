package little.pirate.protocol;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResultResponse<T> {
    private final boolean success;
    private final ResponseCode code;
    private final String message;
    private final T data;

    @Builder
    public ResultResponse(boolean success, ResponseCode code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultResponse<T> success() {
        return success(null);
    }

    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse<>(true, ResponseCode.SUCCEED, null, data);
    }

    public static <T> ResultResponse<T> partialSuccess(T data) {
        return new ResultResponse<>(true, ResponseCode.PARTIAL_SUCCEED, null, data);
    }

    public static <T> ResultResponse<T> fail(String message) {
        return new ResultResponse<>(false, ResponseCode.FAILED, message, null);
    }
}
