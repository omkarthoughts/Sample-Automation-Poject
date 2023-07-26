package common.project;

import java.time.Duration;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		LandingPage landingPage = new LandingPage(driver);
		
		landingPage.loginApplication("omkarpatil@gmail.com","Omkar@123");
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products=productCatalogue.getProductList();
		
		
		
//		for(WebElement product:products)
//		{
//			if(product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
//			{
//				product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//				break;
//			}
//			else
//			{
//				System.out.println("Item Not Found");
//			}
//		}

		
		
		
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("*[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confimMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confimMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	
		}

}
