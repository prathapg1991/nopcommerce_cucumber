package StepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	public SearchCustomerPage searchCust;
	public static Logger logger;
	public Properties configprop;
	
public 	AddcustomerPage addcust;
//Created for generating random string for unique email id 

public static String randomestring()
{
String generatedString1=RandomStringUtils.randomAlphabetic(5);

return (generatedString1);


}
	

	
	
}