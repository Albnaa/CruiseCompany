package com.app.webapplication.model.dao.queryexecution.mapper;

import com.app.webapplication.model.dao.queryexecution.QueryExe;
import com.app.webapplication.model.entity.Port;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PortMapper extends QueryExe<Port> {
    @Override
    public Port mapToEntity(ResultSet rs) throws SQLException {
        int k = 0;
        return Port.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .build();
    }
}
