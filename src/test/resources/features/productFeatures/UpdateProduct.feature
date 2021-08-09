@UpdateProduct
Feature: The product can be updated
  Scenario: The client calls PUT /products/productId
    When the client calls update product
    Then the product is updated
