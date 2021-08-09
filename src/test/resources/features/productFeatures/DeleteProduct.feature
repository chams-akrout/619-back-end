@DeleteProduct
Feature: The product can be deleted
  Scenario: The client calls DELETE /products/productId
    When the client calls delete product
    Then the product is deleted
