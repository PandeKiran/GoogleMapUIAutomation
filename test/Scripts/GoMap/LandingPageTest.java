package GoMap;

import java.io.IOException;

import org.testng.annotations.Test;

import com.GMap.Functions.LandingPage;
import com.commonUtils.seleniumCommonMethods;

public class LandingPageTest extends seleniumCommonMethods {
	LandingPage landingPage=new LandingPage();
	
	@Test(priority=1)
	public void verifyLaunchApp() throws IOException {
		readConfigPropertyFile();
		logger=report.createTest("verifyLaunchApp");
		logger.info("TC Starts");
		launchApp(properties.getProperty("appURL"));
		landingPage.verifyLaunchApp();
		logger.info("TC Ends");
	}
	
	@Test(priority=2)
	public void verifyMapLandingPage() throws IOException {
		logger=report.createTest("verifyMapLandingPage");
		logger.info("TC Starts");
		landingPage.verifyMapLandingPage();
		logger.info("TC Ends");
	}

}
