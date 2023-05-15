package org.dam.controlador;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.dam.modelo.http.HttpCodes;
import org.dam.modelo.vo.Prueba;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/usuario")
public class UsuarioController {

    @Operation(summary = "Buscar usuario por ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
//            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
//    })
    @GetMapping(value = "/buscarUsuario", produces = "application/json")
    public ResponseEntity<Prueba> pruebaGet() throws JsonProcessingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Prueba prueba = new Prueba(1,"Alba");
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        String usuarioJson = objectMapper.writeValueAsString(prueba);
        return new ResponseEntity(prueba, HttpStatus.OK);
    }

    @Operation(summary = "Crear usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.CREATED, description = "Creado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
    })
    @PostMapping(value = "/crearUsuario", produces = "application/json")
    public String pruebaPost() {
        return "post";
    }

    @Operation(summary = "Modificar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/modificarUsuario", produces = "application/json")
    public String pruebaPut() {
        return "put";
    }

    @Operation(summary = "Eliminar usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
        @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe")
    })
    @DeleteMapping (value = "/eliminarUsuario", produces = "application/json")
    public String pruebaDelete(){
        return "delete";
    }

}
