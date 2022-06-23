Feature: Put an Appointment Functionality


  Scenario Outline: As a patient i want to reschedule an appointment in psychohelp .
    Given I want to reschedule an appointment
    And I reschedule an appointment with meetUrl <meetUrl>, motive <motive>, history <history>, test <test>, treatment <treatment> and date <date>
    Then I should be able to see my appointment rescheduled

    Examples:
      | meetUrl    | motive              | history   | test       | treatment | date     |
      | "meet.com" | "Appointment 2"     | "None"    | "test.com" | "Pills"   | "Today"  |

  Scenario Outline: As a patient i want to reschedule an appointment for a second time in psychohelp .
    Given I want to reschedule an appointment for a second time
    And I reschedule the same appointment with meetUrl <meetUrl>, motive <motive>, history <history>, test <test>, treatment <treatment> and date <date>
    Then I shouldn't be able to see my appointment rescheduled

    Examples:
      | meetUrl    | motive              | history   | test       | treatment | date     |
      | "meet.com" | "Appointment 2"     | "None"    | "test.com" | "Pills"   | "Today"  |

