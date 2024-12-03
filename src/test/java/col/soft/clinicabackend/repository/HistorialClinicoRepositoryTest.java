
package col.soft.clinicabackend.repository;

import col.soft.clinicabackend.model.HistorialClinico;
import col.soft.clinicabackend.model.Paciente;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;
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
                .dni(92345679L)
                .nombres("Juan")
                .apellidos("Perez")
                .fechaDeNacimiento(Date.valueOf("2000-04-05")) 
                .correoElectronico("juan.perez@example.com")
                .numeroCelular(987654321L)
                .domicilio("Calle Principal 123")
                .build();
        paciente = pacienteRepository.save(paciente);
         
        paciente2 = Paciente.builder()
                .dni(91341619L)
                .nombres("Mariana")
                .apellidos("Villalobos")
                .fechaDeNacimiento(Date.valueOf("2000-01-07")) 
                .correoElectronico("mariana.villalobos@example.com")
                .numeroCelular(987654321L)
                .domicilio("Calle Principal 124")
                .build();
        paciente2 = pacienteRepository.save(paciente2);
        
        HistorialClinico historialClinico = HistorialClinico.builder()
                .idHistorialClinico("HC1")
                .paciente(paciente)
                .fecha(Date.valueOf("2024-04-05"))
                .observaciones(null)
                .diagnosticos(null)
                .build();
        historialClinicoRepository.save(historialClinico);
    }
    
    @Test
    public void historialClinicoRepository_findByDni() {
        HistorialClinico historialClinico = historialClinicoRepository.findByPacienteDni(92345679L);
        Assertions.assertThat(historialClinico).isNotNull();
        Assertions.assertThat(historialClinico.getPaciente().getDni()).isEqualTo(92345679L);
        Assertions.assertThat(historialClinico.getObservaciones()).isNull();
        Assertions.assertThat(historialClinico.getDiagnosticos()).isNull();
    }
    
    @Test
    public void historialClinicoRepository_insert() {
        HistorialClinico historialClinico = HistorialClinico.builder()
                .idHistorialClinico("HC2")
                .paciente(pacienteRepository.findById(91341619L).get())
                .fecha(Date.valueOf("2024-04-05"))
                .observaciones(null)
                .diagnosticos(null)
                .build();
        
        HistorialClinico newhistorialClinico = historialClinicoRepository.save(historialClinico);
        Assertions.assertThat(newhistorialClinico).isNotNull();
        Assertions.assertThat(newhistorialClinico.getIdHistorialClinico()).isNotNull();
        Assertions.assertThat(historialClinico.getPaciente().getApellidos()).isEqualTo("Villalobos");
        Assertions.assertThat(historialClinico.getObservaciones()).isNull();
        Assertions.assertThat(historialClinico.getDiagnosticos()).isNull();
        
    }
}
