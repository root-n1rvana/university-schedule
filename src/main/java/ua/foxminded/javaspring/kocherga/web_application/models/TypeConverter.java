package ua.foxminded.javaspring.kocherga.web_application.models;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class TypeConverter implements UserType<RoleName> {
    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class<RoleName> returnedClass() {
        return RoleName.class;
    }

    @Override
    public boolean equals(RoleName role, RoleName j1) {
        return role.name().equals(j1.name());
    }

    @Override
    public int hashCode(RoleName role) {
        return 0;
    }

    @Override
    public RoleName nullSafeGet(ResultSet resultSet, int i,
                                SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        System.out.println("=============================" + "resultSet == null "+ resultSet == null);
        System.out.println(resultSet.getString(i));
        System.out.println(i);
        return RoleName.valueOf(resultSet.getString(i));
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, RoleName role,
                            int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (role != null) {
            preparedStatement.setObject(i, role.name(), Types.VARCHAR);
        }
    }

    @Override
    public RoleName deepCopy(RoleName role) {
        return role;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(RoleName role) {
        return null;
    }

    @Override
    public RoleName assemble(Serializable serializable, Object o) {
        return null;
    }

    @Override
    public RoleName replace(RoleName role, RoleName j1, Object o) {
        return j1;
    }
}
