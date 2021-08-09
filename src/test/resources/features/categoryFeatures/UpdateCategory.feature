@UpdateCategory
Feature: The category can be updated
  Scenario: The client calls PUT /categories/categoryId
    When the client calls update category
    Then the category is updated
