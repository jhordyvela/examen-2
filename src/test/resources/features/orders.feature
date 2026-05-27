Feature: Product API
  Scenario: Create a new product via REST
    Given the product API is up
    When I send a POST request to "/api/orders" with name "Teclado" and price 50.0
    Then the response status should be 201