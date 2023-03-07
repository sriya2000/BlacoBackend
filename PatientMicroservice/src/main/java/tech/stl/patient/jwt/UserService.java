package tech.stl.patient.jwt;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tech.stl.patient.entity.Patient;
import tech.stl.patient.repository.PatientRepository;

@Service
    @Transactional
    public class UserService {
        @Autowired private PatientRepository repo;
        @Autowired private PasswordEncoder passwordEncoder;
        
        public Patient save(Patient patient) {
            String rawPassword = patient.getPassword();
            String encodedPassword = passwordEncoder.encode(rawPassword);
            patient.setPassword(encodedPassword);
            
            return repo.save(patient);
        }
}