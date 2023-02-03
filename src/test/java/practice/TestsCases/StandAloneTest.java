package practice.TestsCases;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import practice.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//implicit wait
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LandingPage landingPage = new LandingPage(driver);
		
		// Access Website
		driver.get("https://rahulshettyacademy.com/client");
		
		// input email and password to login
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("toni@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Sepeda36");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'mb-3')]")));
		
		// Get all the products
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'mb-3')]"));
		
		// Click "Add to Cart"
		WebElement prod = products.stream().filter(product->
		product.findElement(By.xpath("//h5")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.xpath("//button[2]")).click();
		
		// Explicit wait to wait toastcotainer
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ngx-spinner")));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		// Confirm the cart is true
		List<WebElement> confirmCart = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match = confirmCart.stream().anyMatch(cproduct->cproduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		// Click checkout button
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
		List<WebElement> countryOptions =  driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button"));
		
		for(WebElement country:countryOptions) {
			if(country.getText().equalsIgnoreCase("indonesia")) {
				country.click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//a[contains(@class, 'action__submit ng-star-inserted')]")).click();
		
		// Confirm the order is success
		String confirmSuccess = driver.findElement(By.xpath("//h1[contains(text(),'Thankyou for the order.')]")).getText();
		Assert.assertTrue(confirmSuccess.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
