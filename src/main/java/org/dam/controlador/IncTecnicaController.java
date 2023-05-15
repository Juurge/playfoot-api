package org.dam.controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.dam.modelo.http.HttpCodes;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/incTecnica")
public class IncTecnicaController {

    @Operation(summary = "Buscar incidencia técnica por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarIncTecnica")
    public String prueba() {
        String hola="INCTECNICA";
        log.debug("Prueba. {}", hola);
        return hola;
    }

    @Operation(summary = "Crear incidencia técnica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.CREATED, description = "Creado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
    })
    @PostMapping(value = "/crearIncTecnica", produces = "application/json")
    public String pruebaPost() {
        return "post";
    }

    @Operation(summary = "Modificar incidencia técnica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/modificarIncTecnica", produces = "application/json")
    public String pruebaPut() {
        return "put";
    }

    @Operation(summary = "Eliminar incidencia técnica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe")
    })
    @DeleteMapping (value = "/eliminarIncTecnica", produces = "application/json")
    public String pruebaDelete(){
        return "delete";
    }

}

