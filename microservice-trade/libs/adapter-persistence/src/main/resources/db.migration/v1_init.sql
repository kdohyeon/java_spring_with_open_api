DROP TABLE IF EXISTS markets;
create table markets
(
    market_id BIGINT AUTO_INCREMENT,
    market_symbol VARCHAR(250) NOT NULL,
    market_korean_name VARCHAR(250),
    market_english_name VARCHAR(250),
    market_warning VARCHAR(250),

    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    modified_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),

    PRIMARY KEY(market_id)
)
;
