package com.psycho.psychohelp.review.resource;

import com.psycho.psychohelp.appointment.resource.AppointmentResource;
import com.psycho.psychohelp.patient.resource.PatientResource;
import com.psycho.psychohelp.psychologist.resource.PsychologistResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResource {
    private Long id;
    private String comment;
    private PsychologistResource psychologist;
    private PatientResource patient;
    private AppointmentResource appointment;
}
