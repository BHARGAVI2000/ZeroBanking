package addPayee;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PayBillsTestNG {


	WebDriver driver;
	static String month= "February 2022";
	static  String day="15";

	@BeforeClass
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority=1)
	public void enterUrl() {
		driver.get("http://zero.webappsecurity.com/index.html");
		String actualTitle=driver.getTitle();
		String expectedTitle= "Zero - Personal Banking - Loans - Credit Cards";
		Assert.assertEquals(actualTitle, expectedTitle, "Zero bank title displayed");
	}

	@Test(priority=2)	
	public void login() {
		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.id("user_login")).sendKeys("username");
		driver.findElement(By.id("user_password")).sendKeys("password");
		driver.findElement(By.name("submit")).click();

		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();

		String actualTitle = driver.getTitle();
		String expectedTitle= "Zero - Account Summary";
		Assert.assertEquals(actualTitle, expectedTitle);}

	@Test(priority=3)
	public void payBills() throws InterruptedException, IOException {
		driver.findElement(By.linkText("Pay Bills")).click();//Pay Bills
		driver.findElement(By.id("sp_payee")).click();//payee element locator
		WebElement Combobox = driver.findElement(By.id("sp_payee"));//payee element locator stored
		Select sel = new Select(Combobox); 
		List<WebElement>ComboList=sel.getOptions();
		System.out.println(ComboList.size());
		for(int i=0; i<ComboList.size();i++)
		{
			System.out.println(ComboList.get(i).getText());

		}

		driver.findElement(By.id("sp_account")).click();//Account element locator
		WebElement Combobox1 = driver.findElement(By.id("sp_account"));
		Select sel1 = new Select(Combobox1);
		List<WebElement>ComboList1=sel1.getOptions();
		System.out.println(ComboList1.size());
		for(int i=0; i<ComboList1.size();i++)
		{
			System.out.println(ComboList1.get(i).getText());

		}

		driver.findElement(By.id("sp_amount")).sendKeys("200");//Amount element
		driver.findElement(By.id("sp_date")).click();// date element
		Thread.sleep(2000);
		while(true)
		{

			String text=driver.findElement(By.xpath("//body/div[@id='ui-datepicker-div']/div[1]/div[1]")).getText();// month/year element
			if(text.equals(month))
			{
				break;


			}
			else {

				driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();// arrow element locator(chropath)to future month/year
			}

			driver.findElement(By.xpath("//a[contains(text(),"+day+")]")).click();//day element locator(chropath) for day and use +day+ for parameterizing
			Thread.sleep(3000);
			driver.findElement(By.id("sp_description")).sendKeys("TD mortgage");//description element locator
			driver.findElement(By.id("pay_saved_payees")).click();
			String actualText= driver.findElement(By.xpath("//span[contains(text(),'The payment was successfully submitted.')]")).getText();
			String expectedText="The payment was successfully submitted.";
			Assert.assertEquals(actualText, expectedText);
			File paybill= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(paybill, new File("c:\\Screenshots\\Paybill.jpg"));

		}		}		

	@AfterClass
	public void closeBrowser() {
		driver.close();}
}








