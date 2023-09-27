Feature: Zipcode api testing

  Scenario: search by postal code
    Given Accept application/json
    And path zipcode is 22031
    When I send a GET request to "/us" endpoint
    Then status code must be 200
    And content type must be "application/json"
    And "Server" header is "cloudflare"
    And "Report-To" header exists
    And body should contains following information
      | post code            | 22031         |
      | country              | United States |
      | country abbreviation | US            |
      | place name           | Fairfax       |
      | state                | Virginia      |
