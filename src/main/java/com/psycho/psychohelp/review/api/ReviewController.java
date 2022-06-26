package com.psycho.psychohelp.review.api;

import com.psycho.psychohelp.review.domain.service.ReviewService;
import com.psycho.psychohelp.review.mapping.ReviewMapper;
import com.psycho.psychohelp.review.resource.CreateReviewResource;
import com.psycho.psychohelp.review.resource.ReviewResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Review")
@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMapper mapper;

    @GetMapping
    public List<ReviewResource> getAllReview() {
        return mapper.toResource(reviewService.getAll());
    }

    @GetMapping("{reviewId}")
    public ReviewResource getReviewById(@PathVariable Long reviewId)
    {
        return mapper.toResource(reviewService.getById(reviewId));
    }

    @PostMapping("/patient/{patientId}/psychologist/{psychologistId}/appointment/{appointmentId}")
    public ReviewResource createReview(@Valid @RequestBody CreateReviewResource request, @PathVariable(name = "patientId") Long patientId, @PathVariable(name = "psychologistId") Long psychologistId, @PathVariable(name = "appointmentId") Long appointmentId)
    {
        return mapper.toResource(reviewService.create(mapper.toModel(request), patientId, psychologistId, appointmentId));
    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        return reviewService.delete(reviewId);
    }

}
