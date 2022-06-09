package com.examples.mockitoJUnit;

import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import com.psycho.psychohelp.psychologist.domain.persistence.PsychologistRepository;
import com.psycho.psychohelp.psychologist.service.PsychologistServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class PsychoUnitTest {

    @Mock
    Psychologist request;

    @InjectMocks
    PsychologistServiceImpl psychologistService;

    @Mock
    PsychologistRepository psychologistRepository;

    @Before
    public void setUp() {
        initMocks(this);
    };

    @Test
    public void getAllPsychoTest() {
        Mockito.when(psychologistRepository.findAll()).thenReturn(new ArrayList<>());
        psychologistService.getAll();
    }

    @Test
    public void createPsychoTest() {
        Date birthdayDate = new Date();
        request = new Psychologist(1L, "Juan", "12345678", birthdayDate, "juanadmin@gmail.com", "asdqwefsdv", "987123456", "saasdasd", "saasdasd", "saasdasd", "saasdasd", "saasdasd", "saasdasd", "saasdas", true, true );
        Mockito.when(psychologistRepository.save(Mockito.any())).thenReturn(new Psychologist());
        psychologistService.create(request);
    }

    @Test
    public void getByIdPsychoTest() {
        Mockito.when(psychologistRepository.findById(Mockito.any())).thenReturn(Optional.of(new Psychologist()));
        psychologistService.getById(1L);
    }

    @Test
    public void getByEmailPsychoTest() {
        Mockito.when(psychologistRepository.findByEmail(Mockito.any())).thenReturn(new Psychologist());
        psychologistService.getByEmail("string@gmail.com");
    }


}
