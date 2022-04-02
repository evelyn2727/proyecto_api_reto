Feature: REST -Apireto
  As a user...
  I want to...


  @ApiRest
  Scenario: Creates a new auth token
    Given System is ready to send request
    When System sends a request to creates a new auth token
      | username | admin |
      | password | password123 |
    Then The response status should be 200


  @ApiRest
  Scenario: Get list booking1
    Given System is ready to send request
    When System sends a request to get list booking
    Then The response status should be 200

  @ApiRest
  Scenario: Get list booking2
    Given System is ready to send request
    When System sends a request to get list booking
    Then The response status should be 200
    And System should responds with response data
      | bookingid | 5 |
