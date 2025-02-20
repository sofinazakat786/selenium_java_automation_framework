@Orders
Feature: Purchase the order from Ecommerce Website

  Background: 
    Given I landed on Ecommerce Page

  @SubmitOrder
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with <username> and <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | username                  | password | productName |
      | nazakat.premium@gmail.com | Nasrul@1 | ZARA COAT 3 |

  @jkkf
  Scenario Outline: Validate order history after placing an order
    Given Logged in with <username> and <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then Navigate to order page and validate the order list

    Examples: 
      | username                  | password | productName |
      | nazakat.premium@gmail.com | Nasrul@1 | ZARA COAT 3 |
