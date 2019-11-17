package com.minsk.BSU.abliznets.cafe.entitie.builder;

import com.minsk.BSU.abliznets.cafe.api.EntityBuilder;
import com.minsk.BSU.abliznets.cafe.entitie.Account;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountBuilder implements EntityBuilder<Account> {

    @Override
    public Account build(ResultSet resultSet) throws SQLException {
        int ID = resultSet.getInt(Account.ID_COLUMN);
        BigDecimal money = resultSet.getBigDecimal(Account.MONEY_COLUMN);

        return new Account(ID, money);
    }
}
