CREATE TABLE order_dishes
(
    ID             int NOT NULL AUTO_INCREMENT,
    OrderID        int NOT NULL,
    DishID         int NOT NULL,
    ServingsNumber int NOT NULL,

    PRIMARY KEY (ID),
    FOREIGN KEY (OrderID) REFERENCES `order` (ID),
    FOREIGN KEY (DishID) REFERENCES dish (ID),
    CHECK (ServingsNumber >= 1)
);