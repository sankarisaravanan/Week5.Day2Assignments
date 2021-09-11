package leaftapsTestcasesWithDataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public ChromeDriver driver;
	
	@Parameters({"url", "name", "password"})
	@BeforeMethod
	public void preCondition(String urllink, String name, String pwd) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.get("http://leaftaps.com/opentaps/");
		driver.get(urllink);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("username")).sendKeys(name);
		
		
		//driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.id("password")).sendKeys(pwd);
				
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		
	}
	@AfterMethod
	public void postCondition() {
		driver.close();
	}
}
