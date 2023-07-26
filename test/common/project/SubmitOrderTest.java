package common.project;

import java.io.IOException;
import java.util.List;
import pageObjects.*;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;

@Listeners(TestComponents.Listeners.class)
public class SubmitOrderTest extends BaseTest {

	@Test
	public void submitOrder() throws IOException {
		String productName = "ZARA COAT 3";
		launchApplication();
		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo();
		landingpage.loginApplication("omkarpatil@gmail.com", "Omkar@123");
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		//List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartPage.goToCheckout();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confimMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confimMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

}
