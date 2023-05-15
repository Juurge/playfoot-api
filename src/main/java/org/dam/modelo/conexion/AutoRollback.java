package org.dam.modelo.conexion;

import java.sql.Connection;
import java.sql.SQLException;

public class AutoRollback implements AutoCloseable{
    private Connection conn;
    private boolean commited;

    public AutoRollback(Connection conn) throws SQLException {
        this.conn = conn;

        conn.setAutoCommit(false);
        commited = false;
    }

    public void commit() throws SQLException {
        conn.commit();
        commited=true;
    }

    @Override
    public void close() throws SQLException {
        if (!commited) {
            conn.rollback();
        }

        conn.setAutoCommit(true);
    }
}
