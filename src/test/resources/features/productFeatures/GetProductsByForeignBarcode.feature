@GetProductsByForeignBarcode
Feature: The local products can be retrieved by foreign barcode
  Scenario: The client calls POST /products/foreignBarcode
    When the client calls get local products by foreign barcode
    Then the client receives a list of local products
  Scenario: The client calls POST /products/stringForeignBarcode
    When the client calls get local products by foreign barcode by long value
    Then the client receives a list of local products
