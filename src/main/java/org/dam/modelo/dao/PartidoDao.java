package org.dam.modelo.dao;

import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartidoDao {

    public static void crearPartido(String fecha, String hora, String resultado, String ganador, String integrantes,
                                    String goleadores, Enum tipoPartido, Enum estado, int contador,
                                    String comentarios) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "insert into partidos (fecha, hora, resultado, ganador, integrantes," +
                "goleadores, tipo, estado, contador, comentarios) values (?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, fecha);
        query.setString(2, hora);
        query.setString(3, resultado);
        query.setString(4, ganador);
        query.setString(5,integrantes);
        query.setString(6, goleadores);
        query.setString(7, tipoPartido.name());
        query.setString(8, estado.name());
        query.setInt(9, contador);
        query.setString(10, comentarios);

        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
    public static void consultar(int idPartido) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select * from instalaciones where id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idPartido);

        ResultSet rs=query.executeQuery();
        while(rs.next()){
            //duda, que hago ahora? porque nosotros no mostramos nada por pantalla
        }
        autoRollback.commit();
        conexion.disconnect();

    }
    public static void actualizar(){
        //????
    }

}
