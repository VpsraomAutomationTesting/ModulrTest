package StepDefinitions;

import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class ForgottenPasswordSteps {


	WebDriver driver = null;
	private String baseUrl;

	private StringBuffer verificationErrors = new StringBuffer();

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		String projectPath = System.getProperty("user.dir");
		baseUrl = "https://secure-sandbox.modulrfinance.com/";
		System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Given("User is provided with Forgotten password Link")
	public void user_is_provided_with_forgotten_password_link() throws InterruptedException {
		driver.navigate().to(baseUrl);
		boolean butnEnabled = driver.findElement(By.id("forgotPasswordHref")).isEnabled();
		Assert.assertTrue(butnEnabled);
		Thread.sleep(5000);
	}

	@When("Clicks on the link")
	public void clicks_on_the_link() throws InterruptedException {
		driver.findElement(By.id("forgotPasswordHref")).click();
		Thread.sleep(2000);
	}

	@Then("Navigates to reset password page")
	public void navigates_to_reset_password_page() {
		String restURL ="https://secure-sandbox.modulrfinance.com/resetaccess";
		String actrestURL =		driver.getCurrentUrl();
		Assert.assertEquals(restURL, actrestURL);
		System.out.println("This page is :"+actrestURL);
	}

	@Then("Entering username")
	public void entering_username() throws InterruptedException {
		driver.findElement(By.id("usernameInput")).clear();
		driver.findElement(By.id("usernameInput")).sendKeys("venkata.maddula7");
		Thread.sleep(5000);
	}

	@Then("Clicks on Request a reset button")
	public void clicks_on_request_a_reset_button() throws InterruptedException {
		  driver.findElement(By.id("signInSubmitButton")).click();
		Thread.sleep(5000);
	}

	@Then("Navigates to Email Sent page")
	public void navigates_to_email_sent_page() {
		 boolean finalSCRN = driver.findElement(By.id("emailSentHeading")).isDisplayed();
		 Assert.assertTrue(finalSCRN);
		 System.out.println("Will the email sent ?"+ finalSCRN);
		 
     driver.getPageSource().contains("Check your email for instructions");
         
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@SuppressWarnings("unused")
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
