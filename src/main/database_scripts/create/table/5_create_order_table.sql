CREATE TABLE `order`
(
    ID            int                          NOT NULL AUTO_INCREMENT,
    ReceiptTime   datetime                     NOT NULL,
    PaymentMethod enum ('CASH', 'CREDIT_CARD') NOT NULL,
    ResultCost    decimal                      NOT NULL,
    Score         int,
    Comment       varchar(1000),
    UserID        int                          NOT NULL,

    PRIMARY KEY (ID),
    FOREIGN KEY (UserID) REFERENCES user (ID),
    CHECK ( Score >= 0 && Score <= 5 )
);