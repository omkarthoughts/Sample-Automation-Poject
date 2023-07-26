package common.project;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;

@Listeners(TestComponents.Listeners.class)
public class ErrorValidationTest extends BaseTest {

	@Test(priority=0)
	public void LoginErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		launchApplication();
		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo();
		landingpage.loginApplication("omkarpatil@gmail.com", "WrongPass@123");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}

	@Test(priority=1)
	public void submitOrder() throws IOException {
		String productName = "ZARA COAT 3";
		launchApplication();
		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo();
		landingpage.loginApplication("omkarpatil@gmail.com", "Omkar@123");
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		// List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
