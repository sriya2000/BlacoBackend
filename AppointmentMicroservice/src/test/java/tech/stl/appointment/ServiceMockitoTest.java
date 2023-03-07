package tech.stl.appointment;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import tech.stl.appointment.entity.Appointment;
import tech.stl.appointment.entity.Status;
import tech.stl.appointment.repository.AppointmentRepository;
import tech.stl.appointment.service.AppointmentService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes= {ServiceMockitoTest.class})
public class ServiceMockitoTest {
    
    private static final Status ACTIVE = null;

    @Mock
    AppointmentRepository appointmentRepository;
    
    @InjectMocks
    AppointmentService appointmentService;
    
    public List<Appointment> appointment;
    @Test
    @Order(1)
    public void   test_getAllAppointment(){
        
        List<Appointment> appointment=new ArrayList<Appointment>(); 
        appointment.add(new Appointment(1,"12am",ACTIVE,"Online","b",true,1,1));
        when(appointmentRepository.findAll()).thenReturn(appointment); //mocking
        assertEquals(1,appointmentService.getAllAppointment().size());
        }
    @Test
    @Order(2)
    public void test_addAppointment() {
        Appointment appointment=new Appointment(1,"12am",ACTIVE,"Online","b",true,1,1);
         when(appointmentRepository.save(appointment)).thenReturn(appointment);
         assertEquals(appointment, appointmentService.addAppointment(appointment));
    }
    @Test
    @Order(3)
    public void  test_getAppointmentById() {
        Appointment appointment=new Appointment(1,"12am",ACTIVE,"Online","b",true,1,1);
        when(appointmentRepository.findById(1)).thenReturn(appointment);
        assertEquals(appointment, appointmentService.getAppointmentById(1));
        
    }
    @Test
    @Order(4)
    public void test_deleteAppointment() {
        Appointment appointment = new Appointment(1,"12am",ACTIVE,"Online","b",true,1,1);
        appointmentRepository.deleteById(appointment.getAppointmentId());
         Appointment deletedAppointment = appointmentRepository.findById(1);
         assertThat(deletedAppointment).isNull();       
    }
    @Test
    @Order(5)
    public void test_updateAppointment() {
        Appointment appointment=new Appointment(1,"12am",ACTIVE,"Online","b",true,1,1);
        appointment.setConsultationType("physical");;
        appointmentRepository.save(appointment);
        assertEquals("physical",appointment.getConsultationType());
        
    }
    @Test
    @Order(6)
    public void test_getAppointmentByDoctorId() {
        List<Appointment> appointment=new ArrayList<Appointment>(); 
        appointment.add(new Appointment(1,"12am",ACTIVE,"Online","b",true,1,1));      
        when(appointmentRepository.findByDoctorId(1)).thenReturn(appointment);
        assertEquals(appointment, appointmentService.getAppointmentByDoctorId(1));
        
    }
    @Test
    @Order(7)
    public void test_getAppointmentByPatientId() {
        List<Appointment> appointment=new ArrayList<Appointment>(); 
        appointment.add(new Appointment(1,"12am",ACTIVE,"Online","b",true,1,1));      
        when(appointmentRepository.findByPatientId(1)).thenReturn(appointment);
        assertEquals(appointment, appointmentService.getAppointmentByPatientId(1));
        
    }
    

}
