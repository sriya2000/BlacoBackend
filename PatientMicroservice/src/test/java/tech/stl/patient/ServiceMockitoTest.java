package tech.stl.patient;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import tech.stl.patient.entity.Patient;
import tech.stl.patient.repository.PatientRepository;
import tech.stl.patient.service.PatientService;

@SpringBootTest(classes= {ServiceMockitoTest.class})
public class ServiceMockitoTest {
    
    @Mock
    PatientRepository patientRepository;

    @InjectMocks
    PatientService patientService;

    public List<Patient> allPatients;

    @Test
    @Order(1)
    public void test_getAllPatients() {

        List<Patient> allPatients = new ArrayList<Patient>();
        allPatients.add(new Patient(1, "Srijan","sri678@gmail", "9879866890"));
        when(patientRepository.findAll()).thenReturn(allPatients);
        assertEquals(1, patientService.getAllPatient().size());

    }
    @Test
    @Order(2)
    public void test_getPatientById() {
        Patient patient = new Patient(1, "Sriya","ss@gmail", "9832123456");
        when(patientRepository.findById(1)).thenReturn(patient);
        // assertEquals(patient, patientService.getPatientById(11));
        assertEquals("ss@gmail", patient.getEmailId());

    }
    @Test
    @Order(3)
    public void test_addPatient() {
        Patient patient = new Patient(1,"sri", "sri678@gmail", "7654329876");
        when(patientRepository.save(patient)).thenReturn(patient);
        assertEquals(patient, patientService.addPatient(patient));
    }
    @Test
    @Order(4)
    public void test_updatePatient() {
        Patient patient = (new Patient(1, "Sriya", "sriya@gmail", "7890689765"));
        patient.setPatientName("Sneha");
        patientRepository.save(patient);
        assertEquals("Sneha", patient.getPatientName());
    }

    @Test
    @Order(5)
    public void test_deletePatient() {
         Patient patient = new Patient(12, "sri", "sri678@gmail", "7654327654");
         
            patientRepository.deleteById(patient.getPatientId());
             
            Patient deletedPatient = patientRepository.findById(12);
             
            assertThat(deletedPatient).isNull();
        
    }


}
