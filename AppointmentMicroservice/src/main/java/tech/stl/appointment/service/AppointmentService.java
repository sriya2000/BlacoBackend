package tech.stl.appointment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.stl.appointment.entity.Appointment;
import tech.stl.appointment.repository.AppointmentRepository;

@Component
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // get all appointment
    public List<Appointment> getAllAppointment() {
        List<Appointment> list = (List<Appointment>) this.appointmentRepository.findAll();
        return list;
    }

    // get appointment by id
    public Appointment getAppointmentById(int appointmentId) {
        Appointment appointment = null;
        appointment = this.appointmentRepository.findById(appointmentId);
        return appointment;
    }

    // add appointment
    public Appointment addAppointment(Appointment appointment) {

        return appointmentRepository.save(appointment);
    }

    // delete appointment
    public void deleteAppointment(int appointmentId) {
        appointmentRepository.deleteById(appointmentId);

    }

    // get appointment by doctorId
    public List<Appointment> getAppointmentByDoctorId(int doctorId) {
        List<Appointment> list1 = (List<Appointment>) this.appointmentRepository.findByDoctorId(doctorId);
        return list1;
    }

    // get appointment by patientId
    public List<Appointment> getAppointmentByPatientId(int patientId) {
        List<Appointment> list1 = (List<Appointment>) this.appointmentRepository.findByPatientId(patientId);
        return list1;
    }

    // delete appointment
    public void updateAppointment(Appointment appointment, int appointmentId) {
        appointment.setAppointmentId(appointmentId);
        appointmentRepository.save(appointment);

    }
}
