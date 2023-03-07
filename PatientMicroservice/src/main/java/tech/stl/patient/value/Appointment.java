package tech.stl.patient.value;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
public class Appointment {
   
    private int appointmentId;
    
    
    private String appointmentSlot;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    private String consultationType;
    
    private String message;
    private int doctorId;
    private int patientId;
    private boolean deleted = Boolean.FALSE;
    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentSlot() {
        return appointmentSlot;
    }
    public void setAppointmentSlot(String appointmentSlot) {
        this.appointmentSlot = appointmentSlot;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getConsultationType() {
        return consultationType;
    }
    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    
        
     public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public Appointment(int appointmentId, String appointmentSlot, Status status,
            String consultationType, String message, int doctorId, int patientId) {
        super();
        this.appointmentId = appointmentId;
//        this.appointmentDate = appointmentDate;
        this.appointmentSlot = appointmentSlot;
        this.status = status;
        this.consultationType = consultationType;
        this.message = message;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }
    public Appointment() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId 
                + ", appointmentSlot=" + appointmentSlot + ", status=" + status + ", consultationType="
                + consultationType + ", message=" + message + ", doctorId=" + doctorId + ", patientId=" + patientId
                + "]";
    }
   
    

}
