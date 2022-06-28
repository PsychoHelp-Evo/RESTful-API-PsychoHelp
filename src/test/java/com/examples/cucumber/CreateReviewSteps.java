package com.examples.cucumber;

import com.psycho.psychohelp.appointment.domain.model.entity.Appointment;
import com.psycho.psychohelp.appointment.domain.model.entity.Status;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import com.psycho.psychohelp.review.domain.model.entity.Review;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class CreateReviewSteps {
    private RestTemplate restTemplate = new RestTemplate();
    private String url="http://localhost:8081/api/v1";
    private String message="";
    Review review;
    Long reviewId = randomLong();

    public Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }

    @When("I want to give a review to the psychologist")
    public void iWantToGiveAReviewToThePsychologist() {
        String reviewUrl=url + "/reviews";
        String getEndpoint=restTemplate.getForObject(reviewUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());

    }

    @And("I post comment {string}, patientId {long}, psychologistId {long} and appointmentId {long}")
    public void iPostCommentCommentPatientIdPatientIdPsychologistIdPsychologistIdAndAppointmentIdAppointmentId(String comment, Long patientId, Long psychologistId, Long appointmentId) {
        String appointmentUrl=url + "/appointment/" + appointmentId;
        String reviewUrl=url + "/reviews/patient/" + patientId + "/psychologist/" + psychologistId + "/appointment/" + appointmentId;

        // Validate if the appointment exists
        Appointment appointment = restTemplate.getForObject(appointmentUrl,Appointment.class);

        if(appointment != null) {
            // Validate if the appointment is completed
            if (appointment.getStatus() == Status.COMPLETED) {
                Patient patient = appointment.getPatient();
                Psychologist psychologist = appointment.getPsychologist();
                // Create a review
                Review newReview = new Review(reviewId, comment, patient, psychologist, appointment);
                review = restTemplate.postForObject(reviewUrl, newReview, Review.class);
                log.info(review.getId());
                assertNotNull(review);
            }
            else{
                log.info("Appointment is not completed you cannot give a review");
                assertTrue(false);
            }
        }else{
            log.info("Appointment is not found");
            assertTrue(false);
        }
    }

    @Then("I should be able to send the review")
    public void iShouldBeAbleToSendTheReview() {
        String reviewUrl=url + "/reviews/" + review.getId();
        try{
            restTemplate.getForObject(reviewUrl, Review.class);
            log.info("Review is sent");
            assertTrue(true);
        }catch (Exception e){
            log.info("Review is not sent");
            assertTrue(false);
        }
    }
}
