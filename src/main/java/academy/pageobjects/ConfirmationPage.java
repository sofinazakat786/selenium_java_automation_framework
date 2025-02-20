package academy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	WebDriver driver;

	@FindBy(xpath = "//li//button[@type='button']")
	WebElement checkoutEle;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;

	public String getConfirmationMessage() {
		return confirmationMessage.getText();

	}
}
