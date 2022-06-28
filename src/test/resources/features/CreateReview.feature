Feature: Post a Review for Psychologist

  Scenario Outline: As a patient I want to give a review to the psychologist who treated me after an appointment.
    When I want to give a review to the psychologist
    And I post comment <comment>, patientId <patientId>, psychologistId <psychologistId> and appointmentId <appointmentId>
    Then I should be able to send the review

    Examples:
    |               comment               | patientId | psychologistId | appointmentId |
    | "The psychologist really helped me" |     1     |        1       |        1      |