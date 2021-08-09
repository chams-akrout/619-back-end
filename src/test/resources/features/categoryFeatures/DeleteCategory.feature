@DeleteCategory
Feature: The category can be deleted
  Scenario: The client calls DELETE /categories/categoryId
    When the client calls delete category
    Then the category is deleted
