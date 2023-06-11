package org.dam.controlador;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.dam.modelo.dao.EquipoDao;
import org.dam.modelo.http.HttpCodes;
import org.dam.modelo.vo.EquipoVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/equipo")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EquipoController {

    @Operation(summary = "Buscar equipo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarEquipo", produces = "application/json")
    public ResponseEntity<String> buscarEquipo(@RequestParam int id) throws JsonProcessingException, IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        EquipoVo equipo=EquipoDao.verEquipo(id);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String usuarioJson = objectMapper.writeValueAsString(equipo);
        if(equipo.getId()==0){
            return new ResponseEntity(usuarioJson,HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity(usuarioJson, HttpStatus.OK);
        }
    }

    @Operation(summary = "Crear equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.CREATED, description = "Creado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
    })
    @PostMapping(value = "/crearEquipo", produces = "application/json")
    public ResponseEntity crearEquipo(@RequestBody EquipoVo equipo) {
        equipo.setIntegrantes(""+equipo.getIdAdministrador());
        EquipoDao.registrarEquipo(equipo);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar equipo nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/modificarEquipoNombre", produces = "application/json")
    public ResponseEntity modificarEquipo(@RequestBody EquipoVo equipo, @RequestParam int id) throws SQLException {
        EquipoDao.modificarEquipoNombre(equipo,id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Modificar equipo integrantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/modificarEquipoIntegrantes", produces = "application/json")
    public ResponseEntity modificarEquipoIntegrantes(@RequestBody EquipoVo equipo, @RequestParam int id) throws SQLException {
        EquipoDao.aniadirIntegranteEquipo(equipo,id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Eliminar equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe")
    })
    @DeleteMapping (value = "/eliminarEquipo", produces = "application/json")
    public ResponseEntity eliminarEquipo(@RequestParam int id){
        EquipoDao.borrarEquipo(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Consultar todos los equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/consultarTodos", produces = "application/json")
    public ResponseEntity<ArrayList<EquipoVo>> consultarTodos() throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<EquipoVo> equipos = EquipoDao.consultarTodos();
        String equiposJson = objectMapper.writeValueAsString(equipos);
        return new ResponseEntity(equiposJson, HttpStatus.OK);
    }

    @Operation(summary = "Borrar  integrantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/borrarEquipoIntegrantes", produces = "application/json")
    public ResponseEntity borrarEquipoIntegrantes(@RequestParam String integrantes, @RequestParam int id) throws SQLException {
        EquipoDao.borrarIntegranteEquipo(integrantes,id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Consultar administrador de equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Es administrador"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "No es administrador"),
    })
    @PutMapping(value = "/verAdminEquipo", produces = "application/json")
    public ResponseEntity verAdminEquipo(@RequestParam String nombre, @RequestParam int id) throws SQLException {
        if(EquipoDao.esAdminEquipo(nombre,id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Dame el id del equipo por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Equipo encontrado"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El equipo no existe"),
    })
    @PutMapping(value = "/consultarIdEquipo", produces = "application/json")
    public ResponseEntity<Integer> consultarIdEquipo(@RequestParam String nombre)throws SQLException {
        int id=EquipoDao.dameIdEquipo(nombre);
        if(id!=0){
            return new ResponseEntity(id,HttpStatus.OK);
        }
        else {
            return new ResponseEntity(id,HttpStatus.NOT_FOUND);
        }
    }

}
