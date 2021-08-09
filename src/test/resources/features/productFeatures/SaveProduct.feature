@SaveProduct
Feature: The product can be saved
  Scenario: The client calls POST /products
    When the client calls save product
    Then  the client receives http status code of 202
    And the product is saved
