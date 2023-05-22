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

    public static UsuarioVo consultarUsuario(int idUsuario) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select * from usuarios where id_usuario=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idUsuario);

        ResultSet rs=query.executeQuery();
        UsuarioVo user= new UsuarioVo();
        while(rs.next()){
            user.setId(rs.getInt("id_usuario"));
            user.setNombre(rs.getString("nombre"));
            user.setApellidos(rs.getString("apellidos"));
            user.setTelefono(rs.getInt("telefono"));
            user.setDni(rs.getString("dni"));
            user.setCorreo(rs.getString("correo"));
            user.setPosicion(rs.getString("posicion"));
            user.setPartidosJugados(rs.getString("partidos_jugados"));
            user.setGoles(rs.getInt("goles"));
            user.setImagen(rs.getString("imagen"));
            user.setPuntos(rs.getInt("puntos"));
        }

        autoRollback.commit();
        conexion.disconnect();
        if(user.getId()==0) return null;
        else return user;
    }
    public static void actualizarUsuario(UsuarioVo miUsuario,int id) throws SQLException {

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "UPDATE usuarios SET nombre = ?, apellidos = ?, telefono =?, dni =?," +
                "correo = ?, posicion = ?, imagen = ? WHERE id_usuario=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, miUsuario.getNombre());
        query.setString(2, miUsuario.getApellidos());
        query.setInt(3, miUsuario.getTelefono());
        query.setString(4, miUsuario.getDni());
        query.setString(5, miUsuario.getCorreo());
        query.setString(6, miUsuario.getPosicion());
        query.setString(7, miUsuario.getImagen());
        query.setInt(8, id);
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
