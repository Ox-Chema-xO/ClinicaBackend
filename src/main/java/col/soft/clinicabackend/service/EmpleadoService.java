
package col.soft.clinicabackend.service;

import col.soft.clinicabackend.dto.EmpleadoRequest;
import col.soft.clinicabackend.dto.EmpleadoResponse;
import col.soft.clinicabackend.model.Cargo;
import col.soft.clinicabackend.model.Empleado;
import col.soft.clinicabackend.model.LugarDeTrabajo;
import col.soft.clinicabackend.repository.CargoRepository;
import col.soft.clinicabackend.repository.EmpleadoRepository;
import col.soft.clinicabackend.repository.LugarDeTrabajoRepository;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    CargoRepository cargoRepository;
    @Autowired
    LugarDeTrabajoRepository lugarDeTrabajoRepository;
    
    public List<EmpleadoResponse> getEmpleados(){
        return EmpleadoResponse.fromEntities(empleadoRepository.findAll());
    }
    
    public EmpleadoResponse getEmpleadoByDni(Integer dni){
        return EmpleadoResponse.fromEntity(empleadoRepository.findById(dni).get());
    }
    
    public EmpleadoResponse insertEmpleado(EmpleadoRequest empleadoRequest){
        Integer idCargo = empleadoRequest.getIdCargo();
        Cargo cargo = cargoRepository.findById(idCargo).get();
        if(cargo == null) return new EmpleadoResponse();
        
        Integer idLugarDeTrabajo = empleadoRequest.getIdLugarDeTrabajo();
        LugarDeTrabajo lugarDeTrabajo = lugarDeTrabajoRepository.findById(idLugarDeTrabajo).get();
        if(lugarDeTrabajo == null) return new EmpleadoResponse();
        
        Empleado empleado = new Empleado(
                    empleadoRequest.getDni(),
                    cargo,
                    lugarDeTrabajo,
                    empleadoRequest.getNombres(),
                    empleadoRequest.getApellidos(),
                    empleadoRequest.getFechaDeNacimiento(),
                    empleadoRequest.getCorreoElectronico(),
                    empleadoRequest.getNumeroCelular(),
                    empleadoRequest.getDomicilio()
        );
        empleado = empleadoRepository.save(empleado);
        EmpleadoResponse empleadoResponse = EmpleadoResponse.fromEntity(empleado);
        return empleadoResponse;
    }
    
    public EmpleadoResponse updateEmpleado(EmpleadoRequest empleadoRequest){
        Empleado empleado = empleadoRepository.findById(empleadoRequest.getDni()).get();
        if(empleado == null) return new EmpleadoResponse();
        
        Cargo cargo = cargoRepository.findById(empleadoRequest.getIdCargo()).get();
        if(cargo != null) empleado.setCargo(cargo);
        
        LugarDeTrabajo lugarDeTrabajo = lugarDeTrabajoRepository.findById(empleadoRequest.getIdLugarDeTrabajo()).get();
        if(lugarDeTrabajo != null) empleado.setLugarDeTrabajo(lugarDeTrabajo);
        
        String nombres = empleadoRequest.getNombres();
        if(nombres != null) empleado.setNombres(nombres);
        
        String apellidos = empleadoRequest.getApellidos();
        if(apellidos != null) empleado.setApellidos(apellidos);
        
        Date fechaDeNacimiento = empleadoRequest.getFechaDeNacimiento();
        if(fechaDeNacimiento != null) empleado.setFechaDeNacimiento(fechaDeNacimiento);
        
        String correoElectronico = empleadoRequest.getCorreoElectronico();
        if(correoElectronico != null) empleado.setCorreoElectronico(correoElectronico);
        
        Integer numeroCelular = empleadoRequest.getNumeroCelular();
        if(numeroCelular != null) empleado.setNumeroCelular(numeroCelular);
        
        String domicilio = empleadoRequest.getDomicilio();
        if(domicilio != null) empleado.setDomicilio(domicilio);
        
        empleado = empleadoRepository.save(empleado);
        EmpleadoResponse empleadoResponse = EmpleadoResponse.fromEntity(empleado);
        return empleadoResponse;
        
    }
    
    public void deleteEmpleado(Integer dni){
        empleadoRepository.deleteById(dni);
    }
}
