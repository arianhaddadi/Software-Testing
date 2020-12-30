@sample_annotation
Feature: Sample Feature

  Background: Owner Existence Precondition
    Given There is a pet owner called "John" "Wick"

  Scenario: Find Owner Scenario
    When We perform find Owner service to find him
    Then He is found successfully

  Scenario: New Pet Scenario
    When We perform new Pet service add a pet for him
    Then A new pet is added successfully for him

  Scenario: Save Pet Scenario
    Given There is a pet called "Doggy"
    When We perform save Pet service on that pet and him
    Then The Pet is saved successfully

  Scenario: Find Pet Scenario
    Given There is a pet called "Doggy"
    When We Set the owner of the pet to be him
    And We save the pet in the pet repository of the pet service
    And We perform find pet service to find the added pet
    Then The Pet is found successfully
