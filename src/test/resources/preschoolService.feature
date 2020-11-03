Feature: Test CRUD methods in Preschool - PreschoolController

  Scenario : Preschools list appear on preschool page from API

    Given the predic application preschool page
    When the user push \"Preschools\" button on preschool page
    And a "GET" request is made to endpoint "http://localhost:8080/preschools"
    Then the response should have status code 200

  Scenario : Add new preschool with submitting form

    Given the predic application add preschool form
    When the user fill the preschool form
    And the user push "New Preschool" button
    And a "POST" request is made to endpoint "http://localhost:8080/preschools"
    Then the response should have status code 200

  Scenario : Edit preschool with preschool edit form

    Given the predic application edit preschool form
    When the user fill the edit preschool form
    And the user push "Edit Preschool" button
    And a "PUT" request is made to endpoint "http://localhost:8080/preschools/preschool_id"
    Then the response should have status code 200

  Scenario : Delete preschool

    Given the predic application preschools page
    And the user push "Delete Preschool" button
    And a "DELETE" request is made to endpoint "http://localhost:8080/preschools/preschools_id"
    Then the response should have status code 200

