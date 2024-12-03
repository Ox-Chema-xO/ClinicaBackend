package col.soft.clinicabackend.controller;

import col.soft.clinicabackend.dto.HistorialClinicoRequest;
import col.soft.clinicabackend.dto.HistorialClinicoResponse;
import col.soft.clinicabackend.model.HistorialClinico;
import col.soft.clinicabackend.model.Paciente;
import col.soft.clinicabackend.service.HistorialClinicoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = HistorialClinicoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class HistorialClinicoControllerTest {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HistorialClinicoService historialClinicoService;
    
    Paciente paciente;
    HistorialClinicoRequest historialClinicoRequest;
    HistorialClinicoResponse historialClinicoResponse;
    
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
        
        Long dniPaciente = paciente.getDni();


        historialClinicoRequest = HistorialClinicoRequest.builder()
                .idHistorialClinico(null)
                .dni(dniPaciente)
                .fecha(null)
                .observaciones(null)
                .diagnosticos(null)
                .build();
        
        historialClinicoResponse=HistorialClinicoResponse.builder()
                .idHistorialClinico("HC3")
                .paciente(paciente)
                .fecha(null)
                .observaciones(null)
                .diagnosticos(null)
                .build();         
    }
   
    @Test
    public void testFindHistorialClinicoByPacienteDni() throws Exception {
        logger.info(">Test-HistorialClinicoController_findPacienteByDni: ");
        when(historialClinicoService.findHistorialClinicoByPacienteDni(historialClinicoRequest.getDni()))
                .thenReturn(historialClinicoResponse);

        ResultActions response = mockMvc.perform(
                get("/api/v1/historialclinico/{dni}", historialClinicoRequest.getDni())
                    .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$.idHistorialClinico").value("HC3"));
        response.andExpect(jsonPath("$.paciente.dni").value(92345679L));
    }

    @Test
    public void testInsertHistorialClinico() throws Exception{
       Mockito.when(historialClinicoService.insertHistorialClinico(historialClinicoRequest)).thenReturn(historialClinicoResponse);
        
        logger.info(">Test-historialClinico: " + historialClinicoResponse.toString());
        
        MockHttpServletResponse response = mockMvc
                .perform(post("/api/v1/historialclinico")
                        .content((new ObjectMapper()).writeValueAsString(historialClinicoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();
       
        Assertions.assertEquals(response.getStatus(),200);
        Assertions.assertEquals(historialClinicoRequest.getObservaciones(), 
                (new ObjectMapper()).readValue(response.getContentAsString(), HistorialClinicoResponse.class).getObservaciones());   
    }
    
}
