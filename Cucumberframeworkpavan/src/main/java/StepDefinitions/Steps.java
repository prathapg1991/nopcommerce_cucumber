package StepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
	@Before
	public void setup() throws IOException
	{
		
		logger=Logger.getLogger("nopComerce");
		PropertyConfigurator.configure("log4j.properties");
		
//		Reading propeties file 
		configprop=new Properties();
		FileInputStream configpropfile=new FileInputStream("C:\\Users\\pgurumallaiah\\Cucumber\\Cucumberframeworkpavan\\config\\config.properties");
		configprop.load(configpropfile);
		
		
		String br=configprop.getProperty("browser");
//		System.setProperty("webdriver.chrome.driver", "‪C:\\Users\\pgurumallaiah\\Cucumber\\CucumberdemoframeworkDrivers/chromedriver.exe");
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",configprop.getProperty("chromepath"));
		driver=new ChromeDriver();
		}
		
		else if(br.equals("firefox"))
		{
		System.setProperty("webdriver.gecko.driver",configprop.getProperty("firefoxpath"));
		driver=new FirefoxDriver();
		}
		else
		{
			System.setProperty("webdriver.ie.driver",configprop.getProperty("iepath"));
			driver=new FirefoxDriver();
		}
		
		logger.info("**************Launching browser***********");	
	}
	


	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		
		lp=new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		logger.info("**************Launching url***********");
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("**************Providing login details***********");
		lp.setusername(email);
		lp.setpassword(password);
		
	}

	@When("Click on Login")
	public void click_on_login() {
		logger.info("*************Started login***********");
	 lp.login();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
		
//		String title="Dashboard / nopCommerce administration";
		
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			logger.info("**************Login passed***********");
			Assert.assertTrue(false);
		} else {
			logger.info("**************Login failed***********");
			Assert.assertEquals(title, driver.getTitle());
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
	 lp.logout();
	Thread.sleep(3000);
	}
	

	@Then("close browser")
	public void close_browser() {
		logger.info("**************Closing browser***********");
		driver.close();
	}

	
//	Customer feature step definition 
	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		addcust =new AddcustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addcust.getPageTitle());
	}

	
	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
		addcust.clickOnCustomersMenu();
	}
	
	
	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		addcust.clickOnCustomersMenuItem();
		Thread.sleep(2000); 
	}
	
	@When("click on Add new button")
	public void click_on_add_new_button() {
		addcust.clickOnAddnew();
	}
	
	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
	Assert.assertEquals("Add a new customer / nopCommerce administration", addcust.getPageTitle());  
	}
	
	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("**************Adding new customer***********");
		logger.info("**************Providing customer details***********");
		String email=randomestring()+"@gmail.com";
	   addcust.setEmail(email);
	   addcust.setPassword("test123");
	   
	   addcust.setCustomerRoles("Guest");
	   Thread.sleep(3000);
	     
	   addcust.setManagerOfVendor("Vendor 2");
	   addcust.setGender("Male");
	   addcust.setFirstName("Pavan");
	   addcust.setLastName("Kumar");
	   addcust.setDob("7/05/1985");
	   addcust.setCompanyName("busyQA");
	   addcust.setAdminContent("This is for testing.........");
	   
	}
	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("**************Saving customer data ***********");
		addcust.clickOnSave();
		Thread.sleep(2000);
	}
	
	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
	Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
			.contains("The new customer has been added successfully"));
	}

	
	//steps for searching a customer using Email ID.........
	
	
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		
		logger.info("**************Searching customer using email id ***********");
		searchCust=new SearchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
		
	}



	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	}
	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
	   
		boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
	Assert.assertEquals(true, status);
	}


//	steps for searching a customer by first name and last name 
	
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		logger.info("**************Searching customer by name ***********");
	
		searchCust= new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
		
	}



	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		searchCust.setLastName("Terces");
		
	}
	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}


	
	
	
	
	
}
