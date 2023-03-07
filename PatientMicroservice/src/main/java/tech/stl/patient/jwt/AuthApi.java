package tech.stl.patient.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.stl.patient.entity.Patient;
import tech.stl.patient.repository.PatientRepository;
import tech.stl.patient.util.JwtTokenUtil;

@RestController
    public class AuthApi {
        @Autowired AuthenticationManager authManager;
        @Autowired JwtTokenUtil jwtUtil;
        
        @PostMapping("/auth/login")
        public ResponseEntity<?> login(@RequestBody AuthRequest request) {
            try {
                Authentication authentication = authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(), request.getPassword())
                );
                
                Patient user = (Patient) authentication.getPrincipal();
                String accessToken = jwtUtil.generateAccessToken(user);
                AuthResponse response = new AuthResponse(user.getEmailId(), accessToken);
                
                return ResponseEntity.ok().body(response);
                
            } catch (BadCredentialsException ex) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
        
        
        @Autowired PatientRepository patientRepo;
        @Autowired PasswordEncoder passwordEncoder;
        
        @PostMapping("/auth/signin/patient")
        public Patient signin(@RequestBody Patient patient) {
            //patient.addRole(new Role(1,"Patient"));
            String rawPassword = patient.getPassword();
            String encodedPassword = passwordEncoder.encode(rawPassword);
            patient.setPassword(encodedPassword);
            patientRepo.save(patient);
            return patient;
        }

}
