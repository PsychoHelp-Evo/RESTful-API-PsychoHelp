Feature: Put a Appointment Functionality


  Scenario Outline: As a patient i want to put an appointment in psychohelp
    Given I want to put a appointment
    And I put a publication with with meetUrl <meetUrl>, motive <motive>, personalHistory <personalHistory>, testRealized <testRealized>, treatment <treatment> and scheduledDate <scheduledDate>
    Then I should be able to reeschedule my appointment

    Examples:
      | meetUrl        | motive              | personalHistory   | testRealized       | treatment | scheduledDate  |
      | "meet.com"     | "Appointment PUT"   | "None"            | "test.com"         |  "Pills"  |   "15/05/22"   |