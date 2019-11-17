CREATE TABLE bonus
(
  ID          int                   NOT NULL AUTO_INCREMENT,
  Name        varchar(90)           NOT NULL,
  Description varchar(1000),
  UserID      int                   NOT NULL,

  PRIMARY KEY (ID),
  FOREIGN KEY (UserID) REFERENCES user (ID)
);