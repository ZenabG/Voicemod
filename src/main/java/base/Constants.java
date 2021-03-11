package base;

/**
 * @author gorachz
 * This java class stores the constant values of any static or final variables needed in the project
 */
public class Constants {

	static String systemKeyFirefox = "webdriver.gecko.driver";
	static String systemValueFirefox= "/src/main/java/utilities/geckodriver";
	public static final String HOME_PAGE_URL = "https://www.voicemod.net/";
	public static final String OLD_WEBSITE_LANGUAGE = "English";
	public static final String OLD_WEBSITE_LANGUAGE_ID_IN_DOM = "2082";
	public static final String NEW_WEBSITE_LANGUAGE = "日本語";
	public static final String NEW_WEBSITE_LANGUAGE_ID_IN_DOM = "2460";
	public static final String VOICEMOD_EXE_DOWNLOAD_PATH = "/src/test/java/VoicemodDownloads";
	public static final String DISCORD_LOGIN_PAGE_URL_FRACTION = "https://discord.com/login";
	public static final String TWITCH_LOGIN_PAGE_URL_FRACTION = "https://www.twitch.tv/login";
	public static final String SCREENSHOT_PATH = ".test-extent/screenshots/shot";
	public static final String EXTENT_REPORT_PATH = "./test-extent/extent";
	
	private Constants() {
		
	}
}
