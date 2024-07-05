package com.commonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class seleniumCommonMethods {
	
	public static Properties properties=new Properties();
	public static WebDriver driver;
	Date date;
	File file;
	public static ExtentReports report;
	public static ExtentTest logger;

	
/**
 * Read config proprties file to get data
 */
	public void readConfigPropertyFile() {
		try {
			FileInputStream file= new FileInputStream(System.getProperty("user.dir")+"\\TestData\\config.properties");
			properties.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/**
 * launchApp method where system calling application url and driver
 */

	public void launchApp(String appURL) {
		System.out.println(appURL);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):properties.getProperty("browser");
		System.out.println(browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chromedriver.driver",System.getProperty("user.dir")+"\\GoogleMap\\test\\resources\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browserName.equalsIgnoreCase("chromeHeadless")) {
			System.setProperty("webdriver.geckodriver.driver", System.getProperty("user.dir")+"\\GoogleMap\\test\\resources\\geckodriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--headless=new");
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1920, 1080)); // Set a reasonable window size

		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.geckodriver.driver", System.getProperty("user.dir")+"\\GoogleMap\\test\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		}
		else {
			System.out.println("Browser is not present");
		}
		driver.get(appURL);
	}
	
/**
 * reportFolderDate() method to get current date and time
 */
	public String reportFolderDate() {
		date=new Date();
		SimpleDateFormat f= new SimpleDateFormat("YYYY-dd-MM-hh-mm");
		String folderDate=f.format(date);
		return folderDate;
		
	}
	
	@BeforeTest(alwaysRun = true)
	public void extendReport() {
		
		file= new File(System.getProperty("user.dir")+"\\reports\\report"+reportFolderDate()+"\\index.html");
		ExtentHtmlReporter extent = new ExtentHtmlReporter(file);
		report = new ExtentReports();
		report.attachReporter(extent);
	}
	
	//screenshot
	public String screenShots(String filename) {
		File sources = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = "./"+"/reports"+"/report"+reportFolderDate()+"/screenshot "+filename+".ping";
		try {
			FileUtils.copyFile(sources, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return destination;

	}
	
	//getText
	public String getText(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return driver.findElement(By.xpath(xpath)).getText();
	}
	
	public boolean isDisplayed(String xpath) {
		try {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath)).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@AfterTest(alwaysRun = true)
	public void extend() {
		report.flush();
	}
	
}
