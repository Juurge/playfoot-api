package org.dam.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.dam.modelo.dao.PartidoDao;
import org.dam.modelo.http.HttpCodes;
import org.dam.modelo.vo.PartidoVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@Slf4j
@RequestMapping("/partido")
public class PartidoController {

    @Operation(summary = "Buscar partido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarPartido", produces = "application/json")
    public ResponseEntity<String> buscarPartido(@RequestParam int id) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        PartidoVo partido = PartidoDao.consultarPartido(id);
        String partidoJson = objectMapper.writeValueAsString(partido);
        if (partido.getId() == 0) {
            return new ResponseEntity(partidoJson, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(partidoJson, HttpStatus.OK);
        }
    }

    @Operation(summary = "Crear partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.CREATED, description = "Creado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
    })
    @PostMapping(value = "/crearPartido", produces = "application/json")
    public ResponseEntity crearPartido(@RequestBody PartidoVo partido) throws SQLException {
        PartidoDao.crearPartido(partido);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/modificarPartido", produces = "application/json")
    public ResponseEntity modificarPartido(@RequestBody PartidoVo partido,@RequestParam int id) throws SQLException {
        PartidoDao.actualizarPartido(partido,id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

