package tech.stl.patient.entity;

public class JwtResponse {
    private Patient patient;
    private String jwtToken;

    public JwtResponse(Patient patient, String jwtToken) {
        this.patient = patient;
        this.jwtToken = jwtToken;
    }

    public Patient getUser() {
        return patient;
    }

    public void setUser(Patient patient) {
        this.patient = patient;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
