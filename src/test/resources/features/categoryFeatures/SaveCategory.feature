@SaveCategory
Feature: The category can be saved
  Scenario: The client calls POST /categories
    When the client calls save category
    Then  the client receives success http code of 202
    And the category is saved
