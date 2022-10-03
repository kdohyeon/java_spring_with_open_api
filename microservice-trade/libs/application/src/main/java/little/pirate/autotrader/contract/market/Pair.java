package little.pirate.autotrader.contract.market;

public enum Pair {
    KRW,
    BTC,
    USDT,
    ;

    public static Pair getPair(String market) {
        return Pair.valueOf(market.split("-")[0]);
    }
}
