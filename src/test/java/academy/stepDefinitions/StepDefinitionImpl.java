package academy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import academy.pageobjects.CartPage;
import academy.pageobjects.CheckoutPage;
import academy.pageobjects.ConfirmationPage;
import academy.pageobjects.LandingPage;
import academy.pageobjects.OrderPage;
import academy.pageobjects.ProductCatalogue;
import academy.testComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CheckoutPage checkoutPage;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	public OrderPage ordersPage;

	@Given("I landed on Ecommerce Page")
	public void I_Landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();

	}

	@Given("^Logged in with (.+) and (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);

	}

	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName) {

		cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();

	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void verify_confirmation_message(String string) {

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void error_message_is_displayed(String expectedErrorMessage) {
		Assert.assertEquals(expectedErrorMessage, landingPage.getErrorMessage());
		driver.close();
	}

	@Then("I validated {string} in present cart page")
	public void validated_wrong_product_in_cart(String string) {

		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(string);
		Assert.assertFalse(match);
		driver.close();

	}

	@Then("Navigate to order page and validate the order list")
	public void navigate_to_order_page_and_validate_the_order_list(String productName) {
	    ordersPage = productCatalogue.goToOrdersPage();
	    Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	    driver.close();
	}

}
