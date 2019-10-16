package com.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.functions.BaseClass;

public class testCaseValidation extends BaseClass {
	// Initialize variables and the objects
	public By dynamicText = By.xpath("//div[@class='large-10 columns']");
	public By avatarsLocator = By.xpath("//div[@class='large-2 columns']/img");

	@BeforeClass
	public void setUp() throws Exception {
		// Launch the Browser and open the test URL
		launchURL();
		// Calling predefined images that could appear on test page
		setImageToNameMap();
	}

	// Test case #1 to find longest word from dynamic text
	@Test(priority = 1)
	public void longestWord() {
		String dynamicTextOnPage = "";
		String longestWord = "";
		// Get all the Dynamic text to a single string to find longest word
		List<WebElement> lorum = driver.findElements(dynamicText);
		Iterator<WebElement> texts = lorum.iterator();
		while (texts.hasNext()) {
			String ltext = texts.next().getText();
			dynamicTextOnPage = dynamicTextOnPage + " " + ltext;
		}
		longestWord = findLongestWord(dynamicTextOnPage);
		Assert.assertTrue(longestWord.length() > 10, "There are no words greater than length 10 in the dynamic text");
		// Display the longest word with length
		System.out.println(
				"The word with largest length is : " + longestWord + " and its length is : " + longestWord.length());

	}

	@Test(priority = 2)
	public void punisherImageValidation() {
		String image = "";
		List<WebElement> listOfImages = driver.findElements(avatarsLocator);
		List<String> listImages = new ArrayList<String>();
		for (WebElement we : listOfImages) {
			image = we.getAttribute("src").substring(
					"https://the-internet.herokuapp.com/img/avatars/Original-Facebook-Geek-Profile-".length());
			listImages.add(image);
		}
		List<String> listImageNames = getImageNames(listImages);
		System.out.println("List of images displayed on the page : " + listImageNames);
		// Assertion to fail the test case if Punisher image exists and pass when not
		// exists
		Assert.assertFalse(listImageNames.contains(IMAGE_NAME_PUNISHER), "Punisher image found on test page");
	}

	@AfterClass
	public void afterClass() {
		// Close the browser
		driver.quit();
	}

}
