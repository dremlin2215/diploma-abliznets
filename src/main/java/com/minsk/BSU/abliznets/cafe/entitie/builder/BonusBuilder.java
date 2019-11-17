package com.minsk.BSU.abliznets.cafe.entitie.builder;

import com.minsk.BSU.abliznets.cafe.api.EntityBuilder;
import com.minsk.BSU.abliznets.cafe.entitie.Bonus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BonusBuilder implements EntityBuilder<Bonus> {

    @Override
    public Bonus build(ResultSet resultSet) throws SQLException {
        int ID = resultSet.getInt(Bonus.ID_COLUMN);
        String name = resultSet.getString(Bonus.NAME_COLUMN);
        String description = resultSet.getString(Bonus.DESCRIPTION_COLUMN);
        int userID = resultSet.getInt(Bonus.USER_ID_COLUMN);

        return new Bonus(ID, name, description, userID);
    }
}
