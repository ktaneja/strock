Create Table IF NOT EXISTS STOCK
(
Symbol varchar(20) NOT NULL,
name varchar(200) NOT NULL,
marketCap varchar(20),
IPOyear int,
sector varchar(100),
industry varchar(200),
summaryURL varchar(200),
PRIMARY KEY (Symbol)
);


Create Table IF NOT EXISTS HISTORICAL_DATA
(
Symbol varchar(20) NOT NULL,
date date NOT NULL,
openPrice double,
closePrice double,
dayHigh double,
dayLow double,
volume int,
adjClose double,
PRIMARY KEY (Symbol, date),
FOREIGN KEY (Symbol) REFERENCES STOCK(Symbol) on delete cascade
); 