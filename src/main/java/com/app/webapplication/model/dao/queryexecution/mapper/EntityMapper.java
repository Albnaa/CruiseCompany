package com.app.webapplication.model.dao.queryexecution.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class EntityMapper<T> {
    abstract T collectFromResultSet(ResultSet set) throws SQLException;
}
