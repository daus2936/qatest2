package practice.TestsCases;

import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.AssertJUnit;
import java.io.IOException;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import practice.TestComponents.BaseTest;
import practice.pageobjects.CartPage;
import practice.pageobjects.CheckoutPage;
import practice.pageobjects.ConfirmationPage;
import practice.pageobjects.LandingPage;
import practice.pageobjects.ProductCatalog;

public class ErrorValidation extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		// input email and password to login
		landingPage.loginApplication("toni@gmail.com", "Sepeda366");
		AssertJUnit.assertEquals(landingPage.getErrorMsg(), "Incorrect email or password.");

	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		
		ProductCatalog productCatalog = landingPage.loginApplication("roni@gmail.com", "Sepeda36");
		productCatalog.getProductList();
		// Get the product name - ZARA COAT 3
		productCatalog.getProductByName(productName);
		// Click add to cart and Go to cart page 
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		// CartPage cartPage = new CartPage(driver);
		
		// verify and click checkout
		Assert.assertFalse(cartPage.verifyCart("yahaha"));

	}
		
		
	

}
