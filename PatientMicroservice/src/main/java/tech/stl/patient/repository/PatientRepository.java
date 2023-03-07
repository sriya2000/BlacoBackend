package tech.stl.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.stl.patient.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    
    
    public Patient findById(int patientId);
    public Patient findByEmailId(String emailId);
    public Patient findBypatientId(int patientId);

}
