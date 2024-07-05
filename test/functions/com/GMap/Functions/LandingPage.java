package com.GMap.Functions;

import java.io.IOException;

import org.testng.Assert;

import com.GMap.Locators.LandingPageLocators;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.commonUtils.seleniumCommonMethods;


public class LandingPage extends seleniumCommonMethods{

	LandingPageLocators landingPageLocators =new LandingPageLocators();
	
	public void verifyLaunchApp() throws IOException {
		String getsearchMapsTitle = getText(landingPageLocators.searchMapsTitle);
		try {
			Assert.assertEquals(getsearchMapsTitle, "Search Google Maps");
			logger.info("Successfully launch google map app");

		} catch (AssertionError e) {
			logger.fail("Unable to launch google map app",MediaEntityBuilder.createScreenCaptureFromPath(screenShots("google map landing page")).build());
		}
//		if(getsearchMapsTitle.equals("Search Google")) {
//			logger.pass("Successfully launch google map app");
//		}
//		else {
//			logger.fail("Unable to launch google map app",MediaEntityBuilder.createScreenCaptureFromPath(screenShots("google map landing page")).build());
//		}
	}
	
	public void verifyMapLandingPage() throws IOException {
		try {
			isDisplayed(landingPageLocators.restaurants);
			logger.pass("Restaurants option is present on map");
			isDisplayed(landingPageLocators.hotels);
			logger.pass("Hotels option is present on map");
			isDisplayed(landingPageLocators.thingstodo);
			logger.pass("Things To Do option is present on map");
		} catch (Exception e) {
			logger.fail("unable to verify map landing page", MediaEntityBuilder.createScreenCaptureFromPath(screenShots("verifyMapLandingPage")).build());
		}
	
	}
	
}
