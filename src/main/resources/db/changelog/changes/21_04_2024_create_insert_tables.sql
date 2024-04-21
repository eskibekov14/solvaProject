CREATE TABLE categories (
    id bigserial primary key ,
    name varchar(50) NOT NULL
);
CREATE TABLE currencies (
    id bigserial primary key ,
    name varchar(50) NOT NULL ,
    value double precision NOT NULL
);
CREATE TABLE limits (
    id bigserial primary key ,
    account_number bigint NOT NULL ,
    current_limit double precision NOT NULL ,
    limit_date timestamp,
    sum double precision NOT NULL ,
    category_id bigint,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);
CREATE TABLE transactions (
    id bigserial primary key ,
    account_from bigint NOT NULL ,
    account_to bigint NOT NULL ,
    current_limit double precision NOT NULL ,
    date_time timestamp ,
    limit_exceeded boolean ,
    sum_transaction double precision NOT NULL ,
    category_id bigint NOT NULL ,
    currency_id bigint NOT NULL ,
    FOREIGN KEY (category_id) REFERENCES categories(id) ,
    FOREIGN KEY (currency_id) REFERENCES currencies(id)
);
INSERT INTO categories(name) VALUES ('product'),
                                    ('service');
INSERT INTO currencies(name, value) VALUES ('usdToRub',90) ,
                                           ('usdToKzt',445) ,
                                           ('usdToUsd',1);
INSERT INTO limits(account_number, current_limit, limit_date, sum, category_id) VALUES (123,1000,now(),1000,1) ,
                                                                                       (123,1000,now(),1000,2);