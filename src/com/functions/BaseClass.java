package com.functions;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass {
	public static WebDriver driver;
	String baseUrl;
	String browserName;
	private static HashMap<String, String> imageToNameMap = new HashMap<String, String>();
	public static final String IMAGE_NAME_PUNISHER = "punisher";

	public void launchURL() throws Exception {
		try {
			// Loading config.properties file
			Properties prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"C:\\econsult\\ShapeSecurity\\src\\com\\functions\\config.properties");
			prop.load(ip);
			baseUrl = prop.getProperty("URL");
			browserName = prop.getProperty("browser");
			// Selecting the browser
			if (browserName.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\SeleniumResources\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", ".//IEDriverServer");
				driver = new InternetExplorerDriver();
			}
			// Loading application page and declaring default timeouts
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(baseUrl);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
	}

	// Method to find the longest word from dynamic text
	public String findLongestWord(String dynamicTextOnPage) {
		String longestWord = "";
		// Split the string to each word separated by spaces
		dynamicTextOnPage.replaceAll(".", "");
		String[] words = dynamicTextOnPage.split(" ");
		for (int i = 0; i < words.length; i++) {
			// find the largest word
			if (words[i].length() > longestWord.length()) {
				longestWord = words[i];
			}
		}
		return longestWord;
	}

	public List<String> getImageNames(List<String> listImagesToConvert) {
		List<String> listImageNames = new ArrayList<String>();
		for (String image : listImagesToConvert) {
			listImageNames.add(imageToNameMap.get(image) != null ? imageToNameMap.get(image) : "unknown");
		}
		return listImageNames;

	}

	// Loading all the possible image names from the test page to a map
	// Assumption : these are the only possible images to appear on the test page
	public void setImageToNameMap() {
		imageToNameMap.put("Avatar-1.jpg", "image 1");
		imageToNameMap.put("Avatar-2.jpg", "image 2");
		imageToNameMap.put("Avatar-3.jpg", IMAGE_NAME_PUNISHER);
		imageToNameMap.put("Avatar-4.jpg", "image 4");
		imageToNameMap.put("Avatar-5.jpg", "image 5");
		imageToNameMap.put("Avatar-6.jpg", "image 6");
		imageToNameMap.put("Avatar-7.jpg", "image 7");
	}
}
