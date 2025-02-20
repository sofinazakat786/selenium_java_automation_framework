@ErrorValidation
Feature: Error Validation

  Background: 
    Given I landed on Ecommerce Page

  @tag2
  Scenario Outline: Validate Error message on wrong credentials
    Given Logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username                  | password  |
      | nazakat.premium@gmail.com | incorrect |

  @tag3
  Scenario Outline: Validate Wrong product inside cart page
    Given Logged in with <username> and <password>
    When I add product <productName> to cart
    Then I validated "ORIGINAL" in present cart page

    Examples: 
      | username                  | password | productName     |
      | nazakat.premium@gmail.com | Nasrul@1 | ADIDAS ORIGINAL |
