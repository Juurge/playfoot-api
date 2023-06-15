package org.dam.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;
import org.dam.modelo.dao.EquipoDao;
import org.dam.modelo.dao.PartidoDao;
import org.dam.modelo.dao.UsuarioDao;
import org.dam.modelo.http.HttpCodes;
import org.dam.modelo.vo.PartidoVo;
import org.dam.modelo.vo.UsuarioVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/partido")
@CrossOrigin(origins = "http://127.0.0.1:5501")
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
        if(PartidoDao.tieneAlgunPartidoIniciado(partido.getIdAdministrador())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else{
            PartidoDao.crearPartido(partido);
            return new ResponseEntity(HttpStatus.CREATED);
        }

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

    @Operation(summary = "Consultar todos los partidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/consultarTodos", produces = "application/json")
    public ResponseEntity<ArrayList<PartidoVo>> consultarTodos() throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<PartidoVo> partidos = PartidoDao.consultarTodos();
        String partidosJson = objectMapper.writeValueAsString(partidos);
        return new ResponseEntity(partidosJson, HttpStatus.OK);
    }

    @Operation(summary = "Unirse partido individual")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/unirsePartidoIndividual", produces = "application/json")
    public ResponseEntity unirsePartidoIndividual(@RequestParam int idUsuario,@RequestParam int idPartido) throws SQLException {
        if (PartidoDao.consultarIntegrantesPartido(idPartido) && !PartidoDao.comprobarExisteJugadorPartido(idPartido,idUsuario)){
            PartidoDao.unirsePartidoPublico(idUsuario,idPartido);
            PartidoDao.aumentarJugadores(idPartido);
            return new ResponseEntity(HttpStatus.OK);
        } else if (!PartidoDao.comprobarExisteJugadorPartido(idPartido,idUsuario)) {
            PartidoDao.unirsePartidoPublico2(idUsuario,idPartido);
            PartidoDao.aumentarJugadores(idPartido);
            return new ResponseEntity(HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/devueldeIdPorCorreo", produces = "application/json")
    public ResponseEntity<Integer> devueldeIdPorCorreo(@RequestParam String correo) throws IOException, SQLException {
        if (UsuarioDao.consultarUsuarioCorreo(correo)>0) {
            int id=UsuarioDao.consultarUsuarioCorreo(correo);
            return new ResponseEntity(id,HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "Unirse partido por equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/unirsePartidoEquipos", produces = "application/json")
    public ResponseEntity unirsePartidoEquipos(@RequestParam int idEquipo,@RequestParam int idPartido) throws SQLException {
      if(PartidoDao.comprobarExisteEquipoPartido(idEquipo,idPartido)){
           PartidoDao.unirsePartidoEquipos(idEquipo,idPartido);
           PartidoDao.aumentarNumeroEquipo(idPartido);
           return new ResponseEntity(HttpStatus.OK);
       }
       else{

          return new ResponseEntity(HttpStatus.NOT_FOUND);
       }







    }




    @Operation(summary = "Consultar pista individual")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/consultaPistaIndividual", produces = "application/json")
    public ResponseEntity<Integer> consultaPistaIndividual(@RequestParam String fecha, @RequestParam String hora,@RequestParam int id) throws IOException, SQLException {
        int cod= PartidoDao.estadoPistaIndividual(fecha,hora,id);
        return new ResponseEntity(cod, HttpStatus.OK);
    }
    @Operation(summary = "Buscar id_partido por fecha, hora e instalacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarIDPartidoPorFechaHoraInstalacion", produces = "application/json")
    public ResponseEntity<Integer> buscarIDPartidoPorFechaHoraInstalacion(@RequestParam String fecha, @RequestParam String hora, @RequestParam int idInstalacion) throws IOException, SQLException {
        int id=PartidoDao.devolverIdPartido(fecha, hora, idInstalacion);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @Operation(summary = "Buscar id_partido por estado y idAdministrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarIDPartidoPorEstadoIdAdministrador", produces = "application/json")
    public ResponseEntity<Integer> buscarIDPartidoPorEstadoIdAdministrador(@RequestParam String estado, @RequestParam int idAdministrador) throws IOException, SQLException {
        int id=PartidoDao.devolverIdPartidoEstadoIdAdministrador(estado,idAdministrador);
        if (id!=0){
            return new ResponseEntity(id, HttpStatus.OK);

        } else {
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Actualizar goles jugador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/actualizarGolesJugador", produces = "application/json")
    public ResponseEntity actualizarGolesJugador(@RequestParam String goleadores) throws SQLException {
        PartidoDao.actualizarGoles(goleadores);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Actualizar partidos jugados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/actualizarPartidosJugados", produces = "application/json")
    public ResponseEntity actualizarPartidosJugados(@RequestParam String integrantes) throws SQLException {
        PartidoDao.actualizarPartidosJugados(integrantes);
        return new ResponseEntity(HttpStatus.OK);
    }
    @Operation(summary = "Buscar integrantes de un partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarIntegrantesPartido", produces = "application/json")
    public ResponseEntity<String> buscarIntegrantesPartido(@RequestParam int idPartido) throws IOException, SQLException {
        String integrantes="";
        integrantes=PartidoDao.consultaIntegrantesPartido(idPartido);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(integrantes);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @Operation(summary = "Buscar integrantes_2 de un partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarIntegrantes2Partido", produces = "application/json")
    public ResponseEntity<String> buscarIntegrantes2Partido(@RequestParam int idPartido) throws IOException, SQLException {
        String integrantes2="";
        integrantes2=PartidoDao.consultaIntegrantes2Partido(idPartido);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(integrantes2);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @Operation(summary = "Buscar tipo partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "ID correcto."),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @GetMapping(value = "/buscarTipoPartido", produces = "application/json")
    public ResponseEntity<Integer> buscarTipoPartido(@RequestParam int idPartido) throws IOException, SQLException {
        int tipo=PartidoDao.consultaTipoPartido(idPartido);
        return new ResponseEntity(tipo, HttpStatus.OK);
    }
    @Operation(summary = "Actualizar partidos ganados equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/actualizarPartidosGanadosEquipo", produces = "application/json")
    public ResponseEntity actualizarPartidosGanadosEquipo(@RequestParam String nombreEquipo) throws SQLException {
        PartidoDao.actualizarPartidosGanadosEquipo(nombreEquipo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Actualizar partidos perdidos equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpCodes.OK, description = "Modificado correctamente"),
            @ApiResponse(responseCode = HttpCodes.BAD_REQUEST, description = "El cuerpo de la petición es incorrecto"),
            @ApiResponse(responseCode = HttpCodes.NOT_FOUND, description = "El ID introducido no existe"),
    })
    @PutMapping(value = "/actualizarPartidosPerdidosEquipo", produces = "application/json")
    public ResponseEntity actualizarPartidosPerdidosEquipo(@RequestParam String nombreEquipo) throws SQLException {
        PartidoDao.actualizarPartidosPerdidosEquipo(nombreEquipo);
        return new ResponseEntity(HttpStatus.OK);
    }







}


