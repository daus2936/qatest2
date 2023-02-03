package practice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practice.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	// driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
	
	//PageFactory
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement inputCountry;
	
	@FindBy(xpath="//section[@class='ta-results list-group ng-star-inserted']/button")
	List<WebElement> listCountry;
	
	@FindBy(xpath="//a[contains(@class, 'action__submit ng-star-inserted')]")
	WebElement placeOrder;
	
	By countryWait = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");
	
	public void selectCountry() {
		inputCountry.sendKeys("ind");
		waitForElementToAppear(countryWait);
		List<WebElement> countryOptions =  listCountry;
		for(WebElement country:countryOptions) {
			if(country.getText().equalsIgnoreCase("indonesia")) {
				country.click();
				break;
			}
		}		
	}
	
	public ConfirmationPage placeOrder() {
		placeOrder.click();
		return new ConfirmationPage(driver);
	}
	
	
}
