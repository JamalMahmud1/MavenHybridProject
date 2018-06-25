package operation;

import java.io.File;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class UIOperations {
	WebDriver driver;

	public UIOperations(WebDriver driver) {
		this.driver=driver;
	}
	public void KeyWordPerform(Properties p,String keyword, String objectName,String objectType,String data) throws Exception {
		switch(keyword.toUpperCase()) {
		case"GOTOURL":
			driver.get(p.getProperty(data));
			break;
		case"SENDKEYS":
			driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(data);
			break;
		case"CLICK":
			driver.findElement(this.getObject(p, objectName, objectType)).click();
			break;
		case "SELECT":
			String stn=null;
			if(stn.equalsIgnoreCase("String")) {
			new Select(driver.findElement(this.getObject(p, objectName, objectType))).selectByVisibleText(data);
			}
			else if(stn.equalsIgnoreCase("Value")) {
			new Select(driver.findElement(this.getObject(p, objectName, objectType))).selectByValue(data);
			}
			
			break;

		case"GETTITLE":
			String title=driver.getTitle();
			System.out.println("Title is:"+title);
			break;
		case"GETTEXT":
			String text=
			driver.findElement(this.getObject(p, objectName, objectType)).getText();
			System.out.println("Text is:"+text);
			break;


		case"GETTYPEDTEXT":
			String TypedText=
			driver.findElement(this.getObject(p, objectName, objectType)).getAttribute("value");
			System.out.println("TypedText is:"+TypedText);
			break;
		case "SCREENSHOT":
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("C:\\Jamal\\Facebook.jpg"));
			break;
		case"CHILDWINDOW":
			Set<String>ChildWindow=driver.getWindowHandles();
			for(String  child:ChildWindow) {
				driver.switchTo().window(child);
				
			}
			
			break;
		case"MAINWINDOW":
			String MainWindow=driver.getWindowHandle();
			driver.switchTo().window(MainWindow);
			
			break;
		case"GOTOBACKPAGE":
			driver.navigate().back();
			break;
		case"GOTOFORWARDPAGE":
			driver.navigate().forward();
			break;
		case"GOTOREFRESSPAGE":
			driver.navigate().refresh();
			break;

		case"RIGHTCLICK":
			Actions act=new Actions(driver);
			WebElement ele1=driver.findElement(this.getObject(p, objectName, objectType));
			act.contextClick(ele1).build().perform();
			break;

		case "GETPOINT":

			Point pnt=driver.findElement(this.getObject(p, objectName, objectType)).getLocation();
			System.out.println("Point is:"+pnt);
			break;


		case "THREAD":
			Thread.sleep(3000);
			break;
		case"CLOSE":
			driver.close();
			break;
		case"QUIT":
			driver.quit();
			break;
		}

	}
	public By getObject(Properties p, String objectName,String objectType) throws Exception {
		if(objectType.equalsIgnoreCase("ID")) {
			return By.id(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("NAME")) {
			return By.name(p.getProperty(objectName));

		}
		else if (objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(p.getProperty(objectName));
		}
		else if (objectType.equalsIgnoreCase("CLASS")) {

			return By.className(p.getProperty(objectName));

		}
		else if (objectType.equalsIgnoreCase("TAGNAME")) {
			return By.tagName(p.getProperty(objectName));
		}
		else if (objectType.equalsIgnoreCase("LINKTEXT")) {

			return By.linkText(p.getProperty(objectName));
		}
		else if (objectType.equalsIgnoreCase("PARTIALLINKTEXT")) {
			return By.partialLinkText(p.getProperty(objectName));
		}
		else if (objectType.equalsIgnoreCase("CSSSELECTOR")) {

			return By.cssSelector(p.getProperty(objectName));





		}
		else {
			throw new Exception("Wrong object type");
		}


	}
}


