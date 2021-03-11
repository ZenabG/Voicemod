package base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.commons.io.FileUtils;

/**
 * @author gorachz This java class consists of all settings that need to be done
 *         before running a test i.e. setting the browser preferences and
 *         creating and killing the driver.
 *
 */
public class BaseClass {

	protected static WebDriver driver;
	protected static Logger log;
	protected static WebDriverWait wait;
	static FirefoxOptions capabilities;
	static FirefoxProfile profile;

	protected static String downloadVoicemodExePath = System.getProperty("user.dir") + Constants.VOICEMOD_EXE_DOWNLOAD_PATH;

	protected static ExtentHtmlReporter htmlReporter;
	protected static ExtentReports extent;
	protected static ExtentTest test;

	/**
	 * This method initialises different extent report objects defined above
	 */
	@BeforeSuite
	public static void beforeSuite() {
		// extent report logic
		htmlReporter = new ExtentHtmlReporter(Constants.EXTENT_REPORT_PATH + System.currentTimeMillis() + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	/**
	 * @param ctx
	 * This method sets the extent report name, title and its theme.
	 */
	@BeforeTest
	public void beforeTest(ITestContext ctx) {
		// extent report logic

		htmlReporter.config().setReportName("Test Report" + ctx.getCurrentXmlTest().getSuite().getName());
		htmlReporter.config().setDocumentTitle("Test Report");
		htmlReporter.config().setTheme(Theme.STANDARD);

	}

	/**
	 * This method sets the browser preferences for firefox that are needed for
	 * clearing the browser cache. Setting them here instead of @BeforeMethod as it
	 * works better.
	 */
	@BeforeTest
	public static void clearBrowserCache() {
		profile = new FirefoxProfile();
		profile.setPreference("browser.cache.disk.enable", false);
		profile.setPreference("browser.cache.memory.enable", false);
		profile.setPreference("browser.cache.offline.enable", false);
		profile.setPreference("network.http.use-cache", false);

	}

	/**
	 * @BeforeMethod - runs once before every test method This method sets the
	 *               browser preferences for firefox that are needed for disabling
	 *               the download pop-up and directly start the download.
	 */
	@BeforeMethod
	public static void setDownloadPrefInFirefox() {

		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.dir", downloadVoicemodExePath);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-msdownload");

		capabilities = new FirefoxOptions();
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);

	}

	/**
	 * @param url - passes the browser url to the tests directly from TestNg suite.
	 * @BeforeMethod - runs once before every test method This java method creates
	 *               the firefox driver instance by using all the capabilities set
	 *               in the above methods
	 */
	@BeforeMethod
	@Parameters({ "url" })
	public static void setUpBrowser(@Optional("https://www.voicemod.net/") String url) {

		System.setProperty(Constants.systemKeyFirefox, System.getProperty("user.dir") + Constants.systemValueFirefox);
		driver = new FirefoxDriver(capabilities);

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		driver.get(Constants.HOME_PAGE_URL);
		test.log(Status.INFO, "The website "+url+" is loaded");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		log = Logger.getLogger("global");
		wait = new WebDriverWait(driver, 10);
	}

	/**
	 * @param result
	 * This method creates entry in extent report for each test by their test method name
	 */
	@BeforeMethod
	public static void before(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	/**
	 * @param result
	 * @throws IOException
	 * @AfterMethod - runs once after every test method
	 * This method gets the failure message in extent report for failed and skipped tests. It also closes the browser and kills the driver instance.
	 */
	@AfterMethod
	public void getTestResultInExtentReport(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build());
		}

		if (result.getStatus() == ITestResult.SKIP)
			test.skip(result.getThrowable().getMessage());

		extent.flush();
		driver.close();
	}

	/**
	 * @throws IOException 
	 * This java method deletes all downloaded files from the Voicemod directory where all downloaded files are saved. This method is executed before starting the download voicemod exe test.
	 */
	protected void clearAllDownloadedVoicemodFiles() throws IOException {
		FileUtils.cleanDirectory(new File(downloadVoicemodExePath));
	}

	/**
	 * @return
	 * This method takes screenshot when called and stores them at the specified path
	 */
	public String screenshot() {

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destFile = Constants.SCREENSHOT_PATH + System.currentTimeMillis() + ".jpg";

		try {
			FileUtils.copyFile(srcFile, new File(destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile;
	}

}
