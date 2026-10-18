package practiceWebsite3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DemoWebShop12 {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--guest");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ico-register"))).click();
		//-------Register Page-------

		WebElement gender_Button = wait.until(ExpectedConditions.elementToBeClickable(By.id("gender-male")));
		gender_Button.click();
		driver.findElement(By.id("FirstName")).sendKeys("Test_12");
		driver.findElement(By.name("LastName")).sendKeys("demo_14");
		String email = "automation" + System.currentTimeMillis() + "@gmail.com";  //to generate unique email id everytime you run new test
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys("Jetfire@12");
		driver.findElement(By.name("ConfirmPassword")).sendKeys("Jetfire@12");
		driver.findElement(By.id("register-button")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".register-continue-button"))).click();

		//--------Product Category Page-------
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='top-menu']//a[normalize-space()='Electronics']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='Show products in category Cell phones']"))).click();

		//-------Product Selection Page------
		WebElement sortBy_dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='products-orderby']")));
		Select select = new Select(sortBy_dropdown);
		select.selectByVisibleText("Price: Low to High");

		WebElement viewAs_dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='products-viewmode']")));
		Select view_As = new Select(viewAs_dropdown);
		view_As.selectByVisibleText("Grid");

		//-------Product Page-------
		driver.findElement(By.xpath("//a[normalize-space()='Smartphone']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='add-to-cart-button-43']"))).click();
		WebElement message = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='content']")));
		System.out.println(message.getText());
		String actualMessage = message.getText();
		Assert.assertEquals(actualMessage,"The product has been added to your shopping cart");
		System.out.println("Assertion passed successfully");
		driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();

		//------Shopping Cart Page------
		WebElement countryAs_dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='CountryId']")));
		Select country_As = new Select(countryAs_dropdown);
		country_As.selectByVisibleText("United States");

		WebElement StateAs_dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='StateProvinceId']")));
		Select state_As = new Select(StateAs_dropdown);
		state_As.selectByVisibleText("Other (Non US)");

		driver.findElement(By.id("ZipPostalCode")).sendKeys("759842");
		driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
		driver.findElement(By.cssSelector(".checkout-button")).click();

		//------CheckOut Page------
		WebElement Company = wait.until(ExpectedConditions.elementToBeClickable(By.id("BillingNewAddress_Company")));
		Company.sendKeys("Acceya");
		WebElement Country_dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='BillingNewAddress_CountryId']")));
		Select country_Select = new Select(Country_dropdown); 
		country_Select.selectByVisibleText("United States");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='BillingNewAddress_StateProvinceId']/option[normalize-space()='AA (Armed Forces Americas)']")));
		WebElement stateProvinceDropdown = driver.findElement(By.id("BillingNewAddress_StateProvinceId"));
		Select stateSelect = new Select(stateProvinceDropdown);
		stateSelect.selectByVisibleText("AA (Armed Forces Americas)");
		driver.findElement(By.cssSelector("#BillingNewAddress_City")).sendKeys("APO");
		driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("A-305 new Place");
		driver.findElement(By.id("BillingNewAddress_Address2")).sendKeys("New Street");
		driver.findElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']")).sendKeys("756124");
		driver.findElement(By.cssSelector("#BillingNewAddress_PhoneNumber")).sendKeys("7894561238");
		driver.findElement(By.name("BillingNewAddress.FaxNumber")).sendKeys("(218) 555-0143");
		WebElement billing_Address = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='Billing.save()']")));
		billing_Address.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='Shipping.save()']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='shippingoption_2']"))).click(); //Shipping method selection
		driver.findElement(By.cssSelector(".shipping-method-next-step-button")).click();
		
		//Payment Method #Visa
		wait.until(ExpectedConditions.elementToBeClickable(By.id("paymentmethod_2"))).click();
		driver.findElement(By.cssSelector(".payment-method-next-step-button")).click();
		
		//Payment Information Details
		wait.until(ExpectedConditions.elementToBeClickable(By.id("CreditCardType")));
		WebElement creditcard_dropdown = driver.findElement(By.id("CreditCardType"));
		Select card_Select = new Select(creditcard_dropdown);
		card_Select.selectByVisibleText("Visa");
		driver.findElement(By.name("CardholderName")).sendKeys("Kanha C");
		driver.findElement(By.id("CardNumber")).sendKeys("4242424242424242");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ExpireMonth")));
		WebElement expiration_date = driver.findElement(By.name("ExpireMonth"));
		Select expiry_month = new Select(expiration_date);
		expiry_month.selectByVisibleText("04");
		Select expiry_year = new Select(driver.findElement(By.id("ExpireYear")));
		expiry_year.selectByVisibleText("2029");
		driver.findElement(By.name("CardCode")).sendKeys("123");
		driver.findElement(By.cssSelector(".payment-info-next-step-button")).click();
		
		//Confirm Order Page
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".confirm-order-next-step-button"))).click();
		
		//Thank You Page
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']")));
		WebElement successful_Message = driver.findElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"));
		System.out.println(successful_Message.getText());
		String actualMessage1 = successful_Message.getText();
		String expectedMessage ="Your order has been successfully processed!";
		Assert.assertEquals(actualMessage1,expectedMessage);
		System.out.println("Assertion Passed successfully");
		driver.findElement(By.linkText("Click here for order details.")).click();
		
		//Order Information Page
		driver.findElement(By.linkText("PDF Invoice")).click();
		
		//Getting back to Previous Page
		driver.navigate().back();
		
		//Once Again on Thank you Page
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Continue']"))).click();
		
		//Logging Out
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log out"))).click();

	}
}


