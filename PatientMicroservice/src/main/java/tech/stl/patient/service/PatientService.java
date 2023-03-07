package tech.stl.patient.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import tech.stl.patient.constant.ConstantEnum;
import tech.stl.patient.entity.Patient;
import tech.stl.patient.repository.PatientRepository;
import tech.stl.patient.value.Appointment;

@Component
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RestTemplate restTemplate;

    // get all patient
    public List<Patient> getAllPatient() {

        return this.patientRepository.findAll();
    }

    // get patient by id
    public Patient getPatientById(int patientId) {
        return this.patientRepository.findById(patientId);

    }

    // add patient
    public Patient addPatient(Patient patient) {

        return patientRepository.save(patient);
    }

    // delete patient
    public void deletePatient(int patientId) {
        patientRepository.deleteById(patientId);

    }

    // update patient
    public void updatePatient(Patient patient, int patientId) {
        patient.setPatientId(patientId);
        patientRepository.saveAndFlush(patient);

    }

    // book appointment
    public ResponseEntity<Appointment> bookappointment(Appointment appointment) {
        return restTemplate.postForEntity(ConstantEnum.BOOK_APPOINTMENTS.getValue(), appointment, Appointment.class);

    }

}