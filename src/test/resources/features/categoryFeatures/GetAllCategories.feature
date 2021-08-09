@GetAllCategories
Feature: the categories can be retrieved
  Scenario: client makes call to GET /categories
    When the client calls get all categories
    Then the client receives a list of categories