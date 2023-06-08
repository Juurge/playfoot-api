package org.dam.modelo.dao;

import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;
import org.dam.modelo.vo.PartidoVo;
import org.dam.modelo.vo.UsuarioVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDao {

    public static void crearUsuario(UsuarioVo miUsuario) throws SQLException {

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "insert into usuarios (nombre, apellidos, telefono, dni, correo, password, " +
                "posicion, partidos_jugados, goles, puntos) values (?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, miUsuario.getNombre());
        query.setString(2, miUsuario.getApellidos());
        query.setInt(3, miUsuario.getTelefono());
        query.setString(4, miUsuario.getDni());
        query.setString(5, miUsuario.getCorreo());
        query.setString(6, miUsuario.getPassword());
        query.setString(7, miUsuario.getPosicion());
        query.setString(8, miUsuario.getPartidosJugados());
        query.setInt(9, miUsuario.getGoles());
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
            user.setPuntos(rs.getInt("puntos"));
            user.setIdEquipoAdministracion(rs.getInt("idEquipoAdministracion"));
        }

        autoRollback.commit();
        conexion.disconnect();

        return user;
    }
    public static void actualizarUsuario(UsuarioVo miUsuario,int id) throws SQLException {

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "UPDATE usuarios SET nombre = ?, apellidos = ?, telefono =?, dni =?," +
                "correo = ?, posicion = ? WHERE id_usuario=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, miUsuario.getNombre());
        query.setString(2, miUsuario.getApellidos());
        query.setInt(3, miUsuario.getTelefono());
        query.setString(4, miUsuario.getDni());
        query.setString(5, miUsuario.getCorreo());
        query.setString(6, miUsuario.getPosicion());
        query.setInt(7, id);
        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();

    }
    public static void borrarUsuario(int idUsuario) throws SQLException {

        UsuarioVo user = consultarUsuario(idUsuario);
        PartidoVo partido = PartidoDao.consultarPartidoPorIdAdministrador(idUsuario);

        if (partido!=null){
            int idPartido=partido.getId();
            PartidoDao.eliminarPartido(idPartido);
        }

        if (user.getIdEquipoAdministracion()!=0){
            EquipoDao.cambioRolEquipo(user.getId(),user.getIdEquipoAdministracion());
        }

        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccionDeleteUsuario = "DELETE FROM usuarios WHERE id_usuario =?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccionDeleteUsuario);

        query.setInt(1, idUsuario);
        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }

    public static boolean loginUsuario(String correo, String password) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "select * from usuarios where correo=? and password=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, correo);
        query.setString(2, password);

        ResultSet rs=query.executeQuery();

        while(rs.next()){
            if (correo.equalsIgnoreCase(rs.getString("correo"))){
                if (password.equalsIgnoreCase(rs.getString("password"))){
                    return true;
                }
            }
        }

        autoRollback.commit();
        conexion.disconnect();

        return false;
    }

    public static void modificarPassword(String correo, String password) throws SQLException {

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select * from usuarios where correo=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, correo);

        ResultSet rs=query.executeQuery();

        while(rs.next()){
            if (rs.getString("correo").equalsIgnoreCase(correo)){
                String instruccionSetteo = "UPDATE usuarios SET password = ? WHERE correo=?;";
                PreparedStatement querySetteo = conexion.getConnection().prepareStatement(instruccionSetteo);
                querySetteo.setString(1, password);
                querySetteo.setString(2, correo);
                querySetteo.executeUpdate();
            }
        }

        autoRollback.commit();
        conexion.disconnect();

    }

    public static ArrayList<UsuarioVo> consultarTodosLosUsuarios() throws SQLException{

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select * from usuarios;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        ResultSet rs=query.executeQuery();
        ArrayList<UsuarioVo> users = new ArrayList<>();
        UsuarioVo user = new UsuarioVo();

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
            user.setPuntos(rs.getInt("puntos"));
            user.setIdEquipoAdministracion(rs.getInt("idEquipoAdministracion"));
            users.add(user);
        }

        autoRollback.commit();
        conexion.disconnect();

        return users;

    }
    public static int getIdUsuario(String correo,String password) throws SQLException {

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select id_usuario from usuarios where correo =? and password = ?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, correo);
        query.setString(2,password);

        ResultSet rs=query.executeQuery();

        autoRollback.commit();
        conexion.disconnect();

        int id= rs.getInt("id_usuario");

        return id;

    }
}
