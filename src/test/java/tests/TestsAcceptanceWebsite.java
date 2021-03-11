package tests;

import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseClass;
import base.Constants;
import pages.DownloadStartPage;
import pages.HomePage;
import pages.SignUpPage;

/**
 * @author gorachz
 * This java class consists of automation of all the 5 acceptance tests which are added in the attached excel sheet at the path src/main/java/Utilities/AcceptanceTestsVoicemod.xlsx
 */
public class TestsAcceptanceWebsite extends BaseClass {
	HomePage homePage; 
	SignUpPage loginPage;
	DownloadStartPage downloadPage;

	/**
	 * @BeforeMethod - runs once before every test method
	 * This method initialises all the needed page class objects for calling the methods in the tests 
	 */
	@BeforeMethod
	public void beforeTest() {
		homePage = new HomePage(driver);
		loginPage = new SignUpPage(driver);
		downloadPage = new DownloadStartPage(driver);
	}

	
	@Test(description = "Test_Website_01 from Acceptance Tests excel sheet")
	public void downloadVoicemodUsingGoogleSignIn() throws IOException, InterruptedException {

		clearAllDownloadedVoicemodFiles();

		homePage.clickAcceptAllCookiesButton();
		driver.manage().deleteAllCookies(); // to go to the sign in page before download starts
		homePage.clickGetVoicemodFreeButton();

		loginPage.clickGoogleSignInButton();
		loginPage.loginToGoogle("tvoicemod@gmail.com", "TEst@123");

		Assert.assertTrue(downloadPage.verifyDownloadStartPage(), "This is not the Download start page");
		test.log(Status.INFO, "User is on Download voicemod exe page");
		
		Assert.assertTrue(downloadPage.checkIfFileIsDownloaded(), "The voicemod exe is not downoaded");
		test.log(Status.PASS, "Voicemod exe file was downloaded successfully and stored at "+downloadVoicemodExePath);

	}

	@Test(description = "Test_Website_02 from Acceptance Tests excel sheet")
	public void verifyVoicemodSignInUsingDiscord() throws IOException, InterruptedException {

		homePage.clickAcceptAllCookiesButton();
		driver.manage().deleteAllCookies(); // to go to the sign in page before download starts
		homePage.clickGetVoicemodFreeButton();
		
		loginPage.clickDiscordSignInButton();
		
		wait.until(ExpectedConditions.urlContains(Constants.DISCORD_LOGIN_PAGE_URL_FRACTION));
		Assert.assertTrue(driver.getCurrentUrl().contains(Constants.DISCORD_LOGIN_PAGE_URL_FRACTION));
		
		test.log(Status.PASS, "User is able to get to Discord login page from voicemod website");

	}
	
	@Test(description = "Test_Website_03 from Acceptance Tests excel sheet", priority = 1)
	public void verifyVoicemodSignInUsingTwitch() throws IOException, InterruptedException {

		homePage.clickAcceptAllCookiesButton();
		driver.manage().deleteAllCookies(); // to go to the sign in page before download starts
		homePage.clickGetVoicemodFreeButton();
		
		loginPage.clickTwitchSignInButton();
		
		wait.until(ExpectedConditions.urlContains(Constants.TWITCH_LOGIN_PAGE_URL_FRACTION));
		Assert.assertTrue(driver.getCurrentUrl().contains(Constants.TWITCH_LOGIN_PAGE_URL_FRACTION));
		
		test.log(Status.PASS, "User is able to get to Twitch login page from voicemod website");

	}

	@Test(description = "Test_Website_04 from Acceptance Tests excel sheet")
	public void changeTheWebsiteLanguage() {

		String oldlanguage = homePage.changeLanguageLinkOld.getText();
		Assert.assertTrue(oldlanguage.equalsIgnoreCase(Constants.OLD_WEBSITE_LANGUAGE),
				"The language of the website is not " + Constants.OLD_WEBSITE_LANGUAGE);

		homePage.clickAcceptAllCookiesButton();
		homePage.clickTheLanguageDropdown();
		homePage.changeLanguage(Constants.NEW_WEBSITE_LANGUAGE);

		String newlanguage = homePage.changeLanguageLinkNew.getText();
		Assert.assertTrue(newlanguage.equalsIgnoreCase(Constants.NEW_WEBSITE_LANGUAGE),
				"The language of the website is not " + Constants.NEW_WEBSITE_LANGUAGE);
		
		test.log(Status.PASS, "Language of the website is changed to "+Constants.NEW_WEBSITE_LANGUAGE);

	}

	@Test(description = "Test_Website_05 from Acceptance Tests excel sheet")
	public void verifyTheWebPageHasContent() {

		for (int i = 0; i < homePage.textBlocks.size(); i++) {

			Assert.assertFalse(homePage.textBlocks.get(i).getText().isEmpty(), "The text block " + i + " is empty");

		}
		
		test.log(Status.INFO, "All the text blocks have content");

		for (int i = 0; i < homePage.multimedia.size(); i++) {

			Assert.assertFalse(homePage.multimedia.get(i).getAttribute("data-src").isEmpty(),
					"The multimedia block " + i + " is empty");

		}
		
		test.log(Status.INFO, "All the multimedia blocks have content");

		for (int i = 0; i < homePage.headerAndMediaBlocks.size(); i++) {

			Assert.assertFalse(homePage.headerAndMediaBlocks.get(i).getText().isEmpty(),
					"The text & media block " + i + " is empty");

		}
		
		test.log(Status.INFO, "All the text and multimedia twin blocks have content");

		Assert.assertTrue(homePage.footer.isDisplayed(), "The webpage has content missing");
		test.log(Status.INFO, "The footer of the webpage is reached");
		
		test.log(Status.PASS, "The website has no content missing under any headers");
	}
}
