package tech.stl.appointment.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.stl.appointment.entity.Appointment;

@Repository
public interface AppointmentRepository  extends JpaRepository<Appointment, Integer> {
    
    public Appointment findById(int appointmentId);

    public List<Appointment> findByDoctorId(int doctorId);

    public List<Appointment> findByPatientId(int patientId);

}