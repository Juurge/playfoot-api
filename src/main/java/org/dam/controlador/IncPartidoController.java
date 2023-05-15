package org.dam.controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.dam.modelo.http.HttpCodes;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/incPartido")
public class IncPartidoController {

    @Operation(summary = "Buscar incidencia de partido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarIncPartido")
    public String prueba() {
        String hola="INCPARTIDO";
        log.debug("Prueba. {}", hola);
        return hola;
    }

    @Operation(summary = "Crear incidencia de partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.CREATED, description = "Creado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
    })
    @PostMapping(value = "/crearIncPartido", produces = "application/json")
    public String pruebaPost() {
        return "post";
    }

    @Operation(summary = "Modificar incidencia de partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/modificarIncPartido", produces = "application/json")
    public String pruebaPut() {
        return "put";
    }

    @Operation(summary = "Eliminar incidencia de partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe")
    })
    @DeleteMapping (value = "/eliminarIncPartido", produces = "application/json")
    public String pruebaDelete(){
        return "delete";
    }

}
