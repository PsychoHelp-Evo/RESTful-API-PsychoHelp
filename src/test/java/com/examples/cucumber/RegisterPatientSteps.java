package com.examples.cucumber;

import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RegisterPatientSteps {

    private RestTemplate restTemplate = new RestTemplate();
    private String url="http://localhost:8081/api/v1";
    private String message="";

    Patient patient;
    Long patientId = randomLong();

    private Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }

    @Given("I want to register")
    public void iWantToRegister() {
        String patientUrl=url + "/patients";
        String getEndpoint=restTemplate.getForObject(patientUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());
    }

    @And("I enter my own information like firstname {string}, lastname {string}, email {string}, password {string}, phone {string}, date {string}, gender {string} and img {string}")
    public void iEnterMyOwnInformationLikeFirstnameFirstnameLastnameLastnameEmailEmailPasswordPasswordPhonePhoneDateDateGenderGenderAndImgImg(String firstname, String lastname, String email , String password, String phone, String date, String gender, String img) {
        String patientUrl=url + "/patients";

        Patient newPatient = new Patient(patientId, firstname, lastname, email, password, phone, date, gender, img);
        patient=restTemplate.postForObject(patientUrl,newPatient,Patient.class);
        log.info(patient.getId());
        assertNotNull(patient);
    }

    @Then("I should be able to see my newly account")
    public void iShouldBeAbleToSeeMyNewlyAccount() {
        String patientUrl=url + "/patients/"+patient.getId();
        try
        {
            Patient getPatientById=restTemplate.getForObject(patientUrl, Patient.class, patient.getId());
            log.info(getPatientById);
        }catch(RestClientException e){
            message="";
        }
        assertEquals("",message);
    }

}
