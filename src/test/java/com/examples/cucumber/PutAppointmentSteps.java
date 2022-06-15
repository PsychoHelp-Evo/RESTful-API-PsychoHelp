package com.examples.cucumber;


import com.psycho.psychohelp.appointment.domain.model.entity.Appointment;
import com.psycho.psychohelp.appointment.domain.model.entity.Status;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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
public class PutAppointmentSteps {

    private RestTemplate restTemplate = new RestTemplate();
    private String url="http://localhost:8081/api/v1";
    private String message="";
    Appointment appointment;
    Long appointmentId = randomLong();

    public Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }

    @Given("I want to put a appointment")
    public void iWantToPutAAppointment() {
        String appointmentUrl=url + "/appointment";
        String getEndpoint=restTemplate.getForObject(appointmentUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());
    }

    @And("I put a publication with with meetUrl <meetUrl>, motive <motive>, personalHistory <personalHistory>, testRealized <testRealized>, treatment <treatment>, scheduledDate <scheduledDate> and status<status>")
    public void iPutAPublicationWithWithMeetUrlMeetUrlMotiveMotivePersonalHistoryPersonalHistoryTestRealizedTestRealizedTreatmentTreatmentScheduledDateScheduledDateAndStatusStatus(String meetUrl, String motive, String history, String test, String treatment, String date) {
        String appointmentUrl=url + "/appointment/patient/"+1L + "/psychologist/"+1L;
        Psychologist psychologist = restTemplate.getForObject(url+"/psychologists/"+1L,Psychologist.class);
        Patient patient = restTemplate.getForObject(url+"/patients/"+1L, Patient.class);
        Status status = Status.RESCHEDULED;

        Appointment newAppointment = new Appointment(appointmentId, meetUrl, motive, history, test, treatment, date, status, patient,psychologist);
        appointment=restTemplate.patchForObject(appointmentUrl,newAppointment,Appointment.class);
        log.info(appointment.getId());
        assertNotNull(appointment);
    }

    @Then("I should be able to reeschedule my appointment")
    public void iShouldBeAbleToReescheduleMyAppointment() {
        String appointmentUrl=url + "/appointment/"+appointmentId;
        try{
            appointment=restTemplate.getForObject(appointmentUrl,Appointment.class);
            log.info(appointment.getId());
            assertNotNull(appointment);
        }catch (Exception e){
            message=e.getMessage();
            log.info(message);
        }
        assertEquals("",message);
    }
}
