
package col.soft.clinicabackend.repository;

import col.soft.clinicabackend.model.HistorialClinico;
import col.soft.clinicabackend.model.Paciente;
import java.sql.Date;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class HistorialClinicoRepositoryTest {

    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private Paciente paciente;
    private Paciente paciente2;

    @BeforeEach
    public void setUp() {
        init();
    }

    public void init() {
        paciente = Paciente.builder()
                .dni(12345679L)
                .nombres("Juan")
                .apellidos("Perez")
                .fechaDeNacimiento(new Date(2000-04-05)) 
                .correoElectronico("juan.perez@example.com")
                .numeroCelular(987654321L)
                .domicilio("Calle Principal 123")
                .build();
        paciente = pacienteRepository.save(paciente);
        
        
        paciente2 = Paciente.builder()
        .dni(12345671L)
        .nombres("Juana")
        .apellidos("Perez")
        .fechaDeNacimiento(new Date(2000-02-01)) 
        .correoElectronico("juana.perez@example.com")
        .numeroCelular(987654312L)
        .domicilio("Calle Principal 123")
        .build();
        paciente2 = pacienteRepository.save(paciente);
        
        

        HistorialClinico historial1 = HistorialClinico.builder()
                .idHistorialClinico(null)
                .paciente(pacienteRepository.findById(12345679L).get())
                .fecha(null)//la bd le asigna automaticamente la fecha de creación
                .observaciones(null)
                .diagnosticos(null)
                .build();
        historialClinicoRepository.save(historial1);
        
    }
    
    @Test
    public void historialClinicoRepository_findByDni() {
        // Obtener un historial por su ID 
        HistorialClinico historial = historialClinicoRepository.findByPacienteDni(12345679L);
        Assertions.assertThat(historial).isNotNull();
        Assertions.assertThat(historial.getPaciente().getDni()).isEqualTo(paciente.getDni());
        Assertions.assertThat(historial.getObservaciones()).isEqualTo("Consulta inicial");
    }

    @Test
    public void historialClinicoRepository_save() {
        // Crear un nuevo historial clínico
        HistorialClinico historial = HistorialClinico.builder()
                .paciente(pacienteRepository.findById(12345671L).get())
                .fecha(null)
                .observaciones("Sintomas leves")
                .diagnosticos("Resfriado leve")
                .build();
        HistorialClinico savedHistorial = historialClinicoRepository.save(historial);

        Assertions.assertThat(savedHistorial).isNotNull();
        Assertions.assertThat(savedHistorial.getIdHistorialClinico()).isNotNull();
        Assertions.assertThat(savedHistorial.getObservaciones()).isEqualTo("Resfriado leve");
    }

}
