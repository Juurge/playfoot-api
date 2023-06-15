package org.dam.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.dam.modelo.dao.IncTecnicaDao;
import org.dam.modelo.dao.UsuarioDao;
import org.dam.modelo.http.HttpCodes;
import org.dam.modelo.vo.IncTecnicaVo;
import org.dam.modelo.vo.UsuarioVo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Slf4j
@RequestMapping("/incTecnica")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class IncTecnicaController {

    @Operation(summary = "Buscar incidencia técnica por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarIncTecnica", produces = "application/json")
    public ResponseEntity<String> buscarIncTecnica(@RequestParam int id) throws JsonProcessingException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        IncTecnicaVo inc = IncTecnicaDao.verIncTecnica(id);
        String incJson = objectMapper.writeValueAsString(inc);
        if (inc.getId() == 0) {
            return new ResponseEntity(incJson, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(incJson, HttpStatus.OK);
        }
    }

    @Operation(summary = "Crear incidencia técnica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.CREATED, description = "Creado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
    })
    @PostMapping(value = "/crearIncTecnica", produces = "application/json")
    public ResponseEntity crearIncTecnica(@RequestBody IncTecnicaVo inc) throws SQLException {
        IncTecnicaDao.registrarIncTecnica(inc);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar incidencia técnica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/modificarIncTecnica", produces = "application/json")
    public ResponseEntity modificarIncTecnica(@RequestBody IncTecnicaVo inc, @RequestParam int id) throws SQLException {
        IncTecnicaDao.actualizarIncTecnica(inc, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Eliminar incidencia técnica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe")
    })
    @DeleteMapping (value = "/eliminarIncTecnica", produces = "application/json")
    public ResponseEntity eliminarIncTecnica(@RequestParam int id) throws SQLException {
        IncTecnicaDao.borrarIncTecnica(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}

