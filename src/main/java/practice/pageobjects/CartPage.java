package practice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dev.failsafe.internal.util.Assert;
import practice.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	//constructor
	public CartPage(WebDriver driver) {
		//init
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	// List<WebElement> confirmCart = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	
	//PageFactory
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> confirmCarts;
	
	By productsElement = By.xpath("//div[contains(@class, 'mb-3')]");
	By addToCart = By.xpath("//button[2]");
	By toast = By.xpath("//div[@id='toast-container']");
	By spinner = By.xpath("//ngx-spinner");
	
	public Boolean verifyCart(String productName) {
		Boolean match = confirmCarts.stream().anyMatch(cproduct->cproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
}
