package org.dam.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.dam.modelo.dao.UsuarioDao;
import org.dam.modelo.http.HttpCodes;
import org.dam.modelo.vo.UsuarioVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UsuarioController {

    @Operation(summary = "Buscar usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarUsuario", produces = "application/json")
    public ResponseEntity<String> buscarUsuario(@RequestParam int id) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        UsuarioVo user = UsuarioDao.consultarUsuario(id);
        String usuarioJson = objectMapper.writeValueAsString(user);
        if (user.getId() == 0) {
            return new ResponseEntity(usuarioJson, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(usuarioJson, HttpStatus.OK);
        }
    }

    @Operation(summary = "Hacer login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Credenciales correctas."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "Credenciales incorrectas"),
    })
    @GetMapping(value = "/loginUsuario", produces = "application/json")
    public ResponseEntity loginUsuario(@RequestParam String correo, @RequestParam String password) throws IOException, SQLException {
        if (UsuarioDao.loginUsuario(correo, password)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Crear usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.CREATED, description = "Creado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
    })
    @PostMapping(value = "/crearUsuario", produces = "application/json")
    public ResponseEntity crearUsuario(@RequestBody Map<String, Object> json) throws SQLException {
        String nombre = (String) json.get("nombre");
        String apellidos = (String) json.get("apellidos");
        int telefono = Integer.parseInt(json.get("telefono").toString());
        String dni = (String) json.get("dni");
        String correo = (String) json.get("correo");
        String password = (String) json.get("password");
        UsuarioVo user=new UsuarioVo(nombre,apellidos,telefono,dni,correo,password);
        UsuarioDao.crearUsuario(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/modificarUsuario", produces = "application/json")
    public ResponseEntity modificarUsuario(@RequestBody UsuarioVo user,@RequestParam int id) throws SQLException {
        UsuarioDao.actualizarUsuario(user,id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Eliminar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe")
    })
    @DeleteMapping (value = "/eliminarUsuario", produces = "application/json")
    public ResponseEntity eliminarUsuario(@RequestParam int id) throws SQLException {
        UsuarioDao.borrarUsuario(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Modificar contraseña")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/modificarPassword", produces = "application/json")
    public ResponseEntity modificarPassword(@RequestParam String correo, @RequestParam String password) throws SQLException {
        UsuarioDao.modificarPassword(correo,password);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Consultar todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/consultarTodos", produces = "application/json")
    public ResponseEntity<ArrayList<UsuarioVo>> consultarTodos() throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<UsuarioVo> users = UsuarioDao.consultarTodosLosUsuarios();
        String usuariosJson = objectMapper.writeValueAsString(users);
        return new ResponseEntity(usuariosJson, HttpStatus.OK);
    }

}