Feature: Register Patient Functionality


  Scenario Outline: As a patient i want to register in psychohelp.
    Given I want to register
    And I enter my own information like firstname <firstname>, lastname <lastname>, email <email>, password <password>, phone <phone>, date <date>, gender <gender> and img <img>
    Then I should be able to see my newly account

    Examples:
      | firstname | lastname | email                  | password   | phone       | date     | gender      | img     |
      | "Luis"    | "Panta"  | "fanoqwerty@gmail.com" | "12345679" | "987425689" | "astrun" | "Masculino" | "sdasd" |