package academy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import academy.pageobjects.CartPage;
import academy.pageobjects.CheckoutPage;
import academy.pageobjects.ConfirmationPage;
import academy.pageobjects.OrderPage;
import academy.pageobjects.ProductCatalogue;
import academy.testComponents.BaseTest;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	// To Verify productName IS DISPLAYED IN ORDERS PAGE
	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData")
	public void OrderHistoryTest(HashMap<String, String> input) throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(input.get("productName")));

	}

	// Extend Reports
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getjsonDataMap(
				System.getProperty("user.dir") + "/src/test/java/academy/data/PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}

//HashMap<String, String> map1 = new HashMap<>();
//map1.put("email", "nazakat.premium@gmail.com");
//map1.put("password", "Nasrul@1");
//map1.put("productName", "ADIDAS ORIGINAL");
//
//HashMap<String, String> map2 = new HashMap<>();
//map2.put("email", "shetty@gmail.com");
//map2.put("password", "Iamking@000");
//map2.put("productName", "ZARA COAT 3");