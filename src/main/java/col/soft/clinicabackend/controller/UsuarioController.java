
package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.dto.UsuarioResponse;
import col.soft.clinicabackend.service.UsuarioService;
import col.soft.clinicabackend.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/usuario")
public class UsuarioController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping()
    public ResponseEntity<?> getUsuarios(){
        List<UsuarioResponse> listaUsuarioResponse=null;
        try{
            listaUsuarioResponse = usuarioService.getUsuarios();
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaUsuarioResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Usuarios not found").build());
        return ResponseEntity.ok(listaUsuarioResponse);
    }
    
    @GetMapping("/find/by/nombre-and-password")   
    public ResponseEntity<?> findEmpleadoByNombreAndPassword(@RequestParam String nombreUsuario, @RequestParam String passwordUsuario){
        logger.info(">findByNombreAndPassword");
        UsuarioResponse usuarioResponse = null;
        try{
            usuarioResponse = usuarioService.getUsuarioByNombreAndPassword(nombreUsuario, passwordUsuario);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(usuarioResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Usuario not found").build());
        return ResponseEntity.ok(usuarioResponse);
    }
}
