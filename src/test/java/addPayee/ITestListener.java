package addPayee;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ITestListener {
	
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();    
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);}

	@Test
	public void enterUrl() {
		driver.get("http://zero.webappsecurity.com/index.html");
		String actualTitle=driver.getTitle();
		String expectedTitle= "Zero - Personal Banking - Loans - Credit Cards";
		Assert.assertEquals(actualTitle, expectedTitle, "Zero bank title displayed");

		
//	}
//	@Test(priority=2)
		
	//public void login() {
		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.id("user_login")).sendKeys("username");
		driver.findElement(By.id("user_password")).sendKeys("password");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
//	}
//	@Test(priority=3)
//	public void payBills() {
		driver.findElement(By.linkText("Pay Bills")).click();
		driver.findElement(By.linkText("Add New Payee")).click();
		driver.findElement(By.xpath("//input[@id='np_new_payee_name']")).sendKeys("HydroOne Utility");
		driver.findElement(By.id("np_new_payee_address")).sendKeys("200 RobertSpec Pkwy, Mississauga,ON L6R1K9");
		driver.findElement(By.id("np_new_payee_account")).sendKeys("123234434");					
		driver.findElement(By.id("np_new_payee_details")).sendKeys("Natural Gas Utility");
		driver.findElement(By.id("add_new_payee")).click();
		String Actual=driver.findElement(By.xpath("//div[@id='alert_content']")).getText();
		String Expected ="The new payee HydroOne Utility was successfully created.";
		Assert.assertEquals(Actual, Expected);
		}

	@AfterMethod
	public void closeBrowser(ITestResult testResult)
	{
		if(testResult.getStatus()==ITestResult.FAILURE){
			System.out.println("this is failed test case"+ testResult.getMethod().getMethodName());
		}if(testResult.getStatus()==ITestResult.SUCCESS) {
			
	System.out.println("this is failed testcase"+testResult.getMethod().getMethodName());
	}
	
	driver.close();}}

	
	
	


