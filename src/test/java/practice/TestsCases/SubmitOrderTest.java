package practice.TestsCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import practice.pageobjects.OrderPage;
import practice.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {

	// String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups = {"purchase"})
	public void submitOrder(HashMap<String,String> input) {
		// input email and password to login
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		// ProductCatalog productCatalog = new ProductCatalog(driver);
		
		// Get all the the products
		List<WebElement> products = productCatalog.getProductList();
		// Click add to cart and Go to cart page 
		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCartPage();
		// CartPage cartPage = new CartPage(driver);
		
		// verify and click checkout
		Assert.assertTrue(cartPage.verifyCart(input.get("productName")));
		CheckoutPage checkoutPage = cartPage.goToCartCheckout();
		checkoutPage.selectCountry();
		
		// Go to confirmation page and check "thankyou for the order"
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		AssertJUnit.assertTrue(confirmationPage.confirmSuccess().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		// atau
		/*
		String confirmation = confirmationPage.confirmSuccess();
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		*/
		// driver.close();
	}
	
	// Verify ZARA COAT 3 in the order page
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalog productCatalog = landingPage.loginApplication("toni@gmail.com", "Sepeda36");
		OrderPage orderPage = productCatalog.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrder("ZARA COAT 3"));
		
	}
	
	
	//Extent Reports
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		// HashMap<Object,Object> map = new HashMap<Object,Object>();
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "roni@gmail.com");
		map.put("password", "Sepeda36");
		map.put("productName", "ADIDAS ORIGINAL");
		
		/*
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "toni@gmail.com");
		map1.put("password", "Sepeda36");
		map1.put("productName", "ZARA COAT 3");
		*/
		
		// from json
		//List<HashMap<String, String>> data = GetJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\practice\\data\\PurchaseOrder.json");
		
		return new Object[][] {{map}};
	}
	
	/*
	 public Object[][] getData() {
		
		return new Object[][] {{"toni@gmail.com", "Sepeda36", "ZARA COAT 3"}, {"roni@gmail.com", "Sepeda36", "ADIDAS ORIGINAL"}};
	}
	 */
		
	

}
