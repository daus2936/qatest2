package practice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dev.failsafe.internal.util.Assert;
import practice.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	
	//constructor
	public OrderPage(WebDriver driver) {
		//init
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	// List<WebElement> confirmOrder = driver.findElements(By.xpath("//table[contains(@class,'table-bordered')]//td[2]"));
	
	//PageFactory
	@FindBy(xpath="//table[contains(@class,'table-bordered')]//td[2]")
	List<WebElement> confirmOrders;

	
	public Boolean verifyOrder(String productName) {
		Boolean match = confirmOrders.stream().anyMatch(cproduct->cproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
}
