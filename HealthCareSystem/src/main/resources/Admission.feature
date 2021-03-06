#Author: s174406@student.dtu.dk
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Patient Admission
  

  @tag1
  Scenario: Registration of a new patient
    Given Any user of the program and a patient that has not been admitted
    When They enter a new patient's non-medical data
    Then The data is saved in the system and a confirmation is displayed to the user

  @tag2
  Scenario: Bed allocation
    Given A nurse and a patient with no allocated bed
    And a bed that is not in use
    When I assign the patient to the bed
    Then The bed is registered as being in use and can no longer be assigned
    And The departments number of free beds changes


  @tag3
  Scenario: Calling on a patient (Outpatient clinic)
    Given A Doctor and a patient
    When I assign a bed to a patient
    Then The patient occupies a bed in the department
    And the number of free beds on the department is updated
    
  @tag4
  Scenario: Discharging patients
    Given A nurse and a patient that is currently admitted to a deparment
    When I discharge the patient
    Then The patient is no longer admitted to a department
    And the number of free beds in the department in incremented by 1
    
  @tag5
  Scenario: Moving a patient to a new department
    Given A nurse and a patient admited to a department
    And A different department to move them to
    When I dischage the patient from the 1st department
    And Admit them to the 2nd department
    Then The patient is admitted to the 2nd department
    And All their information is still saved

  @tag4
  Scenario: Moving a patient within a department
    Given A nurse and a patient that is currently admitted to a deparment
    And admited to a bed within that department
    When I remove the patient from the 1st bed and assign a new bed to the patient
    Then the old bed becomes free
    And the number of free beds in the department remains the same
    
