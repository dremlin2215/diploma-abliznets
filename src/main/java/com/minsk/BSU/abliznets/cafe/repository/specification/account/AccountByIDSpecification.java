package com.minsk.BSU.abliznets.cafe.repository.specification.account;

import com.minsk.BSU.abliznets.cafe.api.repository.specification.EntitySpecification;
import com.minsk.BSU.abliznets.cafe.api.repository.specification.SqlSpecification;
import com.minsk.BSU.abliznets.cafe.entitie.Account;

import java.util.Collections;
import java.util.List;

public class AccountByIDSpecification implements EntitySpecification<Account>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM account WHERE ID = ?";

    private Integer ID;

    public AccountByIDSpecification(Integer ID) {
        this.ID = ID;
    }

    @Override
    public boolean specified(Account account) {
        return ID.equals(account.getID());
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.singletonList(ID);
    }
}
