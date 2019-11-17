CREATE TABLE dish
(
    ID          int            NOT NULL AUTO_INCREMENT,
    Name        nvarchar(90)    NOT NULL,
    Description nvarchar(255),
    Cost        decimal(10, 2) NOT NULL,
    ImageHref   varchar(90),
    IsInMenu    boolean        NOT NULL,

    PRIMARY KEY (ID)
);