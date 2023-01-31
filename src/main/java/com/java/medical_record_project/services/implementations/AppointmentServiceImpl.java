package com.java.medical_record_project.services.implementations;

import com.java.medical_record_project.data.dto.AppointmentDTO;
import com.java.medical_record_project.data.dto.CreateAppointmentDTO;
import com.java.medical_record_project.data.dto.UpdateAppointmentDTO;
import com.java.medical_record_project.data.entity.Appointment;
import com.java.medical_record_project.data.entity.Diagnose;
import com.java.medical_record_project.data.entity.Doctor;
import com.java.medical_record_project.data.entity.Patient;
import com.java.medical_record_project.data.repository.AppointmentRepository;
import com.java.medical_record_project.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AppointmentDTO> getAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToAppointmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointment(long id) {
        return modelMapper.map(appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Appointment ID: " + id)), AppointmentDTO.class);
    }

    @Override
    public List<Appointment> findAllByDate(LocalDate date) {
        return appointmentRepository.findAllByDate(date);
    }

    @Override
    public List<Appointment> findAllByDiagnose(Diagnose diagnose) {
        return appointmentRepository.findAllByDiagnose(diagnose);
    }

    @Override
    public List<Appointment> findAllByDoctor(Doctor doctor) {
        return appointmentRepository.findAllByDoctor(doctor);
    }

    @Override
    public List<Appointment> findAllByPatient(Patient patient) {
        return appointmentRepository.findAllByPatient(patient);
    }

    @Override
    public Appointment createAppointment(CreateAppointmentDTO createAppointmentDTO) {
        return appointmentRepository.save(modelMapper.map(createAppointmentDTO, Appointment.class));
    }

    @Override
    public Appointment updateAppointment(long id, UpdateAppointmentDTO updateAppointmentDTO) {
        Appointment appointment = modelMapper.map(updateAppointmentDTO, Appointment.class);
        appointment.setId(id);
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(long id) {
        appointmentRepository.deleteById(id);
    }

    private AppointmentDTO convertToAppointmentDTO(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    //use
    @Override
    public Long countByDiagnose(Diagnose diagnose) {
        return appointmentRepository.countByDiagnose(diagnose);
    }

    //use
    @Override
    public List<Appointment> findByPatientId(long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
}
