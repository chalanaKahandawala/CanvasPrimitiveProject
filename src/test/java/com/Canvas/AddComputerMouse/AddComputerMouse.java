package com.Canvas.AddComputerMouse;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddComputerMouse {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		String username = "";
		String pw = "";
		String xpathDockBtn = "//div[@title='Dock Catalog']//div[@class='container']";
		String xpathCatalogPanel = "//div[@title='Catalog Panel']";
		String xpathDIComputer = "//div[@data-id='614846a5-a871-4caf-9fdd-af8cd713be10']";  
		String xpathCanvas = "//div[@id='canvas-div']";
		String xpathDIMouse = "//div[@data-id='e4572fe0-6114-46b7-a3a3-62d097dc1028']"; 
		System.setProperty("webdriver.chrome.driver", "F:/Automation/chromedriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//Initiate Webdriver wait
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		
		//maximize the window size
		driver.manage().window().maximize();
		
		//Navigate to the website
		driver.get("https://app.xyicon.com/");
		
		//Wait till page load 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userName")));
		
		//Capture element username input field
		WebElement inUsername = driver.findElement(By.id("userName"));
		
		//Clear username if it is not empty
		if (!inUsername.getText().isEmpty()) {
			inUsername.clear();
		}
		
		//Type username
		inUsername.sendKeys(username);
		
		//Capture element password input field
		WebElement inPassword = driver.findElement(By.id("pwInput"));
		
		//Clear username if it is not empty
		if (!inPassword.getText().isEmpty()) {
			inUsername.clear();
		}
		
		//Type password
		inPassword.sendKeys(pw);
		
		//Capture element Log in button
		WebElement btnLogin = driver.findElement(By.name("submitButton"));
		
		//Click Login button
		btnLogin.click();
		
		//Wait till element present
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-key='7e9857a3-060f-4097-9aec-8e2e623305aa']")));
		
		//Capture Element checkbox Rose Holdings
		WebElement chkbxRoseHoldings = driver.findElement(By.xpath("//div[@data-key='7e9857a3-060f-4097-9aec-8e2e623305aa']"));
		
		//Select check box
		if (!chkbxRoseHoldings.isSelected()) {
			chkbxRoseHoldings.click();
		}
		
		//Capture element hamberger
		WebElement btnHamberger = driver.findElement(By.xpath("//span[@class='navButton button']"));
		
		//Click on hamberger
		btnHamberger.click();
		
		//Wait till element present
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='SideBarNavItem navItem'][2]")));
						
		//Capture element spaces 
		WebElement navSpaces= driver.findElement(By.xpath("//li[@class='SideBarNavItem navItem'][2]"));
		
		//Wait till element to be clickable
		wait.until(ExpectedConditions.elementToBeClickable(navSpaces));
		
		//Click spaces
		navSpaces.click();
		
		//Wait till element present
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title='Level 1']")));
		
		//Click card view
		WebElement vbxHeader = driver.findElement(By.xpath("//div[@title='Level 1']"));
		
		//Wait till element to be clickable
		wait.until(ExpectedConditions.elementToBeClickable(vbxHeader));
		
		//in new Action
		Actions actions = new Actions(driver);
		
		//double click level 1
		actions.doubleClick(vbxHeader).build().perform();
		
		//Wait till element present
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathCatalogPanel)));
		
		//Wait till element present
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathCanvas)));
		
		//capture element
		WebElement canvas = driver.findElement(By.xpath(xpathCanvas));
		
		//Wait till element to be clickable
		wait.until(ExpectedConditions.elementToBeClickable(canvas));
		
		//Capture element Catalog panel
		WebElement btnCatalogPanel = driver.findElement(By.xpath(xpathCatalogPanel)); 
		
		Thread.sleep(15000);
		
		//Wait till element to be clickable
		wait.until(ExpectedConditions.elementToBeClickable(btnCatalogPanel));
				
		//Click on catalog panel
		btnCatalogPanel.click();
		
		//Thread.sleep(20);
		
		//Wait till element present
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathDockBtn)));
		
		//Capture element
		WebElement btnDockCatalog =driver.findElement(By.xpath(xpathDockBtn));
		
		//Wait till element to be clickable
		//wait.until(ExpectedConditions.elementToBeClickable(btnDockCatalog));
		
		//Click on Dock button
		btnDockCatalog.click();
		
		//Capture element
		WebElement btnZoom = driver.findElement(By.xpath("//div[@class='zoomPercentage']"));
		
		//clcik zoom to 100%
		btnZoom.click();
		
		Thread.sleep(30);
		
		//Wait till element present
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathDIComputer)));
		
		//Capture element
		WebElement computerIcon =driver.findElement(By.xpath(xpathDIComputer));
		
		//drag and drop computer on canvas
		actions.dragAndDrop(computerIcon,canvas).build().perform();
		
		//Capture element
		WebElement mouseIcon =driver.findElement(By.xpath(xpathDIMouse));
		
		//drag and drop computer on canvas
		actions.dragAndDrop(mouseIcon,computerIcon).build().perform();
		
		// Extract the images of the dropped mouse and computer icons from the canvas element 
		String droppedMouseImage = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].toDataURL('image/png').substring(21);",canvas); 
		String droppedComputerImage = (String) ((JavascriptExecutor)driver).
	    executeScript("return arguments[0].toDataURL('image/png').substring(21);",canvas);
	  
	  // Compare the images with the images of the catalog item list 
		String catalogMouseImage = "catalog-mouse.png"; 
		String catalogComputerImage ="catalog-computer.png"; Assert.assertEquals(droppedMouseImage,catalogMouseImage); Assert.assertEquals(droppedComputerImage,catalogComputerImage);
		 
		/*// Validate images
		validateImages("reference_computer_image.png", "canvas_screenshot.png");
		validateImages("reference_mouse_image.png", "canvas_screenshot.png");

		// Close the browser
		driver.quit();

		// Method to validate image similarity using SikuliX
		private static void validateImages(String referenceImagePath, String canvasImagePath) {
	    // Set the path for SikuliX to find the images
	    ImagePath.add("path/to/images");

	    // Load reference image and canvas image
	    org.sikuli.script.Pattern referencePattern = new org.sikuli.script.Pattern(referenceImagePath);
	    org.sikuli.script.Pattern canvasPattern = new org.sikuli.script.Pattern(canvasImagePath);

	    // Perform image comparison
	    try {
	        screen.wait(referencePattern, 10);
	        screen.find(canvasPattern);
	    } catch (FindFailed e) {
	        // Handle image validation failure
	        e.printStackTrace();
	        Assert.fail("Image validation failed");
	    }*/	
		

		System.out.println("Test Pass");
  
	}
  
  }
 
