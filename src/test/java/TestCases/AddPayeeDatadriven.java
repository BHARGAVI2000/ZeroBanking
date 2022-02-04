package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utility.XTentReport;
import pageObjects.AddPayeePage;
import pageObjects.LoginPage;
import pageObjects.SignInPage;

//data driven testing including listener class with xml execution 
//data
public class AddPayeeDatadriven extends base {

	SignInPage si;
	LoginPage lp ;
	AddPayeePage ap;

	@Test(priority=1)
	public void login() throws InterruptedException {
		Report=Utility.XTentReport.getReport();
		test=Report.startTest("Login test stared");

		si= new SignInPage(driver);
		lp = new LoginPage(driver);
		ap=new AddPayeePage(driver);


		si.clickSignIn();
		test.log(LogStatus.INFO, "Clicked SignIn");


		lp.doLogin("username", "password");

		test.log(LogStatus.INFO, "Entered username, password, clicked submit");
		lp.clickDetailButton();
		lp.clickProceedLink();
		test.log(LogStatus.INFO, "Login successful");

		String actualTitle = driver.getTitle();
		String expectedTitle= "Zero - Account Summary";
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}


	@Test(dataProvider ="PayeeData",priority=2)
	public void addPayee(String uname, String address , String account, String paydetails) throws InterruptedException {
		test.log(LogStatus.INFO, "click Paybills");
		ap.clickPaybills();
		ap.clickAddNewPayee();
		ap.enterPayee(uname);
		ap.enterPayeeAddress(address);
		ap.enterPayeeAccount(account);
		ap.enterPayeeDetails(paydetails);
		test.log(LogStatus.INFO, "Entered the payee details");
		ap.clickAddBtn();
		test.log(LogStatus.INFO, "clicked on pay button");
		Thread.sleep(1000);

		String ActualText= driver.findElement(By.id("alert_content")).getText();
		String ExpectedText="The new payee Payee1 was successfully created.";
		Assert.assertEquals(ActualText,ExpectedText);

	}

	@DataProvider(name="PayeeData")
	public String[][] testData() throws IOException {
		String path= System.getProperty("user.dir");
		File src= new File(path+"\\src\\test\\resources\\excelData\\NewPayeeDetailsData.xlsx");
		FileInputStream fis= new FileInputStream(src);
		XSSFWorkbook workbook= new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int rowCount=sheet.getLastRowNum();
		int colCount= sheet.getRow(0).getLastCellNum();
		String Data[][]= new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow  currentRow=sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {

				Data[i-1][j]=	currentRow.getCell(j).getStringCellValue();
			}}
		//workbook.close();

		fis.close();
		return Data;
	}

}















