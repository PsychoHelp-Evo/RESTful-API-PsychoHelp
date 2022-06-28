package com.psycho.psychohelp.review.domain.service;

import com.psycho.psychohelp.review.domain.model.entity.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    List<Review> getAll();
    Review getById(Long reviewId);
    Review create(Review review, Long psychologistId, Long patientId, Long appointmentId);
    ResponseEntity<?> delete(Long reviewId);
}
