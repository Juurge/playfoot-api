package org.dam.modelo.dao;

import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;
import org.dam.modelo.vo.UsuarioVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {


    public static void crearUsuario(UsuarioVo miUsuario) throws SQLException {

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "insert into usuarios (nombre, apellidos, telefono, dni, correo," +
                "posicion, partidos_jugados, goles, imagen, puntos) values (?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, miUsuario.getNombre());
        query.setString(2, miUsuario.getApellidos());
        query.setInt(3, miUsuario.getTelefono());
        query.setString(4, miUsuario.getDni());
        query.setString(5, miUsuario.getCorreo());
        query.setString(6, miUsuario.getPosicion());
        query.setString(7, miUsuario.getPartidosJugados());
        query.setInt(8, miUsuario.getGoles());
        query.setString(9, miUsuario.getImagen());
        query.setInt(10, miUsuario.getPuntos());

        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();


    }

    public static void consultarUsuario(int idUsuario) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select * from usuarios where id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idUsuario);

        ResultSet rs=query.executeQuery();
        while(rs.next()){
            //duda, que hago ahora? porque nosotros no mostramos nada por pantalla
        }
        autoRollback.commit();
        conexion.disconnect();
    }
    public static void actualizarUsuario(UsuarioVo miUsuario,int id) throws SQLException {

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "UPDATE usuarios SET nombre = ?, apellidos = ?, telefono =?, dni =?," +
                "correo = ?, posicion = ?, partidos_jugados = ?, goles = ?, imagen = ?," +
                "puntos = ? WHERE id_Usuario=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, miUsuario.getNombre());
        query.setString(2, miUsuario.getApellidos());
        query.setInt(3, miUsuario.getTelefono());
        query.setString(4, miUsuario.getDni());
        query.setString(5, miUsuario.getCorreo());
        query.setString(6, miUsuario.getPosicion());
        query.setString(7, miUsuario.getPartidosJugados());
        query.setInt(8, miUsuario.getGoles());
        query.setString(9, miUsuario.getImagen());
        query.setInt(10, miUsuario.getPuntos());
        query.setInt(11, id);
        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();

    }
    public static void borrarUsuario(int idUsuario) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "DELETE FROM usuarios WHERE id_usuario =?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idUsuario);
        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
}
