package com.minsk.BSU.abliznets.cafe.repository.impl;

import com.minsk.BSU.abliznets.cafe.api.repository.Repository;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.builder.BonusBuilder;
import com.minsk.BSU.abliznets.cafe.entitie.Bonus;
import com.minsk.BSU.abliznets.cafe.repository.RepositoryException;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BonusRepository extends AbstractRepository<Bonus> implements Repository<Bonus> {
    private static final String TABLE_NAME = "bonus";

    public BonusRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParams(Bonus bonus) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(Bonus.ID_COLUMN, bonus.getID());
        params.put(Bonus.NAME_COLUMN, bonus.getName());
        params.put(Bonus.DESCRIPTION_COLUMN, bonus.getDescription());
        params.put(Bonus.USER_ID_COLUMN, bonus.getUserID());

        return params;
    }

    @Override
    public List<Bonus> query(SqlSpecification specification) throws RepositoryException {
        return executeQuery(specification, new BonusBuilder());
    }
}
