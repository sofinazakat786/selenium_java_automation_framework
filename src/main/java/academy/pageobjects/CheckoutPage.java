package academy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(xpath = "//section//button[position()=2]")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		Actions action = new Actions(driver);
		action.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();

	}

	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}

}
