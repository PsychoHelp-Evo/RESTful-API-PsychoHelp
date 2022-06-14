package com.psycho.psychohelp.psychologist.service;

import com.psycho.psychohelp.appointment.domain.model.entity.Appointment;
import com.psycho.psychohelp.appointment.domain.persistance.AppointmentRepository;
import com.psycho.psychohelp.appointment.service.AppointmentServiceImpl;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.patient.domain.persistence.PatientRepository;
import com.psycho.psychohelp.patient.service.PatientServiceImpl;
import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import com.psycho.psychohelp.psychologist.domain.persistence.PsychologistRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.validation.Validator;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class PsychologistServiceImplTest {

    @Mock
    Validator validator;

    @Mock
    Psychologist request;

    @InjectMocks
    PsychologistServiceImpl psychologistService;

    @Mock
    PsychologistRepository psychologistRepository;

    @Before
    public void setUp() {
        Date date = new Date(1980, 12, 3);
        request = new Psychologist(2L, "name", "1234354", date, "email@gmail.com", "password123", "9823892", "specialization", "formation", "about", "male", "sessionType", "image", "cmp", true, false);
    }

    @Test
    public void getAll() {
        Mockito.when(psychologistRepository.findAll()).thenReturn(Arrays.asList(request));
        assertNotNull(psychologistService.getAll());
    }

    @Test
    public void create() {
        Mockito.when(psychologistRepository.save(Mockito.any(Psychologist.class))).thenReturn(request);
        assertNotNull(psychologistService.create(new Psychologist()));
    }

    @Test
    public void getById() {
        Mockito.when(psychologistRepository.findById(2L)).thenReturn(Optional.of(new Psychologist()));
        assertNotNull(psychologistService.getById(2L));
    }

    @Test
    public void getByEmail() {
        Mockito.when(psychologistRepository.findByEmail("email@gmail.com")).thenReturn(new Psychologist());
        assertNotNull(psychologistService.getByEmail("email@gmail.com"));
    }

    @Test
    public void getByGenre() {
        Mockito.when(psychologistRepository.findByGenre("male")).thenReturn(Arrays.asList(request));
        assertNotNull(psychologistService.getByGenre("male"));
    }

    @Test
    public void getBySessionType() {
        Mockito.when(psychologistRepository.findBySessionType("sessionType")).thenReturn(Arrays.asList(request));
        assertNotNull(psychologistService.getBySessionType("sessionType"));
    }

    @Test
    public void getByGenreAndSessionType() {
        Mockito.when(psychologistRepository.findByGenreAndSessionType("male","sessionType")).thenReturn(Arrays.asList(request));
        assertNotNull(psychologistService.getByGenreAndSessionType("male","sessionType"));
    }

    @Test
    public void getByName() {
        Mockito.when(psychologistRepository.findByName("name")).thenReturn(new Psychologist());
        assertNotNull(psychologistService.getByName("name"));
    }

    ///Este test no esta funcionando por q no encuentra le Id del psicologo
    @Test
    public void update() {
        Mockito.when(psychologistRepository.save(Mockito.any(Psychologist.class))).thenReturn(request);
        assertNotNull(psychologistService.update(2L, new Psychologist()));
    }

    @Test
    public void delete() {
        Mockito.when(psychologistRepository.findById(2L)).thenReturn(Optional.of(new Psychologist()));
        assertNotNull(psychologistService.delete(2L));
    }
}