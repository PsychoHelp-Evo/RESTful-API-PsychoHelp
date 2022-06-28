package com.psycho.psychohelp.review.service;

import com.psycho.psychohelp.appointment.domain.model.entity.Appointment;
import com.psycho.psychohelp.appointment.domain.persistance.AppointmentRepository;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.patient.domain.persistence.PatientRepository;
import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import com.psycho.psychohelp.psychologist.domain.persistence.PsychologistRepository;
import com.psycho.psychohelp.review.domain.model.entity.Review;
import com.psycho.psychohelp.review.domain.persistence.ReviewRepository;
import com.psycho.psychohelp.review.domain.service.ReviewService;
import com.psycho.psychohelp.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final static String ENTITY = "Review";

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PsychologistRepository psychologistRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, reviewId));
    }

    @Override
    public Review create(Review review, Long psychologistId, Long patientId, Long appointmentId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient not found with Id " + patientId));
        Psychologist psychologist = psychologistRepository.findById(psychologistId).orElseThrow(() -> new ResourceNotFoundException("Psychologist not found with Id " + psychologistId));
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException("Appointment not found with Id " + appointmentId));

        review.setPatient(patient);
        review.setPsychologist(psychologist);
        review.setAppointment(appointment);
        return reviewRepository.save(review);
    }

    @Override
    public ResponseEntity<?> delete(Long reviewId) {
        return reviewRepository.findById(reviewId).map(review -> {
            reviewRepository.delete(review);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, reviewId));
    }
}
