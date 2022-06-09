package com.examples.mockitoJUnit;

import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.patient.domain.persistence.PatientRepository;
import com.psycho.psychohelp.patient.service.PatientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class PatientUnitTest {

    @Mock
    Validator validator;

    @Mock
    Patient request;

    @InjectMocks
    PatientServiceImpl patientService;

    @Mock
    PatientRepository patientRepository;

    @Before
    public void setUp() {
        initMocks(this);
    };

    @Test
    public void getAllTest() {
        Mockito.when(patientRepository.findAll()).thenReturn(new ArrayList<>());
        patientService.getAll();
    }

    @Test
    public void createPatientTest() {
        request = new Patient(1L,"jose", "ivan", "strtg@gmail.com", "qweerwrrtyy", "987654321","qweerwr","qweerwr","qweerwr");
        Mockito.when(patientRepository.save(Mockito.any())).thenReturn(new Patient());
        patientService.create(request);
    }

    @Test
    public void getByIdPatientTest() {
        Mockito.when(patientRepository.findById(Mockito.any())).thenReturn(Optional.of(new Patient()));
        patientService.getById(1L);
    }

    @Test
    public void getByEmailPatientTest() {
        Mockito.when(patientRepository.findByEmail(Mockito.any())).thenReturn(new Patient());
        patientService.getByEmail("asdas@sad.com");
    }
}
