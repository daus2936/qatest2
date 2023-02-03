package practice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practice.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// String confirmSuccess = driver.findElement(By.xpath("//h1[contains(text(),'Thankyou for the order.')]")).getText();
	
	@FindBy(xpath="//h1[contains(text(),'Thankyou for the order.')]")
	WebElement confirmText;
	
	public String confirmSuccess() {
		return confirmText.getText();
	}
}
