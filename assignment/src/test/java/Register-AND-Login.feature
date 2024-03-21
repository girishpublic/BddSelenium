@Ui_Test
Feature: User Registration, Product Purchase and Payment Validation

  Scenario Outline: Register on the website, add product to cart, and validate payment details with different user details
  	Given I read excel file "<fileName>" and sheet "<sheetName>"
    When User is on the landing page
    And User clicks Sign In
    And User select Register and click Continue 
    And User enters personal information and clicks Register
    And User validates correct name and surname on landing screen
    And User adds a product to the cart
    And User proceeds to checkout
    And User continues till payments
    Then User validates product details on payments page

    Examples:
      | fileName     																					| sheetName      			|
      | src/test/resources/testdata/RegisterForm.xlsx   			| UserDetails        	|
