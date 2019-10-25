package com.viiyue.mapper.spring.demo.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes( { boolean.class, Boolean.class } )
public class BooleanTypeHandler extends BaseTypeHandler<Boolean> {

	@Override
	public void setNonNullParameter( PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType ) throws SQLException {
		ps.setString( i, parameter != null && parameter ? "Y" : "N" );
	}

	@Override
	public Boolean getNullableResult( ResultSet rs, String columnName ) throws SQLException {
		return rs.getBoolean( columnName );
	}

	@Override
	public Boolean getNullableResult( ResultSet rs, int columnIndex ) throws SQLException {
		return rs.getBoolean( columnIndex );
	}

	@Override
	public Boolean getNullableResult( CallableStatement cs, int columnIndex ) throws SQLException {
		return cs.getBoolean( columnIndex );
	}

}
