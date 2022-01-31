package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

import TestCases.base;


public class AddPayeePage  {

	WebDriver driver;
	
	public AddPayeePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);}

	@FindBy(linkText="Pay Bills")
	public WebElement payBills;
	public void clickPaybills() {
		payBills.click();
	}

	@FindBy(linkText="Add New Payee")
	public WebElement addNewPayee;
	public void clickAddNewPayee() {
		addNewPayee.click();

	}

	@FindBy(id="np_new_payee_name")
	public WebElement payeeName;
	public void enterPayee(String pname) {
		payeeName.sendKeys(pname);

	}
	@FindBy(id="np_new_payee_address")
	public WebElement payeeAddress;
	public void enterPayeeAddress(String paddress) {
		payeeAddress.sendKeys(paddress);
	}

	@FindBy(id="np_new_payee_account")
	public WebElement payeeAccount;
	public void enterPayeeAccount(String paccount) {
		payeeAccount.sendKeys(paccount);
	}

	@FindBy(id="np_new_payee_details")
	public WebElement payeeDetails;
	public void enterPayeeDetails(String pdetails) {
		payeeDetails.sendKeys(pdetails);
	}
	@FindBy(id="add_new_payee")
	public WebElement addBtn;

	public void clickAddBtn() throws InterruptedException {
		addBtn.click();

	}

}



