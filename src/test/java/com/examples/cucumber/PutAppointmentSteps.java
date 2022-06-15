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

    private Long masterId=16L;

    Appointment appointment;
    Long appointmentId = randomLong();

    public Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }

    @Given("I want to reschedule an appointment")
    public void i_want_to_reschedule_an_appointment() {
        String appointmentUrl=url + "/appointment";
        String getEndpoint=restTemplate.getForObject(appointmentUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());
    }

    @And("I reschedule an appointment with meetUrl {string}, motive {string}, history {string}, test {string}, treatment {string} and date {string}")
    public void i_reschedule_an_appointment_with_url_motive_history_test_treatment_and_date(String meetUrl, String motive, String history, String test, String treatment, String date) {
        String appointmentUrl=url + "/appointment/"+masterId;
        String guardado= appointmentUrl;
        Appointment oldAppointment = restTemplate.getForObject(appointmentUrl,Appointment.class);
        Psychologist psychologist = oldAppointment.getPsychologist();
        Patient patient = oldAppointment.getPatient();
        //Psychologist psychologist = restTemplate.getForObject(url+"/psychologists/"+1L,Psychologist.class);
        //Patient patient = restTemplate.getForObject(url+"/patients/"+1L, Patient.class);
        Status status = Status.RESCHEDULED;

        Appointment newAppointment = new Appointment(masterId, meetUrl, motive, history, test, treatment, date, status, patient, psychologist);
        restTemplate.put(appointmentUrl,newAppointment,Appointment.class);
        appointment = restTemplate.getForObject(guardado,Appointment.class);
        log.info(appointment.getId());
        assertNotNull(appointment);
    }

    @Then("I should be able to see my appointment rescheduled")
    public void i_should_be_able_to_see_my_appointment_rescheduled() {
        String appointmentUrl=url + "/appointment/"+masterId;
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
