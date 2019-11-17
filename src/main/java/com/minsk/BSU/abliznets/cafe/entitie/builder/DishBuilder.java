package com.minsk.BSU.abliznets.cafe.entitie.builder;

import com.minsk.BSU.abliznets.cafe.api.EntityBuilder;
import com.minsk.BSU.abliznets.cafe.entitie.Dish;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DishBuilder implements EntityBuilder<Dish> {

    @Override
    public Dish build(ResultSet resultSet) throws SQLException {
        int ID = resultSet.getInt(Dish.ID_COLUMN);
        String name = resultSet.getString(Dish.NAME_COLUMN);
        String description = resultSet.getString(Dish.DESCRIPTION_COLUMN);
        BigDecimal cost = resultSet.getBigDecimal(Dish.COST_COLUMN);
        String imageHref = resultSet.getString(Dish.IMAGE_HREF_COLUMN);
        boolean isInMenu = resultSet.getBoolean(Dish.IS_IN_MENU_COLUMN);

        return new Dish(ID, name, description, cost, imageHref, isInMenu);
    }
}
