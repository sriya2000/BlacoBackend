package tech.stl.patient.jwt;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.stl.patient.entity.Patient;
import tech.stl.patient.repository.PatientRepository;

@RestController
public class UserApi {
@Autowired private PatientRepository service;
    
    @PutMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody @Valid Patient patient) {
        Patient createdUser = service.save(patient);
        URI uri = URI.create("/users/" + createdUser.getPatientId());
        
        UserDto userDto = new UserDto(createdUser.getPatientId(), createdUser.getEmailId());
        
        return ResponseEntity.created(uri).body(userDto);
    }

}
