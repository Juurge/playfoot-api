package org.dam.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.dam.modelo.dao.IncPartidoDao;
import org.dam.modelo.dao.UsuarioDao;
import org.dam.modelo.http.HttpCodes;
import org.dam.modelo.vo.IncPartidoVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Slf4j
@RequestMapping("/incPartido")
public class IncPartidoController {

    @Operation(summary = "Buscar incidencia de partido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarIncPartido", produces = "application/json")
    public ResponseEntity<String> buscarIncPartido(@RequestParam int id) throws SQLException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        IncPartidoVo inc= IncPartidoDao.consultarIncPartido(id);
        String incJason = objectMapper.writeValueAsString(inc);
        if (inc.getId() == 0) {
            return new ResponseEntity(incJason, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(incJason, HttpStatus.OK);
        }
    }

    @Operation(summary = "Crear incidencia de partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.CREATED, description = "Creado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
    })
    @PostMapping(value = "/crearIncPartido", produces = "application/json")
    public ResponseEntity crearIncPartido(@RequestBody IncPartidoVo inc) throws SQLException {
        IncPartidoDao.registrarIncPartido(inc);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar incidencia de partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/modificarIncPartido", produces = "application/json")
    public ResponseEntity modificarIncPartido(@RequestBody IncPartidoVo inc, int id) throws SQLException {
        IncPartidoDao.actualizarIncPartido(inc,id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Eliminar incidencia de partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe")
    })
    @DeleteMapping (value = "/eliminarIncPartido", produces = "application/json")
    public ResponseEntity eliminarIncPartido(@RequestParam int id) throws SQLException {
        IncPartidoDao.borrarIncPartido(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
