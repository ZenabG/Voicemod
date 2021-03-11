package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.Status;

import base.BaseClass;
import base.Constants;

/**
 * @author gorachz
 * This java page class stores all locators and methods for the Home page of the voicemod website.
 */
public class HomePage extends BaseClass {
	
	@FindBy(xpath = "//a[@class='voicemod-button']")
	public WebElement getVoicemodFreeButton;
	
	@FindBy(xpath = "//li[@id='menu-item-"+Constants.OLD_WEBSITE_LANGUAGE_ID_IN_DOM+"']")
	public WebElement changeLanguageLinkOld;
	
	@FindBy(xpath = "//li[@id='menu-item-"+Constants.NEW_WEBSITE_LANGUAGE_ID_IN_DOM+"']")
	public WebElement changeLanguageLinkNew;
	
	@FindBy(xpath = "//li[@id='menu-item-"+Constants.OLD_WEBSITE_LANGUAGE_ID_IN_DOM+"']//span")
	public List<WebElement> languageDropDown;
	
	@FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
	public WebElement acceptAllCookies;
	
	@FindBy(xpath = "//section[@class='bloque_texto']")
	public List<WebElement> textBlocks;
	
	@FindBy(xpath = "//img[contains(@alt, 'Yotube Image for video')]")
	public List<WebElement> multimedia;
	
	@FindBy(xpath = "//section[@class='two_blocks ']")
	public List<WebElement> headerAndMediaBlocks;
	
	@FindBy(xpath = "//footer[@class='footer']")
	public WebElement footer;
	
	/**
	 * @param driver
	 * This method initializes PageFactory instance for the page class.
	 */
	public HomePage(WebDriver driver) {
		BaseClass.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method waits for the 'Accept Cookies' banner to disappear and then clicks on 'Get Voicemod Free' button.
	 */
	public void clickGetVoicemodFreeButton() {
		wait.until(ExpectedConditions.invisibilityOfAllElements(acceptAllCookies));
		getVoicemodFreeButton.click();
		
		test.log(Status.INFO, "Get Voicemod Free button is clicked");
	}
	
	/**
	 * This method waits for the 'Accept Cookies' banner to disappear and then clicks on 'Change Language' dropdown.
	 */
	public void clickTheLanguageDropdown() {
		wait.until(ExpectedConditions.invisibilityOfAllElements(acceptAllCookies));
		changeLanguageLinkOld.click();
		
		test.log(Status.INFO, "Change language drop down is clicked");
	}
	
	/**
	 * This method waits for the 'Accept Cookies' banner to appear and then clicks on 'Accept All Cookies' button.
	 */
	public void clickAcceptAllCookiesButton() {
		wait.until(ExpectedConditions.elementToBeClickable(acceptAllCookies));
		acceptAllCookies.click();
		
		test.log(Status.INFO, "Accept All Cookies button is clicked");
	}
	
	/**
	 * @param newLanguage - the value of the language that user wants the website to change to
	 * This method finds the given language from the 'Change Language' dropdown and clicks on it. 
	 */
	public void changeLanguage(String newLanguage) {
		
		for(int i=0;i<languageDropDown.size();i++) {
			
			if(languageDropDown.get(i).getText().equalsIgnoreCase(newLanguage)) {
				languageDropDown.get(i).click();
			}
		}
		
		test.log(Status.INFO, newLanguage+" language is chosen from the change language dropdown");
	}
	
	

}
