package dev.mybatise.typehander;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mybatise.model.Address;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Wes Lin
 */
//@MappedJdbcTypes({JdbcType.CHAR})
//@MappedTypes(Address.class)
public class AddressTypeHandler extends BaseTypeHandler<Address> {

    private ObjectMapper mapper = new ObjectMapper();

    private Address jsonToObject(String json) throws JsonProcessingException {
        if (json == null) {
            return null;
        }
        return mapper.readValue(json, Address.class);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Address parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, mapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address getNullableResult(ResultSet rs, String columnName) throws SQLException {
        try {
            return jsonToObject(rs.getString(columnName));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            return jsonToObject(rs.getString(columnIndex));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            return jsonToObject(cs.getString(columnIndex));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
