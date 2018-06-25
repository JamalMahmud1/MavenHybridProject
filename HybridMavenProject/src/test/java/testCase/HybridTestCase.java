package testCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import operation.ReadObjects;
import operation.UIOperations;

public class HybridTestCase {
WebDriver driver;


	
	@Test(dataProvider="HybridData")
	public void UnderTest(String testCaseId, String testCaseName, String keyWord, 
			String objectName, String objectType, String data) throws Exception {
		
		if(testCaseName!=null && testCaseName.length()!=0) {
			//driver=new FirefoxDriver();
			System.setProperty("webdriver.chrome.driver","C:\\ALL Driver\\chromedriver.exe");
			driver=new ChromeDriver();
			
			
		}
		
		ReadObjects object=new ReadObjects();
		Properties allObject=object.getObjectRepository();
		
		UIOperations uio=new UIOperations(driver);
		uio.KeyWordPerform(allObject, keyWord, objectName, objectType, data);
		
		
	}
	@DataProvider(name="HybridData")
    public Object[][] TestNgDataProvide() throws IOException {
    	Object[][] object=null;
    	
    	File f=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testData", "TestCase.xlsx");
    	FileInputStream fis=new FileInputStream(f);
    	Workbook wb=new XSSFWorkbook(fis);
    	Sheet ws=wb.getSheet("Data2");
    	
    	int rowCount=ws.getLastRowNum()-ws.getFirstRowNum();
    	int colCount=6;
    	object=new Object[rowCount][colCount];
    	for(int i=0;i<rowCount; i=i+1) {
    		Row r=ws.getRow(i+1);
    		for(int j=0; j<r.getLastCellNum(); j=j+1) {
    			object[i][j]=r.getCell(j).toString();
    		}
    	}
    	return object;
    	
    }



}



