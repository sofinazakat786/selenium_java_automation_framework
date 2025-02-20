package academy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import academy.pageobjects.CartPage;
import academy.pageobjects.ProductCatalogue;
import academy.testComponents.BaseTest;
import academy.testComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws InterruptedException {

		landingPage.loginApplication("nazakat.premium@gmail.com", "khjkhk");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test(groups = { "ErrorHandling" })
	public void ProductErrorValidation() throws InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		String username = "nazakat.premium@gmail.com";
		String password = "Nasrul@1";

		ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ORIGINAL");
		Assert.assertFalse(match);

	}

}