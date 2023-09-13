package ua.foxminded.javaspring.kocherga.web_application.models.converter;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static java.sql.Types.OTHER;

public class RoleNameConverter implements UserType<RoleName> {

    @Override
    public int getSqlType() {
        return OTHER;
    }

    @Override
    public Class<RoleName> returnedClass() {
        return RoleName.class;
    }

    @Override
    public boolean equals(RoleName rn1, RoleName rn2) {
        return Objects.equals(rn1, rn2);
    }

    @Override
    public int hashCode(RoleName rn1) {
        return Objects.hashCode(rn1);
    }

    @Override
    public RoleName nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        String string = rs.getString( position );
        return rs.wasNull() ? null : RoleName.valueOf(string);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, RoleName value, int index, SharedSessionContractImplementor session) throws SQLException {
        if ( value == null ) {
            st.setNull(index, OTHER);
        } else {
            st.setObject(index, value.name(), OTHER);
        }
    }

    @Override
    public RoleName deepCopy(RoleName value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(RoleName value) {
        return value;
    }

    @Override
    public RoleName assemble(Serializable cached, Object owner) {
        return (RoleName) cached;
    }
}
