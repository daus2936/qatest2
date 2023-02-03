package practice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practice.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	//constructor
	public LandingPage(WebDriver driver) {
		//init
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	// WebElement userEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
	
	//PageFactory
	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmail;
	
	@FindBy(xpath="//input[@id='userPassword']")
	WebElement userPass;
	
	@FindBy(xpath="//input[@id='login']")
	WebElement userLogin;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement errorMsg;
	
	// Access Website
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	// Login Method
	public ProductCatalog loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPass.sendKeys(password);
		userLogin.click();
		return new ProductCatalog(driver);
	}
	
	// Get error message
	public String getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
	
	
}
