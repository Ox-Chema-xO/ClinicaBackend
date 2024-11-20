
package col.soft.clinicabackend.dto;

import col.soft.clinicabackend.model.Empleado;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoResponse {
    private String nombres;
    private String apellidos;
    private Integer dni;
    private String nombreCargo;
    private String nombreLugar;
    private BigDecimal salario;
    private Integer numeroCelular;
    
    public static EmpleadoResponse fromEntity(Empleado empleado){
        return EmpleadoResponse.builder()
                .nombres(empleado.getNombres())
                .apellidos(empleado.getApellidos())
                .dni(empleado.getDni())
                .nombreCargo(empleado.getCargo().getNombreCargo())
                .nombreLugar(empleado.getLugarDeTrabajo().getNombreLugar())
                .salario(empleado.getCargo().getSalario())
                .numeroCelular(empleado.getNumeroCelular())
                .build();
    }
    
    public static List<EmpleadoResponse> fromEntities(List<Empleado> empleados){
        return empleados.stream()
                .map(EmpleadoResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
