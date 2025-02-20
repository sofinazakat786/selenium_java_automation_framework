package academy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import academy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {
	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LandingPage landingPage = new LandingPage(driver);
		//Landing Start
//		driver.get("https://rahulshettyacademy.com/client/");
		landingPage.goTo();

//		driver.findElement(By.id("userEmail")).sendKeys("nazakat.premium@gmail.com");
//		driver.findElement(By.id("userPassword")).sendKeys("Nasrul@1");
//		driver.findElement(By.xpath("//input[@type='submit']")).click();
		landingPage.loginApplication("nazakat.premium@gmail.com", "Nasrul@1");
		//Landing end
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		driver.findElement(By.xpath("//li//button[@type='button']")).click();

		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build()
				.perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("//section//button[position()=2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();

	}

}
