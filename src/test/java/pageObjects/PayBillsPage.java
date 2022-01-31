package pageObjects;

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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PayBillsPage {

	WebDriver driver;

	public PayBillsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);}

	static String month;
	static  String day;

	@FindBy(linkText="Pay Bills")
	public WebElement PayBills;
	public void clickPayBills() {
		PayBills.click();				
	}

	@FindBy(id="sp_payee")
	public WebElement payee;
	public void clickPayee() {
		payee.click();				
	}
	@FindBy(id="sp_payee")
	public WebElement Combobox;
	public void selectCombobox(String payee) {

		Select sel = new Select(Combobox); 
		List<WebElement>ComboList=sel.getOptions();
		System.out.println(ComboList.size());
		for(int i=0; i<ComboList.size();i++)
		{
			System.out.println(ComboList.get(i).getText());

		}
		sel.selectByVisibleText(payee);
		Combobox.click();				
	}


	@FindBy(id="sp_account")
	public WebElement Combobox1;
	public void selectAccountCombobox(String account){
		Combobox1.click();

		Select sel1 = new Select(Combobox1);
		List<WebElement>ComboList1=sel1.getOptions();
		System.out.println(ComboList1.size());
		for(int i=0; i<ComboList1.size();i++)
		{
			System.out.println(ComboList1.get(i).getText());

		}sel1.selectByVisibleText(account);
	}

	@FindBy(id="sp_amount")
	public WebElement amount;
	public void enterAmount(String amt) {
		amount.sendKeys(amt);
	}


	@FindBy(id="sp_date")
	public WebElement dates;
	@FindBy(xpath="//body/div[@id='ui-datepicker-div']/div[1]/div[1]")
	public WebElement getMonthText;

	@FindBy(xpath="//span[contains(text(),'Next')]")
	public WebElement clickMonthNext;
	@FindBy(xpath="//a[contains(text(),'25')]")
	public WebElement days;
	public void clickAndSelectdate() throws InterruptedException
	{

		dates.click();

		while(true)
		{
			String text=getMonthText.getText();// month/year element
			if(text.equals(month))
			{
				break;

			}
			else {
				clickMonthNext.click();

			}


			days.click();
		}
	}

	@FindBy(id="sp_description")
	public WebElement describe;
	public void enterDescriptionPayment(String descript) {
		describe.click();
       describe.sendKeys(descript);

	}

	//driver.findElement(By.id("sp_description")).sendKeys("TD mortgage");//description element locator
	//driver.findElement(By.id("pay_saved_payees")).click();

	@FindBy(id="pay_saved_payees")	
	public WebElement pay;
	public void clickPay() {

		pay.click();

	}



}


