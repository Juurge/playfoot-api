package org.dam.controlador;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.dam.modelo.dao.UsuarioDao;
import org.dam.modelo.http.HttpCodes;
import org.dam.modelo.vo.Prueba;
import org.dam.modelo.vo.UsuarioVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/usuario")
public class UsuarioController {

    @Operation(summary = "Buscar usuario por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
   })
    @GetMapping(value = "/buscarUsuario", produces = "application/json")
    public ResponseEntity<String> buscarUsuario(@RequestParam int id) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        UsuarioVo user= UsuarioDao.consultarUsuario(id);
        String usuarioJson = objectMapper.writeValueAsString(user);
        if(user.getId()==0) return new ResponseEntity(usuarioJson,HttpStatus.NOT_FOUND);
        else return new ResponseEntity(usuarioJson, HttpStatus.OK);
    }

    @Operation(summary = "Crear usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.CREATED, description = "Creado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
    })
    @PostMapping(value = "/crearUsuario", produces = "application/json")
    public ResponseEntity crearUsuario(@RequestBody UsuarioVo user) throws SQLException {
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

}
