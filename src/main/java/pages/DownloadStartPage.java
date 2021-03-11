package pages;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

/**
 * @author gorachz
 * This java page class stores all locators and methods for the Download page of the voicemod website 
 *
 */
public class DownloadStartPage extends BaseClass {
	
	@FindBy(xpath = "//div[@data-testid='activation_title']")
	public WebElement downloadStartPageTitle;
	
	/**
	 * @param driver 
	 * This method initializes PageFactory instance for the page class.
	 */
	public DownloadStartPage(WebDriver driver) {
		BaseClass.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * @return - this method returns true if the text 'DOWNLOAD STARTED' appears on the download page
	 */
	public boolean verifyDownloadStartPage() {
		return downloadStartPageTitle.isDisplayed();
	}
	
	/**
	 * @return - this method return true if a file exists in the given directory
	 * @throws InterruptedException
	 */
	public boolean checkIfFileIsDownloaded() throws InterruptedException {
		
		Thread.sleep(4000); // forgive this one thread.sleep as the assignment was huge and setting fluent wait here was taking time. Please update the time as needed to run the test.
		return FileUtils.sizeOfDirectory(new File(downloadVoicemodExePath))!=0;
	}

}
