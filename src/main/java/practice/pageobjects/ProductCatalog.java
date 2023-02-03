package practice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practice.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
	
	WebDriver driver;
	
	//constructor
	public ProductCatalog(WebDriver driver) {
		//init
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	// List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'mb-3')]"));
	
	//PageFactory
	@FindBy(xpath="//div[contains(@class, 'mb-3')]")
	List<WebElement> products;
	
	By productsElement = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toast = By.xpath("//div[@id='toast-container']");
	By spinner = By.xpath("//ngx-spinner");
	
	//Get a list of product
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsElement);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement getProduct = getProductByName(productName);
		getProduct.findElement(addToCart).click();
		waitForElementToAppear(toast);
		waitForElementToDissappear(spinner);
	}
	
	
}
