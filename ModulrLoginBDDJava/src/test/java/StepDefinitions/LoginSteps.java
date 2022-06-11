package StepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class LoginSteps {

	WebDriver driver = null;
	private String baseUrl;
	// private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@SuppressWarnings("deprecation")
	@Before

	public void setUp() throws Exception {
		String projectPath = System.getProperty("user.dir");
		baseUrl = "https://secure-sandbox.modulrfinance.com/";
		System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@Given("User is on login page")
	public void user_is_on_login_page() throws InterruptedException {
		// Navigate to login page
		driver.navigate().to(baseUrl);

		Thread.sleep(5000);
	}

	@When("^user enters (.*) and (.*)$")
	public void user_enters_username_and_password(String username, String password) throws InterruptedException {

			
		String decodedStringUsr= new String (Base64.getDecoder().decode(username.getBytes()));	
		String decodedStringPwd= new String (Base64.getDecoder().decode(password.getBytes()));
		driver.findElement(By.id("username-inp")).clear();
		driver.findElement(By.id("username-inp")).sendKeys(decodedStringUsr);
		driver.findElement(By.id("password-inp")).clear();
		driver.findElement(By.id("password-inp")).sendKeys(decodedStringPwd);

		//String enodedString= Base64.getEncoder().encodeToString(username.getBytes());
		//System.out.println("This is the encoded username:" + enodedString);
		Thread.sleep(5000);

	}

	@SuppressWarnings("deprecation")
	@When("clicks on sign in button")
	public void clicks_on_sign_in_button() throws InterruptedException, IOException {

		// driver.findElement(By.id("signInSubmitButton")).click();
		// WebElement we= driver.findElement(By.cssSelector(".ng-untouched"));
		// driver.findElement(By.xpath("//button[@id='signInSubmitButton']/span"));

		WebElement we = driver.findElement(By.xpath("//button[@id='signInSubmitButton']/span"));

		boolean tf = we.isEnabled();
		System.out.println("Is Sign-in Button enabled before click :?" + tf);
		if (tf) 
		we.submit();
		Thread.sleep(2000);
	
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		//Screenshot screenshot = new AShot().takeScreenshot(driver,we);
		//ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") +"/target/Images/ElementScreenshot.png"));
		//BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") +"/target/Images/ElementScreenshotWithPixle.png"));
	    //Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImage);
	    //BufferedImage actualImage = logoImageScreenshot.getImage();
	    //ImageDiffer imgDiff = new ImageDiffer();
	    //ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
	    //Assert.assertFalse(diff.hasDiff(),"Images are Same");
	                 
		boolean tf2 = we.isEnabled();
		Assert.assertTrue(tf2);
		System.out.println("Is Sign-in Button enabled after click :?"+tf2);
		Thread.sleep(10000);
	}

	@Then("^User (.*) to the account overview page$")
	public void user_navigated_to_the_account_overview_page(int navigated) {

		switch (navigated) {
		case 1:

			if (driver.getPageSource().contains("This field is required"))
				System.out.println("Login Unsuccessful due to Username is empty");
			else
				System.out.println("Something wrong with this case1");
			break;
		case 2:
			if (driver.getPageSource().contains("This field is required"))
				System.out.println("Login Unsuccessful due to password is empty");
			else
				System.out.println("Something wrong with this case2");

			break;
		case 3:
			
			if (driver.getPageSource().contains("The username or password is incorrect."))
				System.out.println("Login Unsuccessful due to Username invalid");
			else
				System.out.println("Something wrong with this case3");
			break;
		case 4:
			if (driver.getPageSource().contains("The username or password is incorrect."))
				System.out.println("Login Unsuccessful due to Username invalid");
			else
				System.out.println("Something wrong with this case4");
			break;
		case 5:
			String accExp= "https://secure-sandbox.modulrfinance.com/customer/account";
			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sign out'])[1]/following::section[1]")).click();
			String accActl = driver.getCurrentUrl();
			Assert.assertEquals(accExp, accActl);
			System.out.println("Logged in successfully");
			break;
		}
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
