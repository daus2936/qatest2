package practice.TestsCases;

import java.time.Duration;
import java.util.ArrayList;
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

public class StandAloneTest2 {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//implicit wait
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Access Website
		driver.get("https://rahulshettyacademy.com/client");
		
		// input email and password to login
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("toni@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Sepeda36");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'mb-3')]")));
		
		// Get all the products
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']/button[2]"));
		
		// Click all add to cart 
		for (int i=0; i < products.size(); i++) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ngx-spinner")));
			products.get(i).click();
		}
		
		
		
	}

}
