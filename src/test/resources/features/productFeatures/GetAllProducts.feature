@GetAllProducts
Feature: the products can be retrieved
  Scenario: client makes call to GET /products
    When the client calls get all products
    Then the client receives status code of 200
    And the client receives a list of products