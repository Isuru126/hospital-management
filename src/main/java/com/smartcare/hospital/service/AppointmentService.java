package com.smartcare.hospital.service;

import com.smartcare.hospital.entity.Appointment;
import java.util.List;

public interface AppointmentService {
    Appointment bookAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    Appointment updateAppointment(Long id, Appointment appointment);
    Appointment cancelAppointment(Long id);
}