#Author: Kun Zhu
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
Feature: create a profile

  @tag1
  Scenario: Creating a new patient profile
    Given A new patient
    And a User
    When The User registers a patient with their non medical data
    Then A new patient is created in the HMS

  @tag2
  Scenario: verify patients profile data
    Given A newly registered patient and a User
    When A User requests their non-medical data 
    Then The correct data is shown

  @tag3
  Scenario: Creating a new user profile
    Given A new user
    And An Admin
    When The Admin creates a new User
    Then A new user is created in the HMS

  @tag2
  Scenario: verify user profile data
    Given A newly registered User
    When I request their data 
    Then The correct data is shown
    
    