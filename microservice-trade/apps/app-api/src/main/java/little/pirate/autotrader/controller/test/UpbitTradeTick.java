package little.pirate.autotrader.controller.test;

import lombok.Getter;

@Getter
public class UpbitTradeTick {
    private String market;
    private String trade_date_utc;
    private String trade_time_utc;
    private String timestamp;
    private String trade_price;
    private String trade_volume;
    private String prev_closing_price;
    private String ask_bid;
    private String sequential_id;

    public UpbitTradeTick(String userId, String userName, String password, String market, String trade_date_utc, String trade_time_utc, String timestamp, String trade_price, String trade_volume, String prev_closing_price, String ask_bid, String sequential_id) {
        this.market = market;
        this.trade_date_utc = trade_date_utc;
        this.trade_time_utc = trade_time_utc;
        this.timestamp = timestamp;
        this.trade_price = trade_price;
        this.trade_volume = trade_volume;
        this.prev_closing_price = prev_closing_price;
        this.ask_bid = ask_bid;
        this.sequential_id = sequential_id;
    }
}
