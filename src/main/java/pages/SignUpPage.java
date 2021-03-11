package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.Status;

import base.BaseClass;

/**
 * @author gorachz
 * This java page class stores all locators and methods for the Sign up page of the voicemod website
 */
public class SignUpPage extends BaseClass {

	@FindBy(xpath = "//a[@id='Google']")
	public WebElement googleSignInButton;

	@FindBy(xpath = "//input[@type='email']")
	public WebElement googleEnterEmail;

	@FindBy(xpath = "//div[@id='identifierNext']")
	public WebElement googleEmailNextButton;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement googleEnterPassword;
	
	@FindBy(xpath = "//div[@id='passwordNext']")
	public WebElement googlePasswordNextButton;

	@FindBy(xpath = "//a[@id='Discord']")
	public WebElement discordSignInButton;
	
	@FindBy(xpath = "//a[@id='Twitch']")
	public WebElement twitchSignInButton;
	
	/**
	 * @param driver
	 * This method initializes PageFactory instance for the page class.
	 */
	public SignUpPage(WebDriver driver) {
		BaseClass.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method clicks on 'Continue with Google' button on the sign up page
	 */
	public void clickGoogleSignInButton() {
		googleSignInButton.click();
		
		test.log(Status.INFO, "Sign in using google button is clicked");
	}

	/**
	 * @param email - google email
	 * @param password - google password
	 * This method logs in to google from the official google sign up page using the email and password values 
	 */
	public void loginToGoogle(String email, String password) {

		googleEnterEmail.sendKeys(email);
		test.log(Status.INFO, "Entered google email for signing in");
		
		googleEmailNextButton.click();
		test.log(Status.INFO, "Clicked on next button");
		
		wait.until(ExpectedConditions.elementToBeClickable(googleEnterPassword));
		googleEnterPassword.sendKeys(password);
		test.log(Status.INFO, "Entered google password for signing in");
		
		googlePasswordNextButton.click();
		test.log(Status.INFO, "Clicked on submit button");
		
	}
	
	/**
	 * This method clicks on 'Continue with Discord' button on the sign up page
	 */
	public void clickDiscordSignInButton() {
		discordSignInButton.click();
		
		test.log(Status.INFO, "Sign in using discord button is clicked");
	}
	
	/**
	 * This method clicks on 'Continue with Twitch' button on the sign up page
	 */
	public void clickTwitchSignInButton() {
		twitchSignInButton.click();
		
		test.log(Status.INFO, "Sign in using twitch button is clicked");
	}

}
