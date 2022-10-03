package little.pirate.autotrader.controller;

import little.pirate.autotrader.protocol.response.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class AutoTraderAppApiAdvice {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ResultResponse<Void> handleUnknownException(Exception e) {
        String errorId = UUID.randomUUID().toString();
        return ResultResponse.fail(String.format("알 수 없는 오류가 발생하였습니다. %s", errorId));
    }
}
