package tech.stl.appointment.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.stl.appointment.Exception.ResourceNotFoundException;
import tech.stl.appointment.entity.Appointment;

import tech.stl.appointment.service.AppointmentService;


@RestController
@CrossOrigin
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointment")
    public ResponseEntity<List<Appointment>> getAllAppointments() {

        List<Appointment> list = appointmentService.getAllAppointment();
        if (list.size() <= 0) {
            throw new ResourceNotFoundException("Appointment List is empty");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @GetMapping("/appointment/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable("id") int appointmentId) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        if (appointment == null) {
            throw new ResourceNotFoundException("Appointment is not present with the id:" + appointmentId);
        }
        return ResponseEntity.of(Optional.of(appointment));
    }

    @GetMapping("/appointment/doctorid/{doctorId}")
    public List<Appointment> getAppointmentByDoctorId(@PathVariable("doctorId") int doctorId) {
        return appointmentService.getAppointmentByDoctorId(doctorId);
    }

    @GetMapping("/appointment/patientid/{patientId}")
    public List<Appointment> getAppointmentByPatientId(@PathVariable("patientId") int patientId) {
        return appointmentService.getAppointmentByPatientId(patientId);
    }

    @PostMapping("/appointment")
    public ResponseEntity<Appointment> addPatient(@Valid @RequestBody Appointment appointment) {
        Appointment b = null;
        try {
            b = this.appointmentService.addAppointment(appointment);
            System.out.println(appointment);
            return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/appointment/{appointmentId}")
    public ResponseEntity<?> deleteDoctor(@Valid @PathVariable("appointmentId") int appointmentId) {
        try {
            this.appointmentService.deleteAppointment(appointmentId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/appointment/{appointmentId}")
    public Appointment updateAppointment(@RequestBody Appointment appointment,
            @PathVariable("appointmentId") int appointmentId) {
        this.appointmentService.updateAppointment(appointment, appointmentId);
        return appointment;
    }
}
