INSERT INTO user (Login, Password, Name, Surname, Role, IsBanned, Score, AccountId)
VALUES ('admin', md5('admin'), 'Andrei', 'Bliznets', 'ADMINISTRATOR', NULL, NULL, NULL);
INSERT INTO user (Login, Password, Name, Surname, Role, IsBanned, Score, AccountId)
VALUES ('abliznets', md5('12345678'), 'Andrei', 'Bliznets', 'CLIENT', false, 5, 2);
INSERT INTO user (Login, Password, Name, Surname, Role, IsBanned, Score, AccountId)
VALUES ('sysop', md5('12345678'), 'Ivan', 'Ivanov', 'CLIENT', false, 5, 3);
INSERT INTO user (Login, Password, Name, Surname, Role, IsBanned, Score, AccountId)
VALUES ('sysop1', md5('12345678'), 'Petr', 'Petrov', 'CLIENT', false, 5, 4);
