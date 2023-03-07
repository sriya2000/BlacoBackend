package tech.stl.patient.controller;

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

import tech.stl.patient.Exception.ResourceNotFoundException;
import tech.stl.patient.entity.Patient;

import tech.stl.patient.service.PatientService;
import tech.stl.patient.value.Appointment;

@RestController
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getPatient()  {
        List<Patient> list = patientService.getAllPatient();
        if (list.size() <= 0) {
            throw new ResourceNotFoundException("Patient List is empty");

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @GetMapping("/patients/pId/{pid}")
    public ResponseEntity<Patient> getPatient(@PathVariable("pid") int patientId) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient is not present with the id:"+patientId);
        }
        return ResponseEntity.of(Optional.of(patient));
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
        Patient b = null;
        try {
            b = this.patientService.addPatient(patient);
            System.out.println(patient);
            return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/patients/deletebyId/{patientid}")   
    public ResponseEntity<Patient> deletePatient(@Valid @PathVariable("patientid") int patientId) {
        try {
            this.patientService.deletePatient(patientId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ResourceNotFoundException("Patient is not present with the id:"+patientId);
    }

    @PutMapping("/patients/updateById/{patientId}")
    public ResponseEntity<Patient> updatePatient(@Valid @RequestBody Patient patient,
            @PathVariable("patientId") int patientId) {
        try {
            this.patientService.updatePatient(patient, patientId);
            return ResponseEntity.ok().body(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //---------book appointment
    @PostMapping("/bookAppointment")
    public ResponseEntity<Appointment> addApointment(@Valid @RequestBody Appointment appointment){
        return this.patientService.bookappointment(appointment);
    }
   
}